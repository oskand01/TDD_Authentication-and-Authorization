package com.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginService {

    private final Map<String, StoredUser> storedUsers = new HashMap<>();
    private SessionToken sessionToken;

    public SessionToken login(String username, String password) throws InvalidLoginException {

        if (storedUsers.containsKey(username)) {
            StoredUser storedUser = storedUsers.get(username);
            boolean validLogin = PasswordHandler.verifyPassword(password, storedUser.getEncryptedPassword(), storedUser.getSalt());

            if (validLogin) {
                sessionToken = new SessionToken(true, storedUser.getUsername(), storedUser.getPrivileges());
                return sessionToken;
            }
        }
        throw new InvalidLoginException();
    }

    public void addStoredUser(String username,
                              String password,
                              String salt,
                              Map<Resource, List<Right>> privileges) {

        storedUsers.put(username, new StoredUser(username, PasswordHandler.hashPassword(password, salt).get(), salt, privileges));
    }

    public SessionToken getSessionToken() {
        return sessionToken;
    }
}
