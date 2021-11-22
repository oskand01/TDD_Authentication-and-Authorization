package com.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginService {

    private final Map<String, StoredUser> storedUsers = new HashMap<>();
    private List<SessionToken> sessionTokens = new ArrayList<>();

    public String login(String username, String password) throws InvalidLoginException {

        if (storedUsers.containsKey(username)) {
            StoredUser storedUser = storedUsers.get(username);
            boolean validLogin = PasswordHandler.verifyPassword(password, storedUser.getEncryptedPassword(), storedUser.getSalt());

            if (validLogin) {
                SessionToken sessionToken = new SessionToken(true, storedUser.getUsername(), storedUser.getPrivileges());
                sessionTokens.add(sessionToken);
                return sessionToken.toString();
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

    public SessionToken getSessionToken(String username) {
        for(SessionToken token: sessionTokens) {
            if(token.getUsername().equals(username)) return token;
        }
        return null;
    }
}
