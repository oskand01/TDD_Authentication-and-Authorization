package com.project;

import com.projekt.LoginService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    LoginService loginService;

    @Test
    void test_login_success() {
        loginService = new LoginService();

        assertTrue(loginService.login("anna", "losen"));
    }
}


