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
    public List<SoftwareEngineer> getAllEngineers() {
        return List.of(
                new SoftwareEngineer(1, "Alice", "alice@gmail.com", List.of("Java", "Spring", "Docker")),
                new SoftwareEngineer(2, "Bob", "bob@gmail.com", List.of("Python", "Django", "AWS")),
                new SoftwareEngineer(3, "Carol", "carol@gmail.com", List.of("JavaScript", "React", "Node.js")),
                new SoftwareEngineer(4, "Dave", "dave@gmail.com", List.of("C#", ".NET", "Azure"))
        );
    }


}
