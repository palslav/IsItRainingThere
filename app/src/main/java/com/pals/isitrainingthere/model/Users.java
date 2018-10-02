package com.pals.isitrainingthere.model;

public class Users {
    String userName;
    String emailId;
    String password;
    String confPassword;
    String gender;

    public Users(String userName, String emailId, String password, String confPassword, String gender) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.confPassword = confPassword;
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
