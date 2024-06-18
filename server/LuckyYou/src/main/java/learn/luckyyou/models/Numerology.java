package learn.luckyyou.models;

import java.util.Objects;

public class Numerology {
    private int numerologyId;
    private int lifePathNumber;
    private int birthdayNumber;
    private int expressionNumber;
    private int personalityNumber;
    private int soulUrgeNumber;
    private int luckyNumberMonth;
    private int luckyNumberDay;
    private int luckyNumberYear;
    private int numerologyDescriptionId;

    public Numerology(int numerologyId, int lifePathNumber, int birthdayNumber, int expressionNumber, int personalityNumber,
                      int soulUrgeNumber, int luckyNumberMonth, int luckyNumberDay, int luckyNumberYear,
                      int numerologyDescriptionId) {
        this.numerologyId = numerologyId;
        this.lifePathNumber = lifePathNumber;
        this.birthdayNumber = birthdayNumber;
        this.expressionNumber = expressionNumber;
        this.personalityNumber = personalityNumber;
        this.soulUrgeNumber = soulUrgeNumber;
        this.luckyNumberMonth = luckyNumberMonth;
        this.luckyNumberDay = luckyNumberDay;
        this.luckyNumberYear = luckyNumberYear;
        this.numerologyDescriptionId = numerologyDescriptionId;
    }

    public Numerology() {}

    public int getNumerologyDescriptionId() {
        return numerologyDescriptionId;
    }

    public void setNumerologyDescriptionId(int numerologyDescriptionId) {
        this.numerologyDescriptionId = numerologyDescriptionId;
    }

    public int getNumerologyId() {
        return numerologyId;
    }

    public void setNumerologyId(int numerologyId) {
        this.numerologyId = numerologyId;
    }

    public int getLifePathNumber() {
        return lifePathNumber;
    }

    public void setLifePathNumber(int lifePathNumber) {
        this.lifePathNumber = lifePathNumber;
    }

    public int getBirthdayNumber() {
        return birthdayNumber;
    }

    public void setBirthdayNumber(int birthdayNumber) {
        this.birthdayNumber = birthdayNumber;
    }

    public int getExpressionNumber() {
        return expressionNumber;
    }

    public void setExpressionNumber(int expressionNumber) {
        this.expressionNumber = expressionNumber;
    }

    public int getPersonalityNumber() {
        return personalityNumber;
    }

    public void setPersonalityNumber(int personalityNumber) {
        this.personalityNumber = personalityNumber;
    }

    public int getSoulUrgeNumber() {
        return soulUrgeNumber;
    }

    public void setSoulUrgeNumber(int soulUrgeNumber) {
        this.soulUrgeNumber = soulUrgeNumber;
    }

    public int getLuckyNumberMonth() {
        return luckyNumberMonth;
    }

    public void setLuckyNumberMonth(int luckyNumberMonth) {
        this.luckyNumberMonth = luckyNumberMonth;
    }

    public int getLuckyNumberDay() {
        return luckyNumberDay;
    }

    public void setLuckyNumberDay(int luckyNumberDay) {
        this.luckyNumberDay = luckyNumberDay;
    }

    public int getLuckyNumberYear() {
        return luckyNumberYear;
    }

    public void setLuckyNumberYear(int luckyNumberYear) {
        this.luckyNumberYear = luckyNumberYear;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Numerology that)) return false;
        return numerologyId == that.numerologyId && lifePathNumber == that.lifePathNumber && birthdayNumber ==
                that.birthdayNumber && expressionNumber == that.expressionNumber && personalityNumber ==
                that.personalityNumber && soulUrgeNumber == that.soulUrgeNumber && luckyNumberMonth == that.luckyNumberMonth
                && luckyNumberDay == that.luckyNumberDay && luckyNumberYear == that.luckyNumberYear &&
                numerologyDescriptionId == that.numerologyDescriptionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerologyId, lifePathNumber, birthdayNumber, expressionNumber, personalityNumber, soulUrgeNumber,
                luckyNumberMonth, luckyNumberDay, luckyNumberYear, numerologyDescriptionId);
    }
}
