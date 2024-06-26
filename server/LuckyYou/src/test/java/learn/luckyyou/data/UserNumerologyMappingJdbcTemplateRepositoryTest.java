package learn.luckyyou.data;

import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.ConcordDays;
import learn.luckyyou.models.UserNumerologyMapping;
import learn.luckyyou.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserNumerologyMappingJdbcTemplateRepositoryTest {

    @Autowired
    private UserNumerologyMappingJdbcTemplateRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    KnownGoodState knownGoodState;

    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup) {
            hasSetup = true;
            knownGoodState.set();
        }
    }

    @Test
    void findAll() {
        List<UserNumerologyMapping> result = repository.findAll();
        assertNotNull(result);

        assertTrue(result.size() >= 1 && result.size() <= 16);
    }

    @Test
    void findById() {
        UserNumerologyMapping result = repository.findById(1);

        assertEquals(1, result.getUserNumerologyMappingId());
        assertEquals("Life Path", result.getNumerologyType());
    }

    @Test
    void findByUserId() {
        List<UserNumerologyMapping> result = repository.findByUserId(2);

        assertEquals(2, result.get(0).getUserId());
        assertEquals("Life Path", result.get(0).getNumerologyType());
    }

    @Test
    void findByNumerologyType() {
        List<UserNumerologyMapping> result = repository.findByNumerologyType("Birthday");

        assertEquals(1, result.get(0).getUserId());
        assertEquals("Birthday", result.get(0).getNumerologyType());
        assertEquals(23, result.get(0).getNumerologyDescriptionId());
    }

    @Test
    void findByNumerologyDescriptionId() {
        List<UserNumerologyMapping> result = repository.findByNumerologyDescriptionId(11);

        assertEquals(11, result.get(0).getNumerologyDescriptionId());
        assertEquals("Life Path", result.get(0).getNumerologyType());
    }

    @Test
    void findByUserIdAndNumerologyType() {
        UserNumerologyMapping expected = new UserNumerologyMapping(8,2,
                "Life Path", 1);

        UserNumerologyMapping actual = repository.findByUserIdAndNumerologyType(2,
                "Life Path");

        assertEquals(expected.getNumerologyType(), actual.getNumerologyType());
        assertEquals(expected.getUserId(), actual.getUserId());
    }

}