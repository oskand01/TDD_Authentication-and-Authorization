package com.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    LoginService loginService;

    @Test
    void test_login_success() {
        loginService = new LoginService();
        String salt = PasswordHandler.generateSalt(24).get();

        loginService.addStoredUser("kalle", PasswordHandler.hashPassword("password", salt).get(), salt);

        assertTrue(loginService.login("kalle", "password"));
    }
}


