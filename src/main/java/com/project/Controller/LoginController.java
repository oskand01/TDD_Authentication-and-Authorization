package com.project.Controller;

import com.project.Entity.StoredUser;
import com.project.Exception.InvalidLoginException;
import com.project.Service.UserService;
import com.project.Util.PasswordUtils;
import com.project.Util.TokenUtils;

public class LoginController {

    private final UserService userservice;

    public LoginController(UserService userservice) {
        this.userservice = userservice;
    }

    public String login(String username, String password) throws InvalidLoginException {

        if (userservice.getStoredUser(username) != null) {
            StoredUser storedUser = userservice.getStoredUser(username);
            boolean validLogin = PasswordUtils.verifyPassword(password, storedUser.getEncryptedPassword(), storedUser.getSalt());

            if (validLogin) {
                return TokenUtils.generateToken(storedUser);
            }
        }

        throw new InvalidLoginException();
    }
}
