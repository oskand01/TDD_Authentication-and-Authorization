package com.project.Entity;

import com.project.Enum.Resource;
import com.project.Enum.Right;

import java.util.List;
import java.util.Map;

public class StoredUser {
    private final String username;
    private final String encryptedPassword;
    private final String salt;
    private final Map<Resource, List<Right>> privileges;

    public StoredUser(String username,
                      String password,
                      String salt,
                      Map<Resource, List<Right>> privileges) {
        this.username = username;
        this.encryptedPassword = password;
        this.salt = salt;
        this.privileges = privileges;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public Map<Resource, List<Right>> getPrivileges() {
        return privileges;
    }

}
