package com.university.application.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.view.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.TeacherView.class)
    private int id;
    @JsonView(Views.TeacherName.class)
    private String name;
    @JsonView(Views.TeacherView.class)
    private int age;
    @JsonView(Views.TeacherView.class)
    private int experience;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Plan> plan;

    public Teacher(String name, int age, int experience) {
        this.name = name;
        this.age = age;
        this.experience = experience;
    }
}
