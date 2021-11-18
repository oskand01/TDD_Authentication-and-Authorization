package com.project;

public class SessionToken {

    private Boolean isValid;

    public SessionToken(Boolean isValid) {

        this.isValid = isValid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
