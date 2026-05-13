package com.lodging.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller Class
 * This controller handles requests for the home page
 * 
 * The @Controller annotation tells Spring this is a web controller
 * It handles HTTP requests and returns views (HTML templates)
 */
@Controller
public class HomeController {

    /**
     * Display the home page
     * URL: http://localhost:8080/
     * 
     * @return The name of the template file to render (index.html)
     */
    @GetMapping("/")
    public String home() {
        return "index"; // Returns index.html from templates folder
    }
}
