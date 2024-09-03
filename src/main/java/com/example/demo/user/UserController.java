package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.sql.Timestamp;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
    	user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return service.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }
    
    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest loginRequest) {
        return service.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    
}
