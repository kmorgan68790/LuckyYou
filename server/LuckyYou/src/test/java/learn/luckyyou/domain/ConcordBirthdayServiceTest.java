package learn.luckyyou.domain;

import learn.luckyyou.data.ConcordBirthdayRepository;
import learn.luckyyou.data.UserRepository;
import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.ConcordGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ConcordBirthdayServiceTest {
    @Autowired
    ConcordBirthdayService service;

    @MockBean
    ConcordBirthdayRepository repository;

    @Test
    void shouldNotFindById() {
        List<ConcordBirthday> concordBirthdayList = List.of(makeConcordBirthdayOne(),makeConcordBirthdayTwo());
        when(repository.findAll()).thenReturn(concordBirthdayList);
        List<ConcordBirthday> expected = repository.findAll();

        ConcordBirthday concordBirthday = makeConcordBirthdayOne();
        ConcordBirthday actual = repository.findById(concordBirthday.getConcordGroupId());
        when(repository.findById(concordBirthday.getConcordBirthdayNumberId())).thenReturn(concordBirthday);

        assertEquals(concordBirthdayList.get(0).getConcordBirthdayNumberId(), concordBirthday.getConcordBirthdayNumberId());
    }

    @Test
    void shouldFindByGroupId() {
        List<ConcordBirthday> concordBirthdayList = List.of(makeConcordBirthdayOne(),makeConcordBirthdayTwo());
        when(repository.findAll()).thenReturn(concordBirthdayList);
        List<ConcordBirthday> expected = repository.findAll();

        ConcordBirthday concordBirthday = makeConcordBirthdayOne();
        List<ConcordBirthday> actual = repository.findByGroupId(concordBirthday.getConcordGroupId());
        when(repository.findByGroupId(concordBirthday.getConcordBirthdayNumberId())).thenReturn(List.of(concordBirthday));

        assertEquals(concordBirthdayList.get(0).getConcordGroupId(), concordBirthday.getConcordGroupId());
    }

    @Test
    void shouldFindConcordBirthdayNumber() {
        List<ConcordBirthday> concordBirthdayList = List.of(makeConcordBirthdayOne(),makeConcordBirthdayTwo());
        when(repository.findAll()).thenReturn(concordBirthdayList);
        List<ConcordBirthday> expected = repository.findAll();

        ConcordBirthday concordBirthday = makeConcordBirthdayOne();
        ConcordBirthday actual = repository.findConcordBirthdayNumber(concordBirthday.getConcordBirthdayNumber());
        when(repository.findConcordBirthdayNumber(concordBirthday.getConcordBirthdayNumberId())).thenReturn(concordBirthday);

        assertEquals(concordBirthdayList.get(0).getConcordBirthdayNumber(), concordBirthday.getConcordBirthdayNumber());
    }

    private ConcordBirthday makeConcordBirthdayOne () {
        ConcordBirthday concordBirthdayOne = new ConcordBirthday(1,23,1);

        return concordBirthdayOne;
    }

    private ConcordBirthday makeConcordBirthdayTwo () {
        ConcordBirthday concordBirthdayTwo = new ConcordBirthday(1,23,1);

        return concordBirthdayTwo;
    }

}