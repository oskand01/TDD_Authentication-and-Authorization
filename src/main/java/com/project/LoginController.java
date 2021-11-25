package com.project;

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
