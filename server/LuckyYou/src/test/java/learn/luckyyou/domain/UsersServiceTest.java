package learn.luckyyou.domain;

import learn.luckyyou.data.ConcordBirthdayRepository;
import learn.luckyyou.data.ConcordGroupRepository;
import learn.luckyyou.data.UserRepository;
import learn.luckyyou.data.ZodiacRepository;
import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.Users;
import learn.luckyyou.models.Zodiac;
import org.junit.jupiter.api.Test;
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

        assertEquals(expected, actual);
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