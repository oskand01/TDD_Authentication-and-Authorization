package com.project;

import java.util.List;
import java.util.stream.Collectors;

public class TokenController {

    private final SessionToken sessionToken;

    public TokenController(SessionToken sessionToken) {
        this.sessionToken = sessionToken;
    }

    public boolean validateToken() {
        return sessionToken.getValid();
    }

    public List<String> getUserRights(Resource resource) {
        return sessionToken.getRight(resource).stream()
                .map(Enum::toString)
                .collect(Collectors.toList());
    }
}
