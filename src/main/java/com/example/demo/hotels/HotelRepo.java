package com.example.demo.hotels;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, Integer> {
//    // You can add custom query methods if needed
//	User findByUsernameAndPassword(String username, String password);
}
