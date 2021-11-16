package com.project;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private Map<String, StoredUser> storedUsers = new HashMap<>();

    public LoginService() {
    }

    public Boolean login(String username, String password) {

        if(storedUsers.get(username) != null) {
            StoredUser storedUser = storedUsers.get(username);

            if(storedUser.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addStoredUser(String username, String password) {
        storedUsers.put(username, new StoredUser(username, password));
    }

    private class StoredUser {
        private String username;
        private String password;

        public StoredUser(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
