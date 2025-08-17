package com.example.bugTracker.controller;

import com.example.bugTracker.model.User;
import com.example.bugTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Map<String, Object>> getAllUsers() {
        return service.findAll()
                .stream()
                .map(user -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", user.getId());
                    map.put("name", user.getName() != null ? user.getName() : user.getEmail()); // fallback to email
                    return map;
                })
                .toList();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }
}
