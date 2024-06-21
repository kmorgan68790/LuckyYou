package learn.luckyyou.domain;

import learn.luckyyou.data.UserNumerologyMappingRepository;
import learn.luckyyou.data.ZodiacRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

    }

    @Test
    void shouldCalculateLifePathBasedOnUsersFullDob() {

    }

    @Test
    void shouldCalculateBirthdayBasedOnUsersDayOfBirth() {

    }

    @Test
    void shouldCalculateExpressionBasedOnUsersFullName() {

    }

    @Test
    void shouldCalculatePersonalityBasedOnUsersFullNameConsonantsOnly() {

    }

    @Test
    void shouldCalculateSoulUrgeBasedOnUsersFullNameVowelsOnly() {

    }

    @Test
    void shouldCalculateLuckyNumberBasedOnFullDob() {

    }

    @Test
    void shouldCalculateLuckyYearBasedOnUsersExpressionNumber() {

    }

}