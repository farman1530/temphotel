package com.example.demo.hotels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.sql.Timestamp;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;
    private String description;
    private int rating;
    private int totalrating;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Lob
    @Column(name = "img_data")
    private byte[] imgData; // Updated to reflect new column name

    // Default constructor
    public Hotel() {}

    // Parameterized constructor for creating new hotels
    public Hotel(String name, String location, String description, int rating, int totalrating, Timestamp createdAt, byte[] imgData) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.rating = rating;
        this.totalrating = totalrating;
        this.createdAt = createdAt;
        this.imgData = imgData;
    }

    // Parameterized constructor for updating hotels
    public Hotel(int id, String name, String location, String description, int rating, int totalrating, Timestamp createdAt, byte[] imgData) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.rating = rating;
        this.totalrating = totalrating;
        this.createdAt = createdAt;
        this.imgData = imgData;
    }

    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getTotalrating() {
		return totalrating;
	}

	public void setTotalrating(int totalrating) {
		this.totalrating = totalrating;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }
}
