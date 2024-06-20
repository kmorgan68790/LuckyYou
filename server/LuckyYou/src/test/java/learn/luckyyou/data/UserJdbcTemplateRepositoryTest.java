package learn.luckyyou.data;

import learn.luckyyou.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {
    @Autowired
    private UserJdbcTemplateRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup) {
            hasSetup = true;
            jdbcTemplate.execute("CALL test.set_known_good_state()");
        }
    }

    @Test
    void findAll() {
        List<Users> users = repository.findAll();
        assertNotNull(users);

        assertTrue(users.size() >= 1 && users.size() <= 5);
    }

    @Test
    void findById() {
        Users user = repository.findById(1);
        assertEquals(1, user.getUserId());
        assertEquals("First", user.getFirstName());
    }

    @Test
    void add() {
        // all fields
        Users newUser = makeUser();
        Users actual = repository.add(newUser);

        assertNotNull(actual);
        assertEquals(3, actual.getUserId());
    }

    @Test
    void update() {
        Users newUser = makeUser();
        newUser.setUserId(2);
        assertTrue(repository.update(newUser));
        newUser.setUserId(13);
        assertFalse(repository.update(newUser));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(13));
    }

    @Test
    void findByZodiacId() {
//        Users user = makeUser();
//        Users actual = repository.findByZodiacId(user.getZodiacId());
//
//        assertEquals(5, actual);
        Users user = makeUser();
        // Insert the user into the database before running the findByZodiacId test
        repository.add(user);

        Users actual = repository.findByZodiacId(user.getZodiacId());

        // Verify the actual user is not null and has the expected zodiacId
        assertNotNull(actual);
        assertEquals(user.getZodiacId(), actual.getZodiacId());
    }

    @Test
    void findByConcordGroupId() {
        Users user = makeUser();
        // Insert the user into the database before running the findByZodiacId test
        repository.add(user);

        Users actual = repository.findByConcordGroupId(user.getConcordGroupId());

        // Verify the actual user is not null and has the expected zodiacId
        assertNotNull(actual);
        assertEquals(user.getConcordGroupId(), actual.getConcordGroupId());
    }

    private Users makeUser() {
        Users user = new Users();
        user.setUserId(3);
        user.setUserName("user3");
        user.setPassword("password");
        user.setEmail("test3@test.com");
        user.setFirstName("Third");
        user.setMiddleName("Middle");
        user.setLastName("last");
        user.setDob(LocalDate.of(2000, 1, 20));
        user.setZodiacId(11);
        user.setConcordGroupId(2);
        return user;
    }
}