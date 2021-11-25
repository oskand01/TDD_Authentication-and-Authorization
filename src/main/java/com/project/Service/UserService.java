package com.project.Service;

import com.project.Enum.Resource;
import com.project.Enum.Right;
import com.project.Entity.StoredUser;
import com.project.Util.PasswordUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private final Map<String, StoredUser> storedUsers = new HashMap<>();

    public void addStoredUser(String username,
                              String password,
                              String salt,
                              Map<Resource, List<Right>> privileges) {

        storedUsers.put(username, new StoredUser(username, PasswordUtils.hashPassword(password, salt).get(), salt, privileges));
    }

    public StoredUser getStoredUser(String username) {
        if (storedUsers.containsKey(username)) return storedUsers.get(username);
        return null;
    }

    public List<String> getUserRights(Resource resource, String username) {
        StoredUser user = getStoredUser(username);

        if (user.getPrivileges().containsKey(resource)) {
            return user.getPrivileges().get(resource).stream()
                    .map(Enum::toString)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
