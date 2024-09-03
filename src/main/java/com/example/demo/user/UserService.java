package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }
    
    public User loginUser(String username, String password) {
        User user = repo.findByUsernameAndPassword(username, password);
        if (user != null && "admin".equals(user.getRole())) {
            return user;
        } else {
            return null;
        }
    }
    
}
