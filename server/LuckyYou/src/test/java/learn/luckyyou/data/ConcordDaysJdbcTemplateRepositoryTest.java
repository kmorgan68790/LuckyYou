package learn.luckyyou.data;

import learn.luckyyou.models.ConcordDays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ConcordDaysJdbcTemplateRepositoryTest {
    @Autowired
    private ConcordDaysJdbcTemplateRepository repository;

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
        ConcordDays concordDay = repository.findById(1);

        assertEquals(1, concordDay.getConcordDaysId());
        assertEquals("mental", concordDay.getDayType());
    }

    @Test
    void findByConcordGroupId() {
        ConcordDays concordDay = new ConcordDays(2,"mental",25,1);

        List<ConcordDays> actual = repository.findByConcordGroupId(1);

        assertNotNull(actual);
        assertEquals(concordDay.getConcordGroupId(), actual.get(1).getConcordGroupId());
    }

    @Test
    void findByDayTypeAndGroupId() {
        ConcordDays concordDay = new ConcordDays(2,"mental",25,1);

        List<ConcordDays> actual = repository.findByConcordGroupId(1);

        assertNotNull(actual);
        assertEquals(concordDay.getConcordGroupId(), actual.get(1).getConcordGroupId());
        assertEquals(concordDay.getDayType(), actual.get(1).getDayType());
    }
}