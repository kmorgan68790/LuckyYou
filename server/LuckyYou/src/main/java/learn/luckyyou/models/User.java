package learn.luckyyou.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int userId;
    private int zodiacId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String userName;
    private String password;
    private String email;

    public User(int userId, int zodiacId, String firstName, String middleName, String lastName, LocalDate dob,
                String userName, String password, String email) {
        this.userId = userId;
        this.zodiacId = zodiacId;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return userId == user.userId && zodiacId == user.zodiacId && Objects.equals(firstName, user.firstName) && Objects.equals(middleName, user.middleName) && Objects.equals(lastName, user.lastName) && Objects.equals(dob, user.dob) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, zodiacId, firstName, middleName, lastName, dob, userName, password, email);
    }
}
