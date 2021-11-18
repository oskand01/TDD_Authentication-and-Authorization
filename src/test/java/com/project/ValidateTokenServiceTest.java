package com.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateTokenServiceTest {

    ValidateTokenService validateTokenService;

    @Test
    void test_validate_token_success() throws InvalidLoginException {
        LoginService loginService = new LoginService();
        loginService.addStoredUser("kalle", "password");
        validateTokenService = new ValidateTokenService(loginService.login("kalle", "password"));

        assertTrue(validateTokenService.validateToken());
    }
}
