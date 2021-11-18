package com.project;

public class StoredUser {
    private String username;
    private String encryptedPassword;
    private String salt;

    public StoredUser(String username, String password, String salt) {
        this.username = username;
        this.encryptedPassword = password;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
