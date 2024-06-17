package learn.luckyyou.models;

import java.util.Objects;

public class ConcordGroup {
    private int concordGroupId;
    private int concordGroupNumber;
    private String concordGroupDescription;

    public ConcordGroup(int concordGroupId, int concordGroupNumber, String concordGroupDescription) {
        this.concordGroupId = concordGroupId;
        this.concordGroupNumber = concordGroupNumber;
        this.concordGroupDescription = concordGroupDescription;
    }

    public int getConcordGroupId() {
        return concordGroupId;
    }

    public void setConcordGroupId(int concordGroupId) {
        this.concordGroupId = concordGroupId;
    }

    public int getConcordGroupNumber() {
        return concordGroupNumber;
    }

    public void setConcordGroupNumber(int concordGroupNumber) {
        this.concordGroupNumber = concordGroupNumber;
    }

    public String getConcordGroupDescription() {
        return concordGroupDescription;
    }

    public void setConcordGroupDescription(String concordGroupDescription) {
        this.concordGroupDescription = concordGroupDescription;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ConcordGroup that)) return false;
        return concordGroupId == that.concordGroupId && concordGroupNumber == that.concordGroupNumber && Objects.equals(concordGroupDescription, that.concordGroupDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concordGroupId, concordGroupNumber, concordGroupDescription);
    }
}
