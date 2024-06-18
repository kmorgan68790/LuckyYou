package learn.luckyyou.data;

import learn.luckyyou.models.Numerology;

import java.util.List;

public interface NumerologyRepository {
    List<Numerology> findAll();

    Numerology findById(int numerologyId);

    Numerology findByLifePathNumber(int lifePathNumber);

    Numerology findByExpressionNumber(int expressionNumber);

    Numerology findByPersonalityNumber(int personalityNumber);

    Numerology findBySoulUrgeNumber(int soulUrgeNumber);

    Numerology findByBirthdayNumber(int birthdayNumber);

    List<Numerology> findByLuckyNumber(int month, int day, int year);
}
