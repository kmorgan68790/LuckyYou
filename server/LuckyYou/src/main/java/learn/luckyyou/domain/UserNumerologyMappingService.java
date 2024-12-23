package learn.luckyyou.domain;

import learn.luckyyou.data.NumerologyDescriptionRepository;
import learn.luckyyou.data.UserNumerologyMappingRepository;
import learn.luckyyou.models.NumerologyDescription;
import learn.luckyyou.models.UserNumerologyMapping;
import learn.luckyyou.models.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserNumerologyMappingService {
    private final UserNumerologyMappingRepository repository;

    private final NumerologyDescriptionRepository numerologyDescriptionRepository;

    public UserNumerologyMappingService(UserNumerologyMappingRepository repository, NumerologyDescriptionRepository numerologyDescriptionRepository) {
        this.repository = repository;
        this.numerologyDescriptionRepository = numerologyDescriptionRepository;
    }

    public List<UserNumerologyMapping> findAll() {
        return repository.findAll();
    }

    public UserNumerologyMapping findById(int userNumerologyMappingId) {
        return repository.findById(userNumerologyMappingId);
    }

    public List<UserNumerologyMapping> findByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    public List<UserNumerologyMapping> findByNumerologyType(String numerologyType) {
        return repository.findByNumerologyType(numerologyType);
    }

    public List<UserNumerologyMapping> findByNumerologyDescriptionId(int numerologyDescriptionId) {
        return repository.findByNumerologyDescriptionId(numerologyDescriptionId);
    }

    public UserNumerologyMapping findByUserIdAndNumerologyType(int userId, String numerologyType) {
        return repository.findByUserIdAndNumerologyType(userId,numerologyType);
    }

    public void calculateAndSaveNumerologyMappings(Users user) {
//        Deletes previous user information to store new calculation
        repository.deleteNumerologyMappingsByUserId(user.getUserId());

//        Finds all descriptions for each numerology
        List<NumerologyDescription> numerologyTypeList = numerologyDescriptionRepository.findAll();

//        Calls each calculation method below on the user and stores into variable
        int lifePathNumber = calculateLifePathNumber(user.getDob());
        int birthdayNumber = calculateBirthdayNumber(user.getDob());
        int expressionNumber = calculateExpressionNumber(user.getFirstName(), user.getMiddleName(), user.getLastName());
        int personalityNumber = calculatePersonalityNumber(user.getFirstName(), user.getMiddleName(), user.getLastName());
        int soulUrge = calculateSoulUrgeNumber(user.getFirstName(), user.getMiddleName(), user.getLastName());
        int luckyNumber = calculateLuckyNumber(user.getDob());
        int luckyYear = calculateLuckyYear(user.getFirstName(), user.getMiddleName(), user.getLastName());

//         Maps numerology types to their calculated values
        Map<String, Integer> numerologyCalculations = new HashMap<>();
        numerologyCalculations.put("Life Path", lifePathNumber);
        numerologyCalculations.put("Birthday", birthdayNumber);
        numerologyCalculations.put("Expression", expressionNumber);
        numerologyCalculations.put("Personality", personalityNumber);
        numerologyCalculations.put("Soul Urge", soulUrge);
        numerologyCalculations.put("Lucky Number", luckyNumber);
        numerologyCalculations.put("Lucky Year", luckyYear);

//         Loops through each numerology type and saves the corresponding calculation
        for (Map.Entry<String, Integer> entry : numerologyCalculations.entrySet()) {
            String numerologyType = entry.getKey();
            int numerologyValue = entry.getValue();

//             Finds the corresponding numerology description id
            NumerologyDescription description = numerologyTypeList.stream()
                    .filter(desc -> desc.getNumerologyType().equals(numerologyType) && desc.getNumerologyNumber()
                            == numerologyValue)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Numerology description not found for type: "
                            + numerologyType + " and value: " + numerologyValue));

//             Creates the mapping object
            UserNumerologyMapping mapping = new UserNumerologyMapping();
            mapping.setUserId(user.getUserId());
            mapping.setNumerologyType(numerologyType);
            mapping.setNumerologyDescriptionId(description.getNumerologyDescriptionId());

            // Saves the mapping
            repository.saveNumerologyMapping(mapping);
        }
    }

    public int calculateLifePathNumber(LocalDate dob) {
        int day = dob.getDayOfMonth();
        int month = dob.getMonthValue();
        int year = dob.getYear();

        int daySum = sumDigitsWithMasterNumber(day);
        int monthSum = sumDigitsWithMasterNumber(month);
        int yearSum = sumDigitsWithMasterNumber(year);

        int lifePathNumberSum = daySum + monthSum + yearSum;

        return reduceToSingleDigit(lifePathNumberSum);
    }

//     Calculates Birthday Number based on user's day of birth
    public int calculateBirthdayNumber(LocalDate dob) {
        int day = dob.getDayOfMonth();
        return day;
    }

//     Calculates Expression Number based on user's full name
    public int calculateExpressionNumber(String firstName, String middleName, String lastName) {
        int firstNameSum = isMasterNumber(sumLetters(firstName)) ? sumLetters(firstName) : sumDigits(sumLetters(firstName));
        int middleNameSum = isMasterNumber(sumLetters(middleName)) ? sumLetters(middleName) : sumDigits(sumLetters(middleName));
        int lastNameSum = isMasterNumber(sumLetters(lastName)) ? sumLetters(lastName) : sumDigits(sumLetters(lastName));

        int fullNameSum = sumDigitsWithMasterNumber(firstNameSum) + sumDigitsWithMasterNumber(middleNameSum) +
                sumDigitsWithMasterNumber(lastNameSum) ;

        return reduceToSingleDigit(fullNameSum);
    }

//     Calculates Personality Number based on consonants in user's full name
    public int calculatePersonalityNumber(String firstName, String middleName, String lastName) {
        String firstConsonants = firstName.replaceAll("[aeiouAEIOU]", "");
        String middleConsonants = middleName.replaceAll("[aeiouAEIOU]", "");
        String lastConsonants = lastName.replaceAll("[aeiouAEIOU]", "");

        int firstSum = isMasterNumber(sumLetters(firstConsonants)) ? sumLetters(firstConsonants) : sumDigits(sumLetters(firstConsonants));
        int middleSum = isMasterNumber(sumLetters(middleConsonants)) ? sumLetters(middleConsonants) : sumDigits(sumLetters(middleConsonants));
        int lastSum = isMasterNumber(sumLetters(lastConsonants)) ? sumLetters(lastConsonants) : sumDigits(sumLetters(lastConsonants));

        int fullNameSum = sumDigitsWithMasterNumber(firstSum) + sumDigitsWithMasterNumber(middleSum) +
                sumDigitsWithMasterNumber(lastSum);

        return reduceToSingleDigit(fullNameSum);
    }

//     Calculates Soul Urge Number based on vowels in user's full name
    public int calculateSoulUrgeNumber(String firstName, String middleName, String lastName) {
        String firstConsonants = firstName.replaceAll("[^aeiouAEIOU]", "");
        String middleConsonants = middleName.replaceAll("[^aeiouAEIOU]", "");
        String lastConsonants = lastName.replaceAll("[^aeiouAEIOU]", "");

        int firstSum = isMasterNumber(sumLetters(firstConsonants)) ? sumLetters(firstConsonants) : sumDigits(sumLetters(firstConsonants));
        int middleSum = isMasterNumber(sumLetters(middleConsonants)) ? sumLetters(middleConsonants) : sumDigits(sumLetters(middleConsonants));
        int lastSum = isMasterNumber(sumLetters(lastConsonants)) ? sumLetters(lastConsonants) : sumDigits(sumLetters(lastConsonants));

        int fullNameSum = sumDigitsWithMasterNumber(firstSum) + sumDigitsWithMasterNumber(middleSum) +
                sumDigitsWithMasterNumber(lastSum);

        return reduceToSingleDigit(fullNameSum);
    }

//     Calculates Lucky Number based on user's full date of birth
    public int calculateLuckyNumber(LocalDate dob) {
        int day = dob.getDayOfMonth();
        int month = dob.getMonthValue();
        int year = dob.getYear();

        int daySum = isMasterNumber(day) ? day : sumDigits(day);
        int monthSum = isMasterNumber(month) ? month : sumDigits(month);
        int yearSum = isMasterNumber(year) ? year : sumDigits(year) ;

        int luckyNumber = sumDigitsWithMasterNumber(daySum)  + sumDigitsWithMasterNumber(monthSum)  +
                sumDigitsWithMasterNumber(yearSum);

        return reduceToSingleDigit(luckyNumber);
    }

//     Calculates Lucky Year based on user's expression number
    public int calculateLuckyYear(String firstName, String middleName, String lastName) {
        int firstNameSum = isMasterNumber(sumLetters(firstName)) ? sumLetters(firstName) : sumDigits(sumLetters(firstName));
        int middleNameSum = isMasterNumber(sumLetters(middleName)) ? sumLetters(middleName) : sumDigits(sumLetters(middleName));
        int lastNameSum = isMasterNumber(sumLetters(lastName)) ? sumLetters(lastName) : sumDigits(sumLetters(lastName));

        int fullNameSum = sumDigitsWithMasterNumber(firstNameSum) + sumDigitsWithMasterNumber(middleNameSum) +
                sumDigitsWithMasterNumber(lastNameSum);

        return reduceToSingleDigit(fullNameSum);
    }

    // Helper methods
    // checks if a number is a master number
    public boolean isMasterNumber(int number) {
        return number == 11 || number == 22 || number == 33;
    }

    private int sumDigitsWithMasterNumber(int number) {
        if (isMasterNumber(number)) {
            return number;
        }
        while (number > 9 && !isMasterNumber(number)) {
            number = sumDigits(number);
        }
        return number;
    }

    // sum digits of a number
    private int sumDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    // reduce a number to a single digit unless it's a master number
    private int reduceToSingleDigit(int number) {
        while (number > 9 && !isMasterNumber(number)) {
            number = sumDigits(number);
        }
        return number;
    }

//    maps letters to numbers
    private int sumLetters(String text) {
        return text.chars()
                .map(this::mapCharToNumber)
                .sum();
    }

    private int mapCharToNumber(int ch) {
        if (Character.isLetter(ch)) {
            // Normalizes to a 1-26 range: 'A'->1, 'B'->2, ..., 'Z'->26
            int normalized = Character.toUpperCase(ch) - 'A' + 1;
            // Maps to 1-9 range
            return (normalized - 1) % 9 + 1;
        }
        // For non-letters returns 0
        return 0;
    }


}
