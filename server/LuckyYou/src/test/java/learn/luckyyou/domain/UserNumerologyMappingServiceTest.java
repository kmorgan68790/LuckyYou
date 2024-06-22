package learn.luckyyou.domain;

import learn.luckyyou.data.UserNumerologyMappingRepository;
import learn.luckyyou.data.ZodiacRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserNumerologyMappingServiceTest {
    @Autowired
    UserNumerologyMappingService service;

    @MockBean
    UserNumerologyMappingRepository repository;

    @Test
    void shouldDetermineIfMasterNumber() {
        assertTrue(service.isMasterNumber(11));
        assertFalse(service.isMasterNumber(10));
    }

    @Test
    void shouldCalculateLifePathBasedOnUsersFullDob() {
        LocalDate dob = LocalDate.of(1991, 3, 23);
        assertEquals(1, service.calculateLifePathNumber(dob));
    }

    @Test
    void shouldCalculateBirthdayBasedOnUsersDayOfBirth() {
        LocalDate dob = LocalDate.of(1991, 3, 23);
        assertEquals(23, service.calculateBirthdayNumber(dob));
    }

    @Test
    void shouldCalculateExpressionBasedOnUsersFullName() {
        String first = "John";
        String middle = "";
        String last = "Doe";

        assertEquals(8, service.calculateExpressionNumber(first,middle,last));
    }

    @Test
    void shouldCalculatePersonalityBasedOnUsersFullNameConsonantsOnly() {
        String first = "John";
        String middle = "";
        String last = "Doe";

        assertEquals(9, service.calculatePersonalityNumber(first,middle,last));
    }

    @Test
    void shouldCalculateSoulUrgeBasedOnUsersFullNameVowelsOnly() {
        String first = "John";
        String middle = "";
        String last = "Doe";

        assertEquals(8, service.calculateSoulUrgeNumber(first,middle,last));
    }

    @Test
    void shouldCalculateLuckyNumberBasedOnFullDob() {
        LocalDate dob = LocalDate.of(1991, 3, 23);
        assertEquals(1, service.calculateLuckyNumber(dob));
    }

    @Test
    void shouldCalculateLuckyYearBasedOnUsersExpressionNumber() {
        String first = "John";
        String middle = "";
        String last = "Doe";

        assertEquals(8, service.calculateLuckyYear(first,middle,last));
    }

}