package com.example.demo.aop;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryUserRepository implements UserRepository {
    private final Map<String, User> userStore;

    public MemoryUserRepository() {
        userStore = new ConcurrentHashMap<>();

        userStore.put("1", new User("1", "alice@localhost", "Alice"));
        userStore.put("2", new User("2", "bob@localhost", "Bob"));
        userStore.put("3", new User("3", "carol@localhost", "Carol"));
        userStore.put("4", new User("4", "dave@localhost", "Dave"));
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userStore.values());
    }

    @Override
    public User findById(String id) {
        return userStore.get(id);
    }

    @Override
    public User save(User user) {
        userStore.put(user.id(), user);

        return userStore.get(user.id());
    }
}
