package com.project;

import java.util.List;

public class TokenAndRightsController {

    private final UserService userService;

    public TokenAndRightsController(UserService userService) {
        this.userService = userService;
    }

    public boolean validateToken(String token, String key) {
        return TokenUtils.getClaimFromToken(token, key).equals("true");
    }

    public UserService getUserService() {
        return userService;
    }

    public List<String> authorizeUser(Resource resource, String token) {
        String username = TokenUtils.getClaimFromToken(token, "username");

        return userService.getUserRights(resource, username);
    }
}
