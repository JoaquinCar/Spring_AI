package com.example.demo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<SoftwareEngineer> engineers;

    // Constructores, getters, setters...

    public Skill() {}

    public Skill(String name) {
        this.name = name;
    }

    // Otros métodos...
}
