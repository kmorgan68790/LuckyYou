package learn.luckyyou.data;

import learn.luckyyou.models.ConcordBirthday;

import java.util.List;

public interface ConcordBirthdayRepository {
//    ConcordBirthday add(ConcordBirthday concordBirthday);
    ConcordBirthday findById(int id);
    List<ConcordBirthday> findAll();
    List<ConcordBirthday> findByGroupId(int concordGroupId);
    ConcordBirthday findConcordBirthdayNumber(int birthdayNumber);

//    find concorbdday# by user dob/ pass in day of month
//    boolean update(ConcordBirthday concordBirthday);
}
