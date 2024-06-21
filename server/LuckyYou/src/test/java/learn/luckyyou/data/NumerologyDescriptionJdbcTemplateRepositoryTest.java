package learn.luckyyou.data;

import learn.luckyyou.models.NumerologyDescription;
import learn.luckyyou.models.UserNumerologyMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class NumerologyDescriptionJdbcTemplateRepositoryTest {
    @Autowired
    private NumerologyDescriptionJdbcTemplateRepository repository;

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
        List<NumerologyDescription> result = repository.findAll();
        assertNotNull(result);

        assertTrue(result.size() >= 1 && result.size() <= 104);
    }

    @Test
    void findById() {
        NumerologyDescription result = repository.findById(1);

        assertEquals(1, result.getNumerologyDescriptionId());
        assertEquals("Life Path", result.getNumerologyType());
    }

    @Test
    void findByNumerologyType() {
        NumerologyDescription result = repository.findByNumerologyType("Birthday");

        assertEquals("Birthday", result.getNumerologyType());
    }

    @Test
    void findByNumerologyDescription() {
        NumerologyDescription result = repository.findByNumerologyDescription("Birthday Description");

        assertEquals("Birthday Description", result.getNumerologyDescription());
    }

    @Test
    void findByNumerologyNumber() {
        NumerologyDescription result = repository.findByNumerologyNumber(23);

        assertEquals(23, result.getNumerologyNumber());
        assertEquals("Birthday Description", result.getNumerologyDescription());
    }

    private NumerologyDescription makeNumerologyEntry() {
        NumerologyDescription numerologyDescription = new NumerologyDescription(35,
                "Birthday", "Birthday Description",23);
        return numerologyDescription;
    }
}