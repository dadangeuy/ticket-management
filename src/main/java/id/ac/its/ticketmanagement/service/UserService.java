package id.ac.its.ticketmanagement.service;

import id.ac.its.ticketmanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<Integer, User> userMap = new HashMap<>();

    public User create(int id, String username) {
        User user = new User(id, username);
        userMap.put(id, user);
        return user;
    }

    public User update(int id, String username) {
        User user = userMap.get(id);
        user.setUsername(username);
        userMap.put(id, user);
        return user;
    }

    public User delete(int id) {
        return userMap.remove(id);
    }

    public User retrieve(int id) {
        return userMap.get(id);
    }

    public Collection<User> list() {
        return userMap.values();
    }
}
