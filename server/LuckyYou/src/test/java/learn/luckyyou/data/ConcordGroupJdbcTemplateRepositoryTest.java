package learn.luckyyou.data;

import learn.luckyyou.models.ConcordGroup;
import learn.luckyyou.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ConcordGroupJdbcTemplateRepositoryTest {
    @Autowired
    private ConcordGroupJdbcTemplateRepository repository;

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
        ConcordGroup concordGroup = repository.findById(1);

        assertEquals(1, concordGroup.getConcordGroupId());
        assertEquals(1, concordGroup.getConcordGroupNumber());
    }

    @Test
    void findAll() {
        List<ConcordGroup> concordGroup = repository.findAll();
        assertNotNull(concordGroup);

        assertTrue(concordGroup.size() >= 1 && concordGroup.size() <= 5);
    }
}