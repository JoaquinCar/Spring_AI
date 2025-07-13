package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private List<SoftwareEngineer> engineers;

    // Constructores, getters, setters...

    public Skill() {}

    public Skill(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SoftwareEngineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(List<SoftwareEngineer> engineers) {
        this.engineers = engineers;
    }

    // Otros métodos...
}
