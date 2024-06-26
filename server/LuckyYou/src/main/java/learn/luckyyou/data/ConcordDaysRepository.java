package learn.luckyyou.data;

import learn.luckyyou.models.ConcordDays;

import java.util.List;

public interface ConcordDaysRepository {
    ConcordDays findById(int concordDaysId);
//    ConcordDays findByBirthdayId(int concordBirthdayNumberId);
    List<ConcordDays> findConcordDaysByBirthdayAndGroupId(int concordBirthdayNumber, int concordGroupId);
    List<ConcordDays> findByConcordGroupId(int concordGroupId);
    List<ConcordDays> findByDayTypeAndGroupId(String dayType, int concordGroupId);
}
