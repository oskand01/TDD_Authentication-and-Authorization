package com.project;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private Map<String, StoredUser> storedUsers = new HashMap<>();
    private SessionToken sessionToken;

    public SessionToken login(String username, String password) throws InvalidLoginException {

        if (storedUsers.get(username) != null) {
            StoredUser storedUser = storedUsers.get(username);
            if (PasswordHandler.verifyPassword(password, storedUser.getEncryptedPassword(), storedUser.getSalt())) {
                sessionToken = new SessionToken(true);
                return sessionToken;
            }
        }
        throw new InvalidLoginException();
    }

    public void addStoredUser(String username, String password) {
        String salt = PasswordHandler.generateSalt(24).get();

        storedUsers.put(username, new StoredUser(username, PasswordHandler.hashPassword(password, salt).get(), salt));
    }

    public SessionToken getSessionToken() {
        return sessionToken;
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
