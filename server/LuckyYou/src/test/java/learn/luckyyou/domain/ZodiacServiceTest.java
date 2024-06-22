package learn.luckyyou.domain;

import learn.luckyyou.data.ZodiacRepository;
import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.Users;
import learn.luckyyou.models.Zodiac;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

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
        List<Zodiac> zodiacList = List.of(makeZodiacOne(),makeZodiacTwo());
        when(repository.findAll()).thenReturn(zodiacList);
        List<Zodiac> expected = repository.findAll();

        Zodiac zodiac = makeZodiacOne();
        Zodiac actual = repository.findById(zodiac.getZodiacId());
        when(repository.findById(zodiac.getZodiacId())).thenReturn(zodiac);

        assertEquals(zodiacList.get(0).getZodiacId(), zodiac.getZodiacId());
    }

    @Test
    void shouldFindZodiacStartAndEnd() {
        List<Zodiac> zodiacList = List.of(makeZodiacOne(),makeZodiacTwo());
        when(repository.findAll()).thenReturn(zodiacList);
        List<Zodiac> expected = repository.findAll();
        when(repository.findZodiacStartAndEnd(expected.get(0).getZodiacStart(), expected.get(0).getZodiacEnd()))
                .thenReturn(expected.get(0));
        Zodiac expectedOne = repository.findZodiacStartAndEnd(expected.get(0).getZodiacStart(),
                expected.get(0).getZodiacEnd());

        Zodiac zodiac = makeZodiacOne();
        Zodiac actual = repository.findZodiacStartAndEnd(zodiac.getZodiacStart(), zodiac.getZodiacEnd());
        when(repository.findZodiacStartAndEnd(zodiac.getZodiacStart(), zodiac.getZodiacEnd())).thenReturn(zodiac);

        assertEquals(expectedOne, actual);
    }

    private Zodiac makeZodiacOne () {
        Zodiac zodiacOne = new Zodiac(1, "Aries", "Description for Aries",
                LocalDate.of(2024,3,21), LocalDate.of(2024,4,19));
        return zodiacOne;
    }

    private Zodiac makeZodiacTwo () {
        Zodiac zodiacTwo = new Zodiac(2, "Taurus", "Description for Taurus",
                LocalDate.of(2024,04,20), LocalDate.of(2024,05,20));
        return zodiacTwo;
    }

    private Users makeUser() {
        Users user = new Users(1,1,1,"First", "Middle","Last",
                LocalDate.of(1991,3,23),"username", "password", "email");
        return user;
    }
}