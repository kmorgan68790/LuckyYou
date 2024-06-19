package learn.luckyyou.data;

import learn.luckyyou.models.ConcordGroup;

import java.util.List;

public interface ConcordGroupRepository {
    ConcordGroup findById(int concordGroupId);
    List<ConcordGroup> findAll();

//    ConcordGroup add(ConcordGroup concordGroup);
//    Boolean update(ConcordGroup concordGroup);
//    Boolean deleteById(int concordGroupId);
}
