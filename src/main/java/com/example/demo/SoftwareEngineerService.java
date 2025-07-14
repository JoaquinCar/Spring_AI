package com.example.demo;

import com.example.demo.Entity.Skill;
import com.example.demo.Entity.SoftwareEngineer;
import com.example.demo.Repository.SoftwareEngineerRepository;
import com.example.demo.mapper.EngineerSkillProjection;
import com.example.demo.mapper.SoftwareEngineerDTO;
import com.example.demo.mapper.SoftwareEngineerMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository repository;
    private final SoftwareEngineerMapper mapper;

    private final AIService aiService;


    public SoftwareEngineerService(SoftwareEngineerRepository repository, @Qualifier("softwareEngineerMapperImpl") SoftwareEngineerMapper mapper, AIService aiService) {
        this.repository = repository;
        this.mapper = mapper;
        this.aiService = aiService;
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
    public List<Map<String, Object>> findByNameWithAIAnalysis(String name) {
        List<Map<String, Object>> results = new ArrayList<>();

        Optional<List<SoftwareEngineer>> engineers = repository.findByNameContainingIgnoreCase(name);
        if (engineers.isPresent()) {
            for (SoftwareEngineer engineer : engineers.get()) {
                // Extraemos las habilidades del ingeniero
                List<String> skillNames = engineer.getSkills().stream()
                        .map(Skill::getName)
                        .toList();

                // Formamos el prompt con el nombre y las habilidades
                String prompt = """
                    Based on the tech stack of the software engineer %s with skills: %s,
                    please provide an analysis of their skills and recommendations for improvement.
                    The analysis should be concise and focus on the strengths and weaknesses of the engineer.
                    """.formatted(engineer.getName(), String.join(", ", skillNames));

                // Llamamos al servicio de IA
                String aiAnalysis = aiService.chat(prompt);

                // Creamos un mapa con los resultados
                Map<String, Object> result = new HashMap<>();
                result.put("engineer", engineer);
                result.put("analysis", aiAnalysis);

                results.add(result);
            }
        }

        return results;
    }

}
