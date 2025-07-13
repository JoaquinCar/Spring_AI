package com.example.demo.mapper;
import com.example.demo.Entity.SoftwareEngineer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SkillMapper.class})
public interface SoftwareEngineerMapper {
    // De Entity a DTO (no necesitamos ignorar email porque no existe en el DTO)
    SoftwareEngineerDTO toDto(SoftwareEngineer engineer);

    // Lista de entidades a lista de DTOs
    List<SoftwareEngineerDTO> toDtoList(List<SoftwareEngineer> engineers);

    // De DTO a Entity (no incluimos el email en el DTO, así que será null)
    SoftwareEngineer toEntity(SoftwareEngineerDTO dto);

}
