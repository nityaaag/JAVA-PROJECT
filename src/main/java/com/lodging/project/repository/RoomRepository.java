package com.lodging.project.repository;

import com.lodging.project.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Room Repository Interface
 * This interface extends JpaRepository which provides built-in CRUD operations
 * Methods like save(), findAll(), findById(), delete() are automatically available
 * 
 * JpaRepository<Room, Long> means:
 * - Room is the entity class to manage
 * - Long is the type of the primary key (ID)
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // JpaRepository provides these methods automatically:
    // - save(Room) - for creating and updating
    // - findById(Long) - for finding by ID
    // - findAll() - for getting all rooms
    // - delete(Room) - for deleting a room
    // - deleteById(Long) - for deleting by ID
}
