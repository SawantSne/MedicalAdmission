package org.example.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Admission {

    public Admission() {

    }
    public Admission(int id, LocalDateTime dateOfAdmission, String name, Date birthDate, Sex sex, Category category, String sourceId) {
        this.id = id;
        this.dateOfAdmission = dateOfAdmission;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.category = category;
        this.sourceId = sourceId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(updatable = false)
    @CreationTimestamp
    LocalDateTime dateOfAdmission;

    @Column(nullable = false, updatable = false)
    private String sourceId;
    
    @Column(nullable = false)
    String name;
    
    @Column(nullable = false)
    Date birthDate;
    
    @Column(nullable = false)
    Sex sex;
    
    @Column(nullable = false)
    Category category;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
