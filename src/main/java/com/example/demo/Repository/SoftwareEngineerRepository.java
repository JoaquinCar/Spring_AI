package com.example.demo.Repository;

import com.example.demo.Entity.SoftwareEngineer;
import com.example.demo.Entity.Skill;
import com.example.demo.mapper.EngineerSkillProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Long> {

    // Method to find engineers by name (case-insensitive)
    Optional<List<SoftwareEngineer> > findByNameContainingIgnoreCase(String name);

    // Method to find engineers by skill
    @Query("SELECT e FROM SoftwareEngineer e JOIN e.skills s WHERE s.name = :skillName")
    List<SoftwareEngineer> findBySkillName(@Param("skillName") String skillName);

    // Method to find engineers with their skills using interface projection
    @Query(value = "SELECT se.id AS engineer_id, se.name AS engineer_name, se.email, " +
                   "s.id AS skill_id, s.name AS skill_name " +
                   "FROM software_engineers se " +
                   "JOIN engineer_skills es ON se.id = es.engineer_id " +
                   "JOIN skills s ON es.skill_id = s.id " +
                   "ORDER BY se.id, s.id", nativeQuery = true)
    List<EngineerSkillProjection> findEngineersWithSkills();
}
