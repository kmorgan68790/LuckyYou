package learn.luckyyou.data;

import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.ConcordDays;
import learn.luckyyou.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ConcordBirthdayJdbcTemplateRepositoryTest {
    @Autowired
    private ConcordBirthdayJdbcTemplateRepository repository;

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
    void findById() {
        ConcordBirthday concordBirthday = repository.findById(1);

        assertEquals(1, concordBirthday.getConcordBirthdayNumberId());
    }

    @Test
    void findAll() {
        List<ConcordBirthday> concordBirthday = repository.findAll();
        assertNotNull(concordBirthday);

        assertTrue(concordBirthday.size() >= 1 && concordBirthday.size() <= 5);
    }

    @Test
    void findByGroupId() {
        ConcordBirthday concordBirthday = new ConcordBirthday(3,3,3);

        List<ConcordBirthday> actual = repository.findByGroupId(3);

        assertNotNull(actual);
        assertEquals(concordBirthday.getConcordGroupId(), actual.get(0).getConcordGroupId());
    }

    @Test
    void findConcordBirthdayNumber() {
        ConcordBirthday concordBirthday = new ConcordBirthday(3,3,3);

        ConcordBirthday actual = repository.findConcordBirthdayNumber(3);

        assertNotNull(actual);
        assertEquals(concordBirthday.getConcordBirthdayNumber(), actual.getConcordBirthdayNumber());
    }
}