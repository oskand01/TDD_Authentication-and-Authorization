package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    LoginController loginController;

    @BeforeEach
    void setUp() {
        UserService userService = new UserService();
        loginController = new LoginController(userService);
        String salt = PasswordHandler.generateSalt(24).get();
        userService.addStoredUser("kalle", "password", salt, Map.of());
    }

    @Test
    void test_login_with_encrypted_password_return_token_success() throws InvalidLoginException {
        String sessionToken = loginController.login("kalle", "password");

        assertEquals(loginController.getSessionToken("kalle").toString(), sessionToken);
    }

    @ParameterizedTest
    @CsvSource({"Wrong username, password", "kalle, Wrong password"})
    void test_login_with_encrypted_password_failure(String username, String password) {
        InvalidLoginException invalidLoginException = assertThrows(InvalidLoginException.class, () -> loginController.login(username, password));

        assertEquals("Invalid login", invalidLoginException.getMessage());
    }
}