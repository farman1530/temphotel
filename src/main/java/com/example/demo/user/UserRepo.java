package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    // You can add custom query methods if needed
	User findByUsernameAndPassword(String username, String password);
}
