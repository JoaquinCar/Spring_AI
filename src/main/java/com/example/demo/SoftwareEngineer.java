package com.example.demo;

import java.util.List;
import java.util.Objects;

public class SoftwareEngineer {
    private Integer Id;
    private String name;
    private String email;
    private List<String> skills;

    public SoftwareEngineer(Integer id, String name, String email, List<String> skills) {
        Id = id;
        this.name = name;
        this.email = email;
        this.skills = skills;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return Objects.equals(Id, that.Id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, email, skills);
    }
}
