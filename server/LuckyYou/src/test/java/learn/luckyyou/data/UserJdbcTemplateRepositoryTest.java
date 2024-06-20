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
        Users newUser = makeUser();
        newUser.setUserName("user4");
        newUser.setEmail("test4@test.com");
        Users actual = repository.add(newUser);

        assertNotNull(actual);
        assertNotNull(actual.getUserId());
        assertEquals(newUser.getUserName(), actual.getUserName());
        assertEquals(newUser.getPassword(), actual.getPassword());
        assertEquals(newUser.getEmail(), actual.getEmail());
        assertEquals(newUser.getFirstName(), actual.getFirstName());
        assertEquals(newUser.getMiddleName(), actual.getMiddleName());
        assertEquals(newUser.getLastName(), actual.getLastName());
        assertEquals(newUser.getDob(), actual.getDob());
        assertEquals(newUser.getZodiacId(), actual.getZodiacId());
        assertEquals(newUser.getConcordGroupId(), actual.getConcordGroupId());
    }

    @Test
    void update() {
        Users newUser = makeUser();
        newUser.setFirstName("xx");

        assertTrue(repository.update(newUser));

        newUser.setUserId(13);
        assertFalse(repository.update(newUser));
    }

    @Test
    void findByZodiacId() {
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

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(13));
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