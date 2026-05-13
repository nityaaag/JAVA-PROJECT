package com.lodging.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Room Entity Class
 * This class represents a Room in the database
 * Each room has a unique ID, room number, type, price, and status
 */
@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String roomNumber;

    @Column(nullable = false, length = 100)
    private String roomType;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 50)
    private String status;

    /**
     * Constructor with all fields except ID (for creating new rooms)
     */
    public Room(String roomNumber, String roomType, Double price, String status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
    }
}
