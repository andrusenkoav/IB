package ru.ibank.db.mapper;

import java.sql.Date;

public class UserMapper {
    private String absID;
    private String firstName;
    private String middleNme;
    private String lastName;
    private Date birthday;

    public String getAbsID() {
        return absID;
    }

    public void setAbsID(String absID) {
        this.absID = absID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleNme() {
        return middleNme;
    }

    public void setMiddleNme(String middleNme) {
        this.middleNme = middleNme;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
