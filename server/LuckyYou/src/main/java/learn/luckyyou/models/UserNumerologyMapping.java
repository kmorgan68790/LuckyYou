package learn.luckyyou.models;

import java.util.Objects;

public class UserNumerologyMapping {
    private int userNumerologyMappingId;
    private int userId;
    private String numerologyType;
    private int numerologyDescriptionId;

    public UserNumerologyMapping(int userNumerologyMappingId, int userId, String numerologyType, int numerologyDescriptionId) {
        this.userNumerologyMappingId = userNumerologyMappingId;
        this.userId = userId;
        this.numerologyType = numerologyType;
        this.numerologyDescriptionId = numerologyDescriptionId;
    }

    public UserNumerologyMapping() {
    }

    public int getUserNumerologyMappingId() {
        return userNumerologyMappingId;
    }

    public void setUserNumerologyMappingId(int userNumerologyMappingId) {
        this.userNumerologyMappingId = userNumerologyMappingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNumerologyType() {
        return numerologyType;
    }

    public void setNumerologyType(String numerologyType) {
        this.numerologyType = numerologyType;
    }

    public int getNumerologyDescriptionId() {
        return numerologyDescriptionId;
    }

    public void setNumerologyDescriptionId(int numerologyDescriptionId) {
        this.numerologyDescriptionId = numerologyDescriptionId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserNumerologyMapping that)) return false;
        return userNumerologyMappingId == that.userNumerologyMappingId && userId == that.userId && numerologyType == that.numerologyType && numerologyDescriptionId == that.numerologyDescriptionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNumerologyMappingId, userId, numerologyType, numerologyDescriptionId);
    }
}


