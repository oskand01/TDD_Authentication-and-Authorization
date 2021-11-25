package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TokenAndRightsControllerTest {

    TokenAndRightsController tokenAndRightsController;

    @BeforeEach
    void setUp() {
        UserService userService = new UserService();
        String salt = PasswordUtils.generateSalt(24).get();
        userService.addStoredUser("berit", "123456", salt, Map.of(Resource.ACCOUNT, List.of(Right.READ, Right.WRITE)));
        tokenAndRightsController = new TokenAndRightsController(userService);
    }

    @Test
    void test_validate_token_success() throws InvalidLoginException {
        LoginController loginController = new LoginController(tokenAndRightsController.getUserService());
        String sessionToken = loginController.login("berit", "123456");

        assertTrue(tokenAndRightsController.validateToken(sessionToken, "valid"));
    }

    @Test
    void test_get_user_rights_success() {
        StoredUser user = tokenAndRightsController.getUserService().getStoredUser("berit");
        String sessionToken = TokenUtils.generateToken(user);

        assertEquals(List.of("READ", "WRITE"), tokenAndRightsController.authorizeUser(Resource.ACCOUNT, sessionToken));
    }

    @Test
    void test_get_user_rights_fail_success() {
        StoredUser user = tokenAndRightsController.getUserService().getStoredUser("berit");
        String sessionToken = TokenUtils.generateToken(user);

        assertNull(tokenAndRightsController.authorizeUser(Resource.PROVISION_CALC, sessionToken));
    }
}
