package com.lodging.project.service;

import com.lodging.project.entity.Room;
import com.lodging.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Room Service Class
 * This service class contains business logic for Room operations
 * It acts as a bridge between Controller and Repository
 * 
 * The @Service annotation tells Spring this is a business logic component
 * and it should be automatically detected and registered as a Spring bean
 */
@Service
public class RoomService {

    // @Autowired tells Spring to inject RoomRepository automatically
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Get all rooms from database
     * 
     * @return List of all rooms
     */
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * Get a room by ID
     * 
     * @param id - Room ID
     * @return Optional containing Room if found, empty otherwise
     */
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    /**
     * Save or update a room
     * If ID is null, a new room is created
     * If ID exists, the room is updated
     * 
     * @param room - Room object to save
     * @return Saved room with generated ID
     */
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    /**
     * Delete a room by ID
     * 
     * @param id - Room ID to delete
     */
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    /**
     * Check if a room with given ID exists
     * 
     * @param id - Room ID to check
     * @return true if room exists, false otherwise
     */
    public boolean roomExists(Long id) {
        return roomRepository.existsById(id);
    }
}
