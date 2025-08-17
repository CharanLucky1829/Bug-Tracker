package com.example.bugTracker.service;

import com.example.bugTracker.model.User;
import com.example.bugTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public List<User> findAll() { return repo.findAll(); }
    public User createUser(User user) { return repo.save(user); }
    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }
 public List<User> getAllUsers(){
        return repo.findAll();
 }
}
