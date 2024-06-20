package learn.luckyyou.models;

import java.util.Objects;

public class ConcordBirthday {
    private Integer concordBirthdayNumberId;
    private Integer concordBirthdayNumber;
    private Integer concordGroupId;

    public ConcordBirthday(Integer concordBirthdayNumberId, Integer concordBirthdayNumber, Integer concordGroupId) {
        this.concordBirthdayNumberId = concordBirthdayNumberId;
        this.concordBirthdayNumber = concordBirthdayNumber;
        this.concordGroupId = concordGroupId;
    }

    public ConcordBirthday() {
    }

    public Integer getConcordBirthdayNumberId() {
        return concordBirthdayNumberId;
    }

    public void setConcordBirthdayNumberId(Integer concordBirthdayNumberId) {
        this.concordBirthdayNumberId = concordBirthdayNumberId;
    }

    public Integer getConcordBirthdayNumber() {
        return concordBirthdayNumber;
    }

    public void setConcordBirthdayNumber(Integer concordBirthdayNumber) {
        this.concordBirthdayNumber = concordBirthdayNumber;
    }

    public Integer getConcordGroupId() {
        return concordGroupId;
    }

    public void setConcordGroupId(Integer concordGroupId) {
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
