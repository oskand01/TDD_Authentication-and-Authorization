package com.project;

public class ValidateTokenService {

    private SessionToken sessionToken;

    public ValidateTokenService(SessionToken sessionToken) {
        this.sessionToken = sessionToken;
    }

    public SessionToken getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(SessionToken sessionToken) {
        this.sessionToken = sessionToken;
    }

    public boolean validateToken() {

        return sessionToken.getValid();
    }
}
