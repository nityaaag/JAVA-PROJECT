-- =====================================================
-- Lodgings & Restaurant Management System
-- Database Setup Script
-- MySQL 8.0+
-- =====================================================

-- Drop database if it exists (optional, be careful!)
-- DROP DATABASE IF EXISTS lodging_db;

-- Create database
CREATE DATABASE IF NOT EXISTS lodging_db;

-- Use the database
USE lodging_db;

-- Note: The rooms table will be created automatically by Hibernate
-- when you run the Spring Boot application for the first time.
-- 
-- However, if you want to create it manually, use the SQL below:

CREATE TABLE IF NOT EXISTS rooms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(50) NOT NULL UNIQUE,
    room_type VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert sample data (optional)
INSERT INTO rooms (room_number, room_type, price, status) VALUES
('101', 'Simple Room', 3000, 'Available'),
('102', 'Deluxe Room', 5000, 'Occupied'),
('103', 'Luxury Room', 8000, 'Available'),
('201', 'Suite Room', 12000, 'Under Maintenance'),
('202', 'Lake View Room', 10000, 'Available'),
('203', 'Simple Room', 3000, 'Occupied');

-- Display all rooms
SELECT * FROM rooms;

-- =====================================================
-- Useful MySQL Commands for Management
-- =====================================================

-- View all rooms
-- SELECT * FROM rooms;

-- View available rooms only
-- SELECT * FROM rooms WHERE status = 'Available';

-- Delete all rooms (be careful!)
-- DELETE FROM rooms;

-- Drop the table
-- DROP TABLE rooms;

-- View table structure
-- DESCRIBE rooms;

-- Delete database
-- DROP DATABASE lodging_db;
