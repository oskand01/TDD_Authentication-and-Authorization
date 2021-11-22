package com.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {


    private final Map<String, StoredUser> storedUsers = new HashMap<>();


    public void addStoredUser(String username,
                              String password,
                              String salt,
                              Map<Resource, List<Right>> privileges) {

        storedUsers.put(username, new StoredUser(username, PasswordHandler.hashPassword(password, salt).get(), salt, privileges));
    }

    public StoredUser getStoredUser(String username) {
        if(storedUsers.containsKey(username)) return storedUsers.get(username);
        return null;
    }
}
