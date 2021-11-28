package com.example.myecommercestore.usercredentialActivity;

public class UserModelClass {
    private String username,emailAddress,password,userId;

    public UserModelClass(String username, String emailAddress, String password, String userId) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userId = userId;
    }

    public UserModelClass() {}

    //   constructor for sign up
    public UserModelClass(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
