package com.example.demo.aop;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> get() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id")  String id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public User post(@RequestBody User user) {
        if (userRepository.findById(user.id()) != null) {
            throw new IllegalArgumentException("Resource already exists");
        }

        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User put(@PathVariable("id") String id, @RequestBody User user) {
        if (!Objects.equals(id, user.id())) {
            throw new IllegalArgumentException("Unmatched ids");
        }

        if (userRepository.findById(user.id()) == null) {
            throw new IllegalArgumentException("Resource not found");
        }

        return userRepository.save(user);
    }
}