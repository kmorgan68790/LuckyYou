package learn.luckyyou.models;

import java.util.Objects;

public class Numerology {
    private int numerologyId;
    private int lifePathNumber;
    private String lifePathDescription;
    private int birthdayNumber;
    private String birthdayDescription;
    private int expressionNumber;
    private String expressionDescription;
    private int personalityNumber;
    private String personalityDescription;
    private int soulUrgeNumber;
    private String soulUrgeDescription;
    private int luckyNumberMonth;
    private int luckyNumberDay;
    private int luckyNumberYear;
    private int userId;
    private int concordGroupId;

    public Numerology(int numerologyId, int lifePathNumber, String lifePathDescription, int birthdayNumber,
                      String birthdayDescription, int expressionNumber, String expressionDescription,
                      int personalityNumber, String personalityDescription, int soulUrgeNumber,
                      String soulUrgeDescription, int luckyNumberMonth, int luckyNumberDay, int luckyNumberYear,
                      int userId, int concordGroupId) {
        this.numerologyId = numerologyId;
        this.lifePathNumber = lifePathNumber;
        this.lifePathDescription = lifePathDescription;
        this.birthdayNumber = birthdayNumber;
        this.birthdayDescription = birthdayDescription;
        this.expressionNumber = expressionNumber;
        this.expressionDescription = expressionDescription;
        this.personalityNumber = personalityNumber;
        this.personalityDescription = personalityDescription;
        this.soulUrgeNumber = soulUrgeNumber;
        this.soulUrgeDescription = soulUrgeDescription;
        this.luckyNumberMonth = luckyNumberMonth;
        this.luckyNumberDay = luckyNumberDay;
        this.luckyNumberYear = luckyNumberYear;
        this.userId = userId;
        this.concordGroupId = concordGroupId;
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

    public String getLifePathDescription() {
        return lifePathDescription;
    }

    public void setLifePathDescription(String lifePathDescription) {
        this.lifePathDescription = lifePathDescription;
    }

    public int getBirthdayNumber() {
        return birthdayNumber;
    }

    public void setBirthdayNumber(int birthdayNumber) {
        this.birthdayNumber = birthdayNumber;
    }

    public String getBirthdayDescription() {
        return birthdayDescription;
    }

    public void setBirthdayDescription(String birthdayDescription) {
        this.birthdayDescription = birthdayDescription;
    }

    public int getExpressionNumber() {
        return expressionNumber;
    }

    public void setExpressionNumber(int expressionNumber) {
        this.expressionNumber = expressionNumber;
    }

    public String getExpressionDescription() {
        return expressionDescription;
    }

    public void setExpressionDescription(String expressionDescription) {
        this.expressionDescription = expressionDescription;
    }

    public int getPersonalityNumber() {
        return personalityNumber;
    }

    public void setPersonalityNumber(int personalityNumber) {
        this.personalityNumber = personalityNumber;
    }

    public String getPersonalityDescription() {
        return personalityDescription;
    }

    public void setPersonalityDescription(String personalityDescription) {
        this.personalityDescription = personalityDescription;
    }

    public int getSoulUrgeNumber() {
        return soulUrgeNumber;
    }

    public void setSoulUrgeNumber(int soulUrgeNumber) {
        this.soulUrgeNumber = soulUrgeNumber;
    }

    public String getSoulUrgeDescription() {
        return soulUrgeDescription;
    }

    public void setSoulUrgeDescription(String soulUrgeDescription) {
        this.soulUrgeDescription = soulUrgeDescription;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getConcordGroupId() {
        return concordGroupId;
    }

    public void setConcordGroupId(int concordGroupId) {
        this.concordGroupId = concordGroupId;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Numerology that)) return false;
        return numerologyId == that.numerologyId && lifePathNumber == that.lifePathNumber && birthdayNumber == that.birthdayNumber
                && expressionNumber == that.expressionNumber && personalityNumber == that.personalityNumber && soulUrgeNumber ==
                that.soulUrgeNumber && luckyNumberMonth == that.luckyNumberMonth && luckyNumberDay == that.luckyNumberDay
                && luckyNumberYear == that.luckyNumberYear && userId == that.userId && concordGroupId == that.concordGroupId
                && Objects.equals(lifePathDescription, that.lifePathDescription) && Objects.equals(birthdayDescription,
                that.birthdayDescription) && Objects.equals(expressionDescription, that.expressionDescription)
                && Objects.equals(personalityDescription, that.personalityDescription) && Objects.equals(soulUrgeDescription,
                that.soulUrgeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerologyId, lifePathNumber, lifePathDescription, birthdayNumber, birthdayDescription,
                expressionNumber, expressionDescription, personalityNumber, personalityDescription, soulUrgeNumber,
                soulUrgeDescription, luckyNumberMonth, luckyNumberDay, luckyNumberYear, userId, concordGroupId);
    }
}
