package learn.luckyyou.models;

import java.util.Objects;

public class UserConcordGroup {
    private int userConcordGroupId;
    private int concordGroupId;
    private int userId;

    public UserConcordGroup(int userConcordGroupId, int concordGroupId, int userId) {
        this.userConcordGroupId = userConcordGroupId;
        this.concordGroupId = concordGroupId;
        this.userId = userId;
    }

    public int getUserConcordGroupId() {
        return userConcordGroupId;
    }

    public void setUserConcordGroupId(int userConcordGroupId) {
        this.userConcordGroupId = userConcordGroupId;
    }

    public int getConcordGroupId() {
        return concordGroupId;
    }

    public void setConcordGroupId(int concordGroupId) {
        this.concordGroupId = concordGroupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserConcordGroup that)) return false;
        return userConcordGroupId == that.userConcordGroupId && concordGroupId == that.concordGroupId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userConcordGroupId, concordGroupId, userId);
    }
}
