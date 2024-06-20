package learn.luckyyou.data;

import learn.luckyyou.models.UserNumerologyMapping;

import java.util.List;

public interface UserNumerologyMappingRepository {
    List<UserNumerologyMapping> findAll();
    UserNumerologyMapping findById(int userNumerologyMappingId);
    List<UserNumerologyMapping> findByUserId(int userId);
    List<UserNumerologyMapping> findByNumerologyType(String numerologyType);
    List<UserNumerologyMapping> findByNumerologyDescriptionId(int numerologyDescriptionId);
    List<UserNumerologyMapping> findByUserIdAndNumerologyType(int userId, String numerologyType);


//    List<Numerology> findAll();
//
//    Numerology findById(int numerologyId);
//
//    Numerology findByLifePathNumber(int lifePathNumber);
//
//    Numerology findByExpressionNumber(int expressionNumber);
//
//    Numerology findByPersonalityNumber(int personalityNumber);
//
//    Numerology findBySoulUrgeNumber(int soulUrgeNumber);
//
//    Numerology findByBirthdayNumber(int birthdayNumber);
//
//    List<Numerology> findByLuckyNumber(int month, int day, int year);
}
