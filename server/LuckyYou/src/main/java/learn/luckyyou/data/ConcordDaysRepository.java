package learn.luckyyou.data;

import learn.luckyyou.models.ConcordDays;

import java.lang.classfile.Interfaces;
import java.util.List;

public interface ConcordDaysRepository {
//    ConcordDays add(ConcordDays concordDays);
//    boolean update(ConcordDays concordDays);
    ConcordDays findById(int concordDaysId);
    List<ConcordDays> findByConcordGroupId(int concordGroupId);
    List<ConcordDays> findByDayTypeAndGroupId(String dayType, int concordGroupId);

}
