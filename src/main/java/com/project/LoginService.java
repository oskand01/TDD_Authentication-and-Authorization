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
}
