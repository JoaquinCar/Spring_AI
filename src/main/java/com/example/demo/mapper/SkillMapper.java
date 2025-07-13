package com.example.demo.mapper;

import com.example.demo.Entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    // Map from Entity to DTO
    SkillDTO toDto(Skill skill);

    // Map from DTO to Entity
    Skill toEntity(SkillDTO dto);

    // Map a list of entities to a list of DTOs
    List<SkillDTO> toDtoList(List<Skill> skills);
}
