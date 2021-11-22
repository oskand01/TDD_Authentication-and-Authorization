package com.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginController {

    private UserService userservice;
    private List<SessionToken> sessionTokens = new ArrayList<>();


    public LoginController(UserService userservice) {
        this.userservice = userservice;
    }

    public String login(String username, String password) throws InvalidLoginException {

        if (userservice.getStoredUser(username) != null) {
            StoredUser storedUser = userservice.getStoredUser(username);
            boolean validLogin = PasswordHandler.verifyPassword(password, storedUser.getEncryptedPassword(), storedUser.getSalt());

            if (validLogin) {
                SessionToken sessionToken = new SessionToken(true, storedUser.getUsername(), storedUser.getPrivileges());
                sessionTokens.add(sessionToken);
                return sessionToken.toString();
            }
        }
        throw new InvalidLoginException();
    }



    public SessionToken getSessionToken(String username) {
        for(SessionToken token: sessionTokens) {
            if(token.getUsername().equals(username)) return token;
        }
        return null;
    }
}
