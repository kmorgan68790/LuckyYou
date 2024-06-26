package learn.luckyyou.domain;

import learn.luckyyou.data.ConcordDaysRepository;
import learn.luckyyou.data.ConcordGroupRepository;
import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.ConcordDays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ConcordDaysServiceTest {
    @Autowired
    ConcordDaysService service;

    @MockBean
    ConcordDaysRepository repository;

    @Test
    void shouldFindById() {
        List<ConcordDays> concordDayList = List.of(makeConcordDaysOne(),makeConcordDaysTwo());
        when(repository.findById(concordDayList.get(0).getConcordDaysId())).thenReturn(concordDayList.get(0));
        ConcordDays expected = repository.findById(concordDayList.get(0).getConcordDaysId());

        ConcordDays concordDay = makeConcordDaysOne();
        ConcordDays actual = repository.findById(concordDay.getConcordDaysId());
        when(repository.findById(concordDay.getConcordDaysId())).thenReturn(concordDay);

        assertEquals(expected,actual);
    }

    @Test
    void shouldFindByConcordGroupId() {
        List<ConcordDays> concordDayList = List.of(makeConcordDaysOne(),makeConcordDaysTwo());
        when(repository.findByConcordGroupId(concordDayList.get(0).getConcordGroupId()))
                .thenReturn(List.of(concordDayList.get(0)));
        List<ConcordDays> expected = repository.findByConcordGroupId(concordDayList.get(0).getConcordGroupId());

        ConcordDays concordDay = makeConcordDaysOne();
        List<ConcordDays> actual = repository.findByConcordGroupId(concordDay.getConcordGroupId());
        when(repository.findByConcordGroupId(concordDay.getConcordGroupId())).thenReturn(List.of(concordDay));

        assertEquals(expected,actual);
    }

    @Test
    void shouldFindByDayTypeAndGroupId() {
        List<ConcordDays> concordDayList = List.of(makeConcordDaysOne(),makeConcordDaysTwo());
        when(repository.findByDayTypeAndGroupId(concordDayList.get(0).getDayType(),
                concordDayList.get(0).getConcordGroupId())).thenReturn( List.of(concordDayList.get(0),
                concordDayList.get(0)));
        List<ConcordDays> expected = repository.findByDayTypeAndGroupId(concordDayList.get(0).getDayType(),
                concordDayList.get(0).getConcordGroupId());

        ConcordDays concordDay = makeConcordDaysOne();
        List<ConcordDays> actual = repository.findByDayTypeAndGroupId(concordDay.getDayType(), concordDay.getConcordGroupId());
        when(repository.findByDayTypeAndGroupId(concordDay.getDayType(),
                concordDay.getConcordGroupId())).thenReturn(List.of(concordDay,
                concordDay));

        assertEquals(expected,actual);
    }
//
    private ConcordDays makeConcordDaysOne () {
        ConcordDays concordDayOne = new ConcordDays(1,"mental",5,1,1);

        return concordDayOne;
    }

    private ConcordDays makeConcordDaysTwo () {
        ConcordDays concordDayTwo = new ConcordDays(2,"mental",5,25,1);

        return concordDayTwo;
    }
}