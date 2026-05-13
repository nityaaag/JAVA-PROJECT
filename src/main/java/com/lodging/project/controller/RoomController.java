package com.lodging.project.controller;

import com.lodging.project.entity.Room;
import com.lodging.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Room Controller Class
 * This controller handles all HTTP requests related to Room CRUD operations
 * 
 * The @Controller annotation tells Spring this is a web controller
 * It processes HTTP requests and returns views (HTML templates)
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {

    // @Autowired tells Spring to inject RoomService automatically
    @Autowired
    private RoomService roomService;

    /**
     * Display all rooms
     * URL: http://localhost:8080/rooms
     * 
     * @param model - Model to pass data to the view
     * @return rooms.html template
     */
    @GetMapping
    public String getAllRooms(Model model) {
        // Get all rooms from service
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms"; // Returns rooms.html
    }

    /**
     * Display form to add new room
     * URL: http://localhost:8080/rooms/add
     * 
     * @param model - Model to pass data to the view
     * @return add-room.html template
     */
    @GetMapping("/add")
    public String addRoomForm(Model model) {
        // Create a new empty Room object for the form
        model.addAttribute("room", new Room());
        // Add room types as a list for dropdown
        model.addAttribute("roomTypes", new String[]{"Simple Room", "Deluxe Room", "Luxury Room", "Suite Room", "Lake View Room"});
        // Add status options as a list for dropdown
        model.addAttribute("statusOptions", new String[]{"Available", "Occupied", "Under Maintenance"});
        return "add-room"; // Returns add-room.html
    }

    /**
     * Save a new room or update existing room
     * URL: http://localhost:8080/rooms/save (POST request)
     * 
     * @param room - Room object with data from form
     * @return Redirect to /rooms to display all rooms
     */
    @PostMapping("/save")
    public String saveRoom(@ModelAttribute Room room) {
        // Save room using service
        roomService.saveRoom(room);
        // Redirect to rooms list
        return "redirect:/rooms";
    }

    /**
     * Display form to edit existing room
     * URL: http://localhost:8080/rooms/edit/{id}
     * 
     * @param id - Room ID to edit
     * @param model - Model to pass data to the view
     * @return edit-room.html template
     */
    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        // Find the room by ID
        Optional<Room> room = roomService.getRoomById(id);
        
        if (room.isPresent()) {
            // Add room to model for form
            model.addAttribute("room", room.get());
            // Add room types for dropdown
            model.addAttribute("roomTypes", new String[]{"Simple Room", "Deluxe Room", "Luxury Room", "Suite Room", "Lake View Room"});
            // Add status options for dropdown
            model.addAttribute("statusOptions", new String[]{"Available", "Occupied", "Under Maintenance"});
            return "edit-room"; // Returns edit-room.html
        } else {
            // If room not found, redirect to rooms list
            return "redirect:/rooms";
        }
    }

    /**
     * Delete a room by ID
     * URL: http://localhost:8080/rooms/delete/{id}
     * 
     * @param id - Room ID to delete
     * @return Redirect to /rooms to display all rooms
     */
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        // Check if room exists before deleting
        if (roomService.roomExists(id)) {
            roomService.deleteRoom(id);
        }
        // Redirect to rooms list
        return "redirect:/rooms";
    }
}
