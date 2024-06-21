package learn.luckyyou.domain;

import learn.luckyyou.data.ZodiacRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ZodiacServiceTest {
    @Autowired
    ZodiacService service;

    @MockBean
    ZodiacRepository repository;

    @Test
    void shouldFindById() {
    }

    @Test
    void shouldFindZodiacStartAndEnd() {
    }

    @Test
    void shouldFindByDate() {
    }
}