package com.xvr.dto;


public class AppUserSignInDTO {

    private String userName;
    private String password;

    public AppUserSignInDTO() {

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
}
