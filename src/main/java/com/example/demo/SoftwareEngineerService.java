package com.example.demo;

import com.example.demo.Entity.SoftwareEngineer;
import com.example.demo.Repository.SoftwareEngineerRepository;
import com.example.demo.mapper.EngineerSkillProjection;
import com.example.demo.mapper.SoftwareEngineerDTO;
import com.example.demo.mapper.SoftwareEngineerMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository repository;
    private final SoftwareEngineerMapper mapper;


    public SoftwareEngineerService(SoftwareEngineerRepository repository, @Qualifier("softwareEngineerMapperImpl") SoftwareEngineerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Métodos existentes que devuelven entidades completas
    public List<SoftwareEngineer> findAll() {
        return repository.findAll();
    }

    public Optional<SoftwareEngineer> findById(Integer id) {
        return repository.findById(Long.valueOf(id));
    }

    // Nuevos métodos que devuelven DTOs sin correos electrónicos
    public List<SoftwareEngineerDTO> findAllDTOs() {
        return mapper.toDtoList(repository.findAll());
    }

    public Optional<SoftwareEngineerDTO> findDTOById(Integer id) {
        return repository.findById(Long.valueOf(id))
                .map(mapper::toDto);
    }

    // Método para guardar usando DTO
    public SoftwareEngineerDTO save(SoftwareEngineerDTO dto) {
        SoftwareEngineer entity = mapper.toEntity(dto);
        // Nota: tendrías que manejar el correo electrónico aquí si es necesario
        SoftwareEngineer saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public Optional<List<SoftwareEngineer>> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Optional<List<SoftwareEngineerDTO>> findDTOsByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .map(mapper::toDtoList);
    }

    public Optional<List<SoftwareEngineer>> findBySkillName(String skillName) {
        return Optional.ofNullable(repository.findBySkillName(skillName));
    }

    public Optional<List<SoftwareEngineerDTO>> findDTOsBySkillName(String skillName) {
        List<SoftwareEngineer> engineers = repository.findBySkillName(skillName);
        return Optional.ofNullable(engineers != null ? mapper.toDtoList(engineers) : null);
    }

    // Method to find engineers with their skills using interface projection
    public List<EngineerSkillProjection> findEngineersWithSkills() {
        return repository.findEngineersWithSkills();
    }
}
