package learn.luckyyou.models;

import java.time.LocalDate;
import java.util.Objects;

public class Users {
    private int userId;
    private int zodiacId;
    private int numerologyId;
    private int concordGroupId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String userName;
    private String password;
    private String email;



    public Users() {}

    public Users(int userId, int zodiacId, int numerologyId, int concordGroupId, String firstName, String middleName,
                 String lastName, LocalDate dob, String userName, String password, String email) {
        this.userId = userId;
        this.zodiacId = zodiacId;
        this.numerologyId = numerologyId;
        this.concordGroupId = concordGroupId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getZodiacId() {
        return zodiacId;
    }

    public void setZodiacId(int zodiacId) {
        this.zodiacId = zodiacId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConcordGroupId() {
        return concordGroupId;
    }

    public void setConcordGroupId(int concordGroupId) {
        this.concordGroupId = concordGroupId;
    }

    public int getNumerologyId() {
        return numerologyId;
    }

    public void setNumerologyId(int numerologyId) {
        this.numerologyId = numerologyId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Users users)) return false;
        return userId == users.userId && zodiacId == users.zodiacId && numerologyId == users.numerologyId && concordGroupId
                == users.concordGroupId && Objects.equals(firstName, users.firstName) && Objects.equals(middleName,
                users.middleName) && Objects.equals(lastName, users.lastName) && Objects.equals(dob, users.dob)
                && Objects.equals(userName, users.userName) && Objects.equals(password, users.password) &&
                Objects.equals(email, users.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, zodiacId, numerologyId, concordGroupId, firstName, middleName, lastName, dob, userName,
                password, email);
    }
}
