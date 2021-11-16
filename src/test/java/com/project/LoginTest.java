package com.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    LoginService loginService;

    @Test
    void test_login_success() {
        loginService = new LoginService();
        loginService.addStoredUser("kalle", "password");

        assertTrue(loginService.login("kalle", "password"));
    }
}


