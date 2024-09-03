package com.example.demo.hotels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService service;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return service.getAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable int id) {
        return service.getHotelById(id);
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestParam("name") String name,
                                             @RequestParam("location") String location,
                                             @RequestParam("description") String description,
                                             @RequestParam("rating") int rating,
                                             @RequestParam("totalrating") int totalrating,
                                             @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            byte[] imageData = null;
            if (file != null && !file.isEmpty()) {
                // Convert MultipartFile to byte[]
                imageData = file.getBytes();
            }

            // Create new hotel entity
            Hotel newHotel = new Hotel(name, location, description, rating, totalrating, new Timestamp(System.currentTimeMillis()), imageData);
            Hotel savedHotel = service.saveHotel(newHotel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable int id,
                                              @RequestParam(value = "file", required = false) MultipartFile file,
                                              @RequestParam("name") String name,
                                              @RequestParam("location") String location,
                                              @RequestParam("description") String description,
                                              @RequestParam("rating") int rating,
                                              @RequestParam("totalrating") int totalrating) {
        try {
            // Fetch the existing hotel from the database
            Hotel existingHotel = service.getHotelById(id);
            if (existingHotel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            byte[] imageData = existingHotel.getImgData(); // Default to existing image

            if (file != null && !file.isEmpty()) {
                // Convert MultipartFile to byte[]
                imageData = file.getBytes();
            }

            // Create the updated hotel object
            Hotel updatedHotel = new Hotel(id, name, location, description, rating, totalrating, new Timestamp(System.currentTimeMillis()), imageData);

            // Save the updated hotel to the database
            Hotel savedHotel = service.saveHotel(updatedHotel);

            return ResponseEntity.status(HttpStatus.OK).body(savedHotel);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int id) {
        try {
            service.deleteHotel(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        Hotel hotel = service.getHotelById(id);
        byte[] imageData = hotel.getImgData();
        
        if (imageData != null) {
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust based on image format
                .body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
