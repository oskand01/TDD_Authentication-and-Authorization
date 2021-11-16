package com.project;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private Map<String, StoredUser> storedUsers = new HashMap<>();

    public LoginService() {
    }

    public Boolean login(String username, String password) {

        if (storedUsers.get(username) != null) {
            StoredUser storedUser = storedUsers.get(username);
            if (PasswordHandler.verifyPassword(password, storedUser.getEncryptedPassword(), storedUser.getSalt())) {
                return true;
            }
        }
        return false;
    }


    public void addStoredUser(String username, String encryptedPassword, String salt) {
        storedUsers.put(username, new StoredUser(username, encryptedPassword, salt));
    }

    private class StoredUser {
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
}
