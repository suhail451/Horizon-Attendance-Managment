package com.example.comeback.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
@Entity
@Table(name="Students")


public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int semester;

    public Student() {
    }

    public Student(String name, int semester) {
        this.name = name;
        this.semester = semester;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

}
