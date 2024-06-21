package learn.luckyyou.domain;

import at.favre.lib.crypto.bcrypt.BCrypt;
import learn.luckyyou.data.*;
import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.Users;
import learn.luckyyou.models.Zodiac;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UsersService {
    private final UserRepository repository;
    private final ZodiacRepository zodiacRepository;
    private final ConcordGroupRepository concordGroupRepository;
    private final ConcordBirthdayRepository concordBirthdayRepository;
    private final UserNumerologyMappingRepository userNumerologyMappingRepository;
    private final UserNumerologyMappingService userNumerologyMappingService;

    private  int BCRYPT_ROUNDS = 12;

    public UsersService(UserRepository repository, ZodiacRepository zodiacRepository,
                        ConcordGroupRepository concordGroupRepository, ConcordBirthdayRepository concordBirthdayRepository, UserNumerologyMappingRepository userNumerologyMappingRepository, UserNumerologyMappingService userNumerologyMappingService) {
        this.repository = repository;
        this.zodiacRepository = zodiacRepository;
        this.concordGroupRepository = concordGroupRepository;
        this.concordBirthdayRepository = concordBirthdayRepository;
        this.userNumerologyMappingRepository = userNumerologyMappingRepository;
        this.userNumerologyMappingService = userNumerologyMappingService;
    }

    public List<Users> findAll() {
        return repository.findAll();
    }

    public Users findById(int userId) {
        return repository.findById(userId);
    };

    public Result<Users> add(Users user) {
        List<Zodiac> zodiacs = zodiacRepository.findAll();
        List<ConcordBirthday> birthdays = concordBirthdayRepository.findAll();
        Result<Users> result = validate(user);

        Integer zodiacId = zodiacs.stream()
                .filter(zodiac -> isDateWithinRangeIgnoreYear(user.getDob(), zodiac.getZodiacStart(), zodiac.getZodiacEnd()))
                .map(Zodiac::getZodiacId)
                .findFirst()
                .orElse(null);

        if (zodiacId != null) {
            user.setZodiacId(zodiacId);
        } else {
            // Only add error if zodiac validation is critical, otherwise it should be a warning or log
            result.addErrorMessage("Zodiac sign could not be determined based on the date of birth", ResultType.INVALID);
        }

        int userDayOfBirth = user.getDob().getDayOfMonth();
        for(ConcordBirthday birthday : birthdays) {
            if (birthday.getConcordBirthdayNumber() == userDayOfBirth) {
                user.setConcordGroupId(birthday.getConcordGroupId());
                break;
            }
        }
            if (result.isSuccess()) {
                String hashedPassword = BCrypt.withDefaults()
                        .hashToString(BCRYPT_ROUNDS, user.getPassword().toCharArray());

                user.setPassword(hashedPassword);
                Users createdUser = repository.add(user);

                // Calculate and save numerology mappings
                userNumerologyMappingService.calculateAndSaveNumerologyMappings(user);

                result.setPayload(createdUser);
            }

            return result;
    }

    public boolean isDateWithinRangeIgnoreYear(LocalDate date, LocalDate startDate, LocalDate endDate) {
        LocalDate adjustedStartDate = startDate.withYear(2000); // Arbitrary non-leap year
        LocalDate adjustedEndDate = endDate.withYear(2000);
        LocalDate adjustedDate = date.withYear(2000);

        if (adjustedStartDate.isAfter(adjustedEndDate)) {
            // Zodiac sign spans over the new year
            return (adjustedDate.isAfter(adjustedStartDate) || adjustedDate.isEqual(adjustedStartDate)) ||
                    (adjustedDate.isBefore(adjustedEndDate) || adjustedDate.isEqual(adjustedEndDate));
        } else {
            return (adjustedDate.isAfter(adjustedStartDate) || adjustedDate.isEqual(adjustedStartDate)) &&
                    (adjustedDate.isBefore(adjustedEndDate) || adjustedDate.isEqual(adjustedEndDate));
        }
    }

//    private int calculateConcordGroupId(LocalDate birthday) {
//        // Your logic to calculate Concord Group ID based on date of birth
//
//        // This is a placeholder example, replace with your own logic
//        return 1; // Replace with actual calculation logic
//    }

    public Result<Users> update(Users user) {
        Result<Users> result = validate(user);

        if (user.getUserId() <= 0) {
            result.addErrorMessage("User `id` is required", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            if (repository.update(user)) {
                result.setPayload(user);
            } else {
                result.addErrorMessage("Account was not found", ResultType.NOT_FOUND);
            }
        }
        return result;
    };


    public Result<Users> deleteById(int userId) {
        Result<Users> result = new Result<>();
        if (!repository.deleteById(userId)) {
            result.addErrorMessage("User id %s was not found", ResultType.NOT_FOUND, userId);
        }
        return result;
    };

    public Users findByZodiacId(int zodiacId) {
        return repository.findById(zodiacId);

    };

    public Users findByConcordGroupId(int concordGroupId) {
        return repository.findById(concordGroupId);
    };

    private Result validate(Users user) {
        List<Users> existingUsers = repository.findAll();

        Result result = validateNulls(user);
        if (!result.isSuccess()) {
            return result;
        }

        result = validateDob(user);
        if (!result.isSuccess()) {
            return result;
        }

        result = validateEmailAndUsernameInputs(user, existingUsers);
        if (!result.isSuccess()) {
            return result;
        }

        return result;

    }

    private Result<Users> validateDob(Users user) {
        Result<Users> result = new Result<>();
        //        validate user dob/ mostly the year
        int pastYear = 1000;
        LocalDate currentYear = LocalDate.now();

        if (user.getDob().getDayOfMonth() < 0 || user.getDob().getDayOfMonth() > 31 || user.getDob().getYear() < pastYear
                || user.getDob().getYear() > currentYear.getYear() || user.getDob().getMonth().getValue() < 0
                || user.getDob().getMonth().getValue() > 12) {
            result.addErrorMessage("Birthdate must be a valid month, day, and year", ResultType.INVALID);
        }

        return result;
    }
    public Result validateEmailAndUsernameInputs(Users user, List<Users> existingUsers) {
        Result result = new Result();

            for (Users existingUser : existingUsers) {
                if (existingUser.getEmail().equals(user.getEmail())) {
                    result.addErrorMessage("Email is already taken", ResultType.INVALID);
                    break;
                }
            }

            for (Users existingUser : existingUsers) {
                if (existingUser.getUserName().equals(user.getUserName())) {
                    result.addErrorMessage("Username is already taken", ResultType.INVALID);
                    break;
                }
            }

        return result;
    }

    private Result<Users> validateNulls(Users user) {
        List<Users> users = repository.findAll();

        Result<Users> result = new Result<>();
//
//        if (!users.contains(user.getUserId())) {
//            result.addErrorMessage("User", ResultType.INVALID);
//        }

        if (user.getUserName() == null || user.getUserName().isBlank()) {
            result.addErrorMessage("Username cannot be blank", ResultType.INVALID);
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            result.addErrorMessage("Password cannot be blank", ResultType.INVALID);
        }

        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            result.addErrorMessage("First name is required to calculate results", ResultType.INVALID);
        }

        if (user.getLastName() == null || user.getLastName().isBlank()) {
            result.addErrorMessage("Last name is required to calculate results", ResultType.INVALID);
        }

        if (user.getDob() == null ) {
            result.addErrorMessage("Date of Birth is required to calculate results", ResultType.INVALID);
        }

        return result;
    }
}
