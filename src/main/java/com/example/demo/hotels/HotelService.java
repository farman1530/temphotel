package com.example.demo.hotels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepo repository;

    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    public Hotel getHotelById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Hotel saveHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    public void deleteHotel(int id) {
        repository.deleteById(id);
    }
}
