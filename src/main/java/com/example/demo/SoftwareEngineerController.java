package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/software-engineers")
public class SoftwareEngineerController {
    // This class will handle HTTP requests related to Software Engineers
    // You can define methods here to handle GET, POST, PUT, DELETE requests
    // For example, you might have methods like getAllEngineers(), getEngineerById(), createEngineer(), etc.
    @GetMapping("/all")
    public String getAllEngineers() {
        return "This method will return a list of all software engineers";
    }


}
