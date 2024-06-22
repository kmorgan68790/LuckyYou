package learn.luckyyou.data;
import learn.luckyyou.models.Zodiac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ZodiacJdbcTemplateRepositoryTest {
    @Autowired
    private ZodiacJdbcTemplateRepository repository;

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
        List<Zodiac> zodiac = repository.findAll();
        assertNotNull(zodiac);

        assertTrue(zodiac.size() >= 1 && zodiac.size() <= 13);
    }

    @Test
    void findById() {
        Zodiac zodiac = repository.findById(1);

        assertEquals(1, zodiac.getZodiacId());
        assertEquals("Aries", zodiac.getZodiacName());
    }

    @Test
    void findZodiacStartAndEnd() {
        Zodiac zodiac = repository.findZodiacStartAndEnd(LocalDate.of(2024,03,21),
                LocalDate.of(2024,04,19));

        assertEquals(1, zodiac.getZodiacId());
        assertEquals(LocalDate.of(2024,03,21), zodiac.getZodiacStart());
        assertEquals(LocalDate.of(2024,04,19), zodiac.getZodiacEnd());
    }

    @Test
    void findByDate() {
        LocalDate date = LocalDate.of(1990, 8, 11);
        Zodiac zodiac = repository.findByDate(date);

        assertNotNull(zodiac);
//        make sure it returns august
        assertEquals("Leo", zodiac.getZodiacName());
    }
}