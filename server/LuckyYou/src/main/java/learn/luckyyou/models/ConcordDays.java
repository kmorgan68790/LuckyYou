package learn.luckyyou.models;

import java.util.Objects;

public class ConcordDays {
    private int concordDaysId;
    private String dayType;
    private int concordBirthdayNumber;

    private int concordDayNumber;
    private int concordGroupId;

    public ConcordDays(int concordDaysId, String dayType, int concordBirthdayNumber, int concordDayNumber, int concordGroupId) {
        this.concordDaysId = concordDaysId;
        this.dayType = dayType;
        this.concordBirthdayNumber = concordBirthdayNumber;
        this.concordDayNumber = concordDayNumber;
        this.concordGroupId = concordGroupId;
    }

    public ConcordDays() {}

    public int getConcordDaysId() {
        return concordDaysId;
    }

    public void setConcordDaysId(int concordDaysId) {
        this.concordDaysId = concordDaysId;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    public int getConcordDayNumber() {
        return concordDayNumber;
    }

    public void setConcordDayNumber(int concordDayNumber) {
        this.concordDayNumber = concordDayNumber;
    }

    public int getConcordGroupId() {
        return concordGroupId;
    }

    public void setConcordGroupId(int concordGroupId) {
        this.concordGroupId = concordGroupId;
    }

    public int getConcordBirthdayNumber() {
        return concordBirthdayNumber;
    }

    public void setConcordBirthdayNumber(int concordBirthdayNumber) {
        this.concordBirthdayNumber = concordBirthdayNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ConcordDays that)) return false;
        return concordDaysId == that.concordDaysId && concordBirthdayNumber == that.concordBirthdayNumber && concordDayNumber == that.concordDayNumber && concordGroupId == that.concordGroupId && Objects.equals(dayType, that.dayType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concordDaysId, dayType, concordBirthdayNumber, concordDayNumber, concordGroupId);
    }
}
