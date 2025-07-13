package com.example.demo.Repository;

import com.example.demo.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    // JpaRepository proporciona operaciones CRUD básicas:
    // save, findById, findAll, deleteById, etc.

    // También puedes agregar métodos personalizados si los necesitas, como:
    // Skill findByName(String name);
}