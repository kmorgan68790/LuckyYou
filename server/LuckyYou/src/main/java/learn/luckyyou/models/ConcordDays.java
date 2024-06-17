package learn.luckyyou.models;

import java.util.Objects;

public class ConcordDays {
    private int concordDaysId;
    private String dayType;
    private int dayNumber;
    private int concordGroupId;

    public ConcordDays(int concordDaysId, String dayType, int dayNumber, int concordGroupId) {
        this.concordDaysId = concordDaysId;
        this.dayType = dayType;
        this.dayNumber = dayNumber;
        this.concordGroupId = concordGroupId;
    }

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

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
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
        if (!(object instanceof ConcordDays that)) return false;
        return concordDaysId == that.concordDaysId && dayNumber == that.dayNumber && concordGroupId == that.concordGroupId
                && Objects.equals(dayType, that.dayType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concordDaysId, dayType, dayNumber, concordGroupId);
    }
}
