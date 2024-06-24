package learn.luckyyou.domain;

import learn.luckyyou.data.*;
import learn.luckyyou.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UsersServiceTest {
    @Autowired
    UsersService service;

    @Autowired
    UserNumerologyMappingService userNumerologyMappingService;
    @MockBean
    UserNumerologyMappingRepository userNumerologyMappingRepository;

    @MockBean
    NumerologyDescriptionRepository numerologyDescriptionRepository;

    @MockBean
    UserRepository repository;
    @MockBean
    ZodiacRepository zodiacRepository;
    @MockBean
    ConcordGroupRepository concordGroupRepository;
    @MockBean
    ConcordBirthdayRepository concordBirthdayRepository;


    @Test
    void shouldNotAddNonUniqueUsername() {
        Users existingUser = makeUser();
        when(repository.findAll()).thenReturn(List.of(existingUser));  // Mock findAll to return existing user
        when(repository.add(existingUser)).thenReturn(existingUser);  // Mock add to return existing user

        Users newUser = makeUser();
        newUser.setUserId(2);
        newUser.setEmail("email2");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Username is already taken", ResultType.INVALID);

        // Act
        Result<Users> actual = service.add(newUser);

        // Assert
        assertFalse(actual.isSuccess());
        assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
    }

    @Test
    void shouldNotAddNonUniqueEmail() {
        Users existingUser = makeUser();
        when(repository.findAll()).thenReturn(List.of(existingUser));  // Mock findAll to return existing user
        when(repository.add(existingUser)).thenReturn(existingUser);  // Mock add to return existing user

        Users newUser = makeUser();
        newUser.setUserId(2);
        newUser.setUserName("user2");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Email is already taken", ResultType.INVALID);

        // Act
        Result<Users> actual = service.add(newUser);

        // Assert
        assertFalse(actual.isSuccess());
        assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
    }

    @Test
    void shouldNotAddUserWithNullOrBlankPassword() {
        Users user = makeUser();
        user.setPassword("");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Password cannot be blank", ResultType.INVALID);

        Result<Users> actual = service.add(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddUserWithNullFirstName() {
        Users user = makeUser();
        user.setFirstName("");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("First name is required to calculate results", ResultType.INVALID);

        Result<Users> actual = service.add(user);

        assertEquals(expected, actual);
    };

    @Test
    void shouldAddUserWithNullMiddleName() {
        Users user = makeUser();
        user.setMiddleName("");

        Result<Users> expected = new Result<>();
        expected.setPayload(user);

        Result<Users> actual = service.add(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddUserWithNullLastName() {
        Users user = makeUser();
        user.setLastName("");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Last name is required to calculate results", ResultType.INVALID);

        Result<Users> actual = service.add(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddUserWithNullDob() {
        Users user = makeUser();
        user.setDob(LocalDate.of(0000,3,23));

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Birthdate must be a valid month, day, and year", ResultType.INVALID);
        Result<Users> actual = service.add(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNonExistingId() {
        Users newUser = makeUser();
        newUser.setUserId(1);

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Account was not found", ResultType.NOT_FOUND);

        Result<Users> actual = service.update(newUser);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNonUniqueUsername() {
        Users existingUser = makeUser();
        when(repository.findAll()).thenReturn(List.of(existingUser));
        when(repository.add(existingUser)).thenReturn(existingUser);

        Users newUser = makeUser();
        newUser.setUserId(2);
        newUser.setUserName("user2");
        newUser.setEmail("email2");
        when(repository.add(newUser)).thenReturn(newUser);

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Username is already taken", ResultType.INVALID);
        newUser.setUserName("username");

        // Act
        Result<Users> actual = service.update(newUser);

        // Assert
        assertFalse(actual.isSuccess());
        assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
    }

    @Test
    void shouldNotUpdateNonUniqueEmail() {
        Users existingUser = makeUser();
        when(repository.findAll()).thenReturn(List.of(existingUser));
        when(repository.add(existingUser)).thenReturn(existingUser);

        Users newUser = makeUser();
        newUser.setUserId(2);
        newUser.setUserName("user2");
        newUser.setEmail("email2");
        when(repository.add(newUser)).thenReturn(newUser);

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Email is already taken", ResultType.INVALID);
        newUser.setEmail("email");

        // Act
        Result<Users> actual = service.update(newUser);

        // Assert
        assertFalse(actual.isSuccess());
        assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
    }

    @Test
    void shouldNotUpdateUserWithNullOrBlankPassword() {
        Users user = makeUser();
        user.setPassword("");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Password cannot be blank", ResultType.INVALID);

        Result<Users> actual = service.update(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateUserWithNullFirstName() {
        Users user = makeUser();
        user.setFirstName("");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("First name is required to calculate results", ResultType.INVALID);

        Result<Users> actual = service.update(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateUserWithNullMiddleName() {
        Users user = makeUser();
        user.setMiddleName("");

        Result<Users> expected = new Result<>();
        expected.setPayload(user);
        Result<Users> actual = service.add(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateUserWithNullLastName() {
        Users user = makeUser();
        user.setLastName("");

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Last name is required to calculate results", ResultType.INVALID);

        Result<Users> actual = service.add(user);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateUserWithInvalidDob() {
        Users user = makeUser();
        when(repository.add(user)).thenReturn(user);

        user.setDob(LocalDate.of(0000,3,23));

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("Birthdate must be a valid month, day, and year", ResultType.INVALID);
        Result<Users> actual = service.update(user);

        assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
    }

    @Test
    void shouldNotDeleteNonExistingUser() {
        Users user = makeUser();

        Result<Users> expected = new Result<>();
        expected.addErrorMessage("User id 1 was not found", ResultType.NOT_FOUND);
        Result<Users> actual = service.deleteById(user.getUserId());


        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteExistingUser() {
        Users user = makeUser();
        when(repository.findAll()).thenReturn(List.of(user));
        when(repository.add(user)).thenReturn(user);
        when(repository.deleteById(user.getUserId())).thenReturn(true);

        Result<Users> result = service.deleteById(user.getUserId());

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldAssignZodiacSignWithUsersDobWithoutYear() {
        Zodiac zodiacSign = new Zodiac(1, "Aries","Description for Aries",
                LocalDate.of(2024,3,21), LocalDate.of(2024,4,19));
        when(zodiacRepository.findAll()).thenReturn(List.of(zodiacSign));

        Users user = makeUser();
        user.setDob(LocalDate.of(1991,3,23));
        when(repository.findAll()).thenReturn(List.of(user));
        when(repository.add(user)).thenReturn(user);

        Boolean actual = service.isDateWithinRangeIgnoreYear(user.getDob(),zodiacSign.getZodiacStart(),zodiacSign.getZodiacEnd());

        assertEquals(zodiacSign.getZodiacId(), user.getZodiacId());
        assertTrue(actual);
    }

    @Test
    void shouldAssignConcordGroupWithUsersBirthDay() {
        ConcordBirthday concordBirthday = new ConcordBirthday(1, 23,1);
        when(concordBirthdayRepository.findAll()).thenReturn(List.of(concordBirthday));

        Users user = makeUser();

        user.setDob(LocalDate.of(1991,3,23));
        when(repository.findAll()).thenReturn(List.of(user));
        when(repository.add(user)).thenReturn(user);

        assertEquals(concordBirthday.getConcordGroupId(), user.getConcordGroupId());
    }

//    @Test
//    void shouldCalculateAndSaveNumerologyMappings() {
//        Users user = makeUser();
//        user.setUserId(1);
//        user.setDob(LocalDate.of(1991, 3, 23));
//        user.setFirstName("John");
//        user.setMiddleName("A");
//        user.setLastName("Doe");
//        user.setPassword("password");
//
//        when(repository.add(user)).thenReturn(user);
//        service.add(user);
//
//        // Verify that calculateAndSaveNumerologyMappings was called
//        List<UserNumerologyMapping> numerologyMappings = userNumerologyMappingRepository.findByUserId(user.getUserId());
//
//        // Check if numerology mappings are as expected
//        assertNotNull(numerologyMappings);
//        assertFalse(numerologyMappings.isEmpty());
//    }

//    @Test
//    void shouldCalculateAndSaveNumerologyMappings() {
//        Users user = makeUser();
//        user.setUserId(1);
//        user.setDob(LocalDate.of(1991, 3, 23));
//        user.setFirstName("John");
//        user.setMiddleName("A");
//        user.setLastName("Doe");
//        user.setPassword("password");
//
//        // Mock repository behavior
//        when(repository.add(user)).thenReturn(user);
//
//        // Mock numerology description repository
//        NumerologyDescription lifePathDescription = new NumerologyDescription(1, "Life Path", "Description for Life Path", 1);
//        NumerologyDescription personalityDescription = new NumerologyDescription(2, "Personality", "Description for Personality", 1);
//        when(numerologyDescriptionRepository.findAll()).thenReturn(List.of(lifePathDescription, personalityDescription));
//
//        // Mock save behavior
////        when(userNumerologyMappingRepository.saveNumerologyMapping(any(UserNumerologyMapping.class));
//
//        // Call the method under test
//        service.add(user);
//
//        // Verify that calculateAndSaveNumerologyMappings was called
//        verify(userNumerologyMappingService, times(1)).calculateAndSaveNumerologyMappings(user);
//
//        // Fetch the saved numerology mappings for the user
//        List<UserNumerologyMapping> numerologyMappings = userNumerologyMappingRepository.findByUserId(user.getUserId());
//
//        // Check if numerology mappings are as expected
//        assertNotNull(numerologyMappings);
//        assertFalse(numerologyMappings.isEmpty());
//
//        // Verify the saved mappings
//        verify(userNumerologyMappingRepository, times(2)).saveNumerologyMapping(any(UserNumerologyMapping.class)); // adjust the times() based on the expected number of saves
//    }


    private Users makeUser() {
        Users user = new Users(1,1,1,"First", "Middle","Last",
                LocalDate.of(1991,3,23),"username", "password", "email");

        when(repository.add(user)).thenReturn(user);
        when(zodiacRepository.findAll()).thenReturn(List.of(new Zodiac(1, "Aries", "Description for Aries",
                LocalDate.of(2024, 3, 21), LocalDate.of(2024, 4, 19))));
        when(concordBirthdayRepository.findAll()).thenReturn(List.of(new ConcordBirthday(1, 23, 1)));
        return user;
    }

}