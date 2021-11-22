package com.project;

import java.util.List;
import java.util.Map;

public class SessionToken {

    private Boolean isValid;
    private String username;
    private final Map<Resource, List<Right>> privileges;

    public SessionToken(Boolean isValid, String username, Map<Resource, List<Right>> privileges) {
        this.isValid = isValid;
        this.username = username;
        this.privileges = privileges;
    }

    public Boolean getValid() {
        return isValid;
    }

    public List<Right> getRight(Resource resource) {
        if (privileges.containsKey(resource)) {
            return privileges.get(resource);
        }
        return null;
    }
}
