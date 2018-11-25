package com.xvr.model;

import javax.persistence.*;


@Entity
@Table(name = "appuser")
public class AppUser {

    @Id
    @GeneratedValue
    private Long userId;

    @Column (name = "userName")
    private String userName;

    private boolean enabled;

    @Column (name = "encryptedPassword")
    private String encryptedPassword;

    public AppUser() {
    }

    public AppUser(Long userId, String userName, boolean enabled, String encryptedPassword) {
        this.userId = userId;
        this.userName = userName;
        this.enabled = enabled;
        this.encryptedPassword = encryptedPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
