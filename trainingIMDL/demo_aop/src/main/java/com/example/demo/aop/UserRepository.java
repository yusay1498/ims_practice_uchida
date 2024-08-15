package com.example.demo.aop;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(String id);
    User save(User user);
}
