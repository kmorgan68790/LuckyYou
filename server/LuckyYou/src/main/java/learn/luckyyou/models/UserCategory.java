package learn.luckyyou.models;

import java.util.Objects;

public class UserCategory {
    private int userCategoryId;
    private int userId;
    private int categoryId;
    private int numerologyId;
    private int zodiacId;
    private int concordGroupId;

    public UserCategory(int userCategoryId, int userId, int categoryId, int numerologyId, int zodiacId, int concordGroupId) {
        this.userCategoryId = userCategoryId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.numerologyId = numerologyId;
        this.zodiacId = zodiacId;
        this.concordGroupId = concordGroupId;
    }

    public UserCategory() {}

    public int getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(int userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getNumerologyId() {
        return numerologyId;
    }

    public void setNumerologyId(int numerologyId) {
        this.numerologyId = numerologyId;
    }

    public int getZodiacId() {
        return zodiacId;
    }

    public void setZodiacId(int zodiacId) {
        this.zodiacId = zodiacId;
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
        if (!(object instanceof UserCategory that)) return false;
        return userCategoryId == that.userCategoryId && userId == that.userId && categoryId == that.categoryId && numerologyId == that.numerologyId && zodiacId == that.zodiacId && concordGroupId == that.concordGroupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCategoryId, userId, categoryId, numerologyId, zodiacId, concordGroupId);
    }
}
