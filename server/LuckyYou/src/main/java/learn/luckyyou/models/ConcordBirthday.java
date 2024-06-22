package learn.luckyyou.models;

import java.util.Objects;

public class ConcordBirthday {
    private int concordBirthdayNumberId;
    private int concordBirthdayNumber;
    private int concordGroupId;

    public ConcordBirthday(int concordBirthdayNumberId, int concordBirthdayNumber, int concordGroupId) {
        this.concordBirthdayNumberId = concordBirthdayNumberId;
        this.concordBirthdayNumber = concordBirthdayNumber;
        this.concordGroupId = concordGroupId;
    }

    public ConcordBirthday() {
    }

    public int getConcordBirthdayNumberId() {
        return concordBirthdayNumberId;
    }

    public void setConcordBirthdayNumberId(int concordBirthdayNumberId) {
        this.concordBirthdayNumberId = concordBirthdayNumberId;
    }

    public int getConcordBirthdayNumber() {
        return concordBirthdayNumber;
    }

    public void setConcordBirthdayNumber(int concordBirthdayNumber) {
        this.concordBirthdayNumber = concordBirthdayNumber;
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
        if (!(object instanceof ConcordBirthday that)) return false;
        return Objects.equals(concordBirthdayNumberId, that.concordBirthdayNumberId) && Objects.equals(concordBirthdayNumber, that.concordBirthdayNumber) && Objects.equals(concordGroupId, that.concordGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concordBirthdayNumberId, concordBirthdayNumber, concordGroupId);
    }
}
