package com.example.demo;

import com.example.demo.Entity.SoftwareEngineer;
import com.example.demo.mapper.EngineerSkillProjection;
import com.example.demo.mapper.SoftwareEngineerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/software-engineers")
public class SoftwareEngineerController {
    // This class will handle HTTP requests related to Software Engineers
    // You can define methods here to handle GET, POST, PUT, DELETE requests
    // For example, you might have methods like getAllEngineers(), getEngineerById(), createEngineer(), etc.

    private final SoftwareEngineerService service;


    public SoftwareEngineerController(SoftwareEngineerService service) {
        this.service = service;
    }

    // Endpoint público que devuelve DTOs sin correos
    @GetMapping( "/all")
    public List<SoftwareEngineerDTO> getAllEngineers() {
        return service.findAllDTOs();
    }

    @GetMapping("/engineer/{id}")
    public SoftwareEngineerDTO getEngineerById(@PathVariable Integer id) {
        return service.findDTOById(id)
                .orElseThrow(() -> new RuntimeException("Engineer not found with id: " + id));
    }

    @PostMapping("/create")
    public SoftwareEngineerDTO createEngineer(@RequestBody SoftwareEngineerDTO engineerDTO) {
        return service.save(engineerDTO);
    }

    @GetMapping("/search/{name}")
    public Optional<List<SoftwareEngineerDTO>> findByName(@PathVariable String name) {
        return Optional.ofNullable(service.findDTOsByNameContainingIgnoreCase(name).orElseThrow(() -> new RuntimeException("Engineer not found with name: " + name)));
    }

    @GetMapping("/skill/{skillName}")
    public Optional<List<SoftwareEngineerDTO>> findBySkillName(@PathVariable String skillName) {
        return service.findDTOsBySkillName(skillName);
    }

    @GetMapping("/engineers-with-skills")
    public List<EngineerSkillProjection> getEngineersWithSkills() {
        return service.findEngineersWithSkills();
    }

}
