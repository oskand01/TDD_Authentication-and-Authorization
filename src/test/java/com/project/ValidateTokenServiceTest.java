package com.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateTokenServiceTest {

    ValidateTokenService validateTokenService;

    @BeforeEach
    void setUp() {

        SessionToken sessionToken = new SessionToken(true, "berit", Map.of(Resource.ACCOUNT, List.of(Right.READ, Right.WRITE)));
        validateTokenService = new ValidateTokenService(sessionToken);
    }

    @Test
    void test_validate_token_success() {

        assertTrue(validateTokenService.validateToken());
    }

    @Test
    void test_get_user_rights_success() {

        assertEquals(List.of("READ", "WRITE"), validateTokenService.getUserRights(Resource.ACCOUNT));
    }
}
