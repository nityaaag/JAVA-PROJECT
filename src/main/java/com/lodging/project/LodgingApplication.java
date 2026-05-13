package com.lodging.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * This is the entry point of the application
 * It scans the entire com.lodging.project package for Spring components
 */
@SpringBootApplication
public class LodgingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LodgingApplication.class, args);
        System.out.println("===================================");
        System.out.println("🏨 Lodgings & Restaurant Management System");
        System.out.println("===================================");
        System.out.println("Server running on: http://localhost:8080");
        System.out.println("===================================");
    }
}
