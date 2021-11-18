package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
        String salt = PasswordHandler.generateSalt(24).get();

        loginService.addStoredUser("kalle", PasswordHandler.hashPassword("password", salt).get(), salt);
    }

    @Test
    void test_login_with_encrypted_password_success() throws InvalidLoginException {

        assertEquals(loginService.login("kalle", "password"), loginService.getSessionToken());
    }

    @ParameterizedTest
    @CsvSource({"Wrong username, password", "kalle, Wrong password"})
    void test_login_with_encrypted_password_failure(String username, String password) {
        InvalidLoginException invalidLoginException = assertThrows(InvalidLoginException.class, () -> loginService.login(username, password));

        assertEquals("Invalid login", invalidLoginException.getMessage());
    }
}