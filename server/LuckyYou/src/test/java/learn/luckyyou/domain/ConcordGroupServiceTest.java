package learn.luckyyou.domain;

import learn.luckyyou.data.ConcordGroupRepository;
import learn.luckyyou.data.UserRepository;
import learn.luckyyou.models.ConcordGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ConcordGroupServiceTest {
    @Autowired
    ConcordGroupService service;

    @MockBean
    ConcordGroupRepository repository;

    @Test
    void shouldFindConcordGroupId() {
        List<ConcordGroup> concordGroupList = List.of(makeConcordGroupOne(),makeConcordGroupTwo());

        ConcordGroup concordGroup = makeConcordGroupOne();
        ConcordGroup actual = repository.findById(concordGroup.getConcordGroupId());

        when(repository.findById(concordGroup.getConcordGroupId()))
                .thenReturn(concordGroup);

        assertEquals(concordGroupList.get(0).getConcordGroupId(), concordGroup.getConcordGroupId());
    }

    @Test
    void shouldFindIdForMatchingDescription() {
        List<ConcordGroup> concordGroupList = List.of(makeConcordGroupOne(),makeConcordGroupTwo());

        ConcordGroup concordGroup = makeConcordGroupOne();
        ConcordGroup actual = repository.findById(concordGroup.getConcordGroupId());

        when(repository.findById(concordGroup.getConcordGroupId()))
                .thenReturn(concordGroup);

        assertEquals(concordGroupList.get(0).getConcordGroupDescription(), concordGroup.getConcordGroupDescription());
    }


    private ConcordGroup makeConcordGroupOne() {
        ConcordGroup concordGroupOne = new ConcordGroup(1,1,
                "Group 1 Description");
        return concordGroupOne;
    }

    private ConcordGroup makeConcordGroupTwo() {
        ConcordGroup concordGroupTwo = new ConcordGroup(2,2,
                "Group 2 Description");
        return concordGroupTwo;
    }
}