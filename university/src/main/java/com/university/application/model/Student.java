package com.university.application.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.view.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.StudentView.class)
    private int id;
    @JsonView(Views.StudentView.class)
    private String name;
    @JsonView(Views.StudentView.class)
    private int age;
    @JsonView(Views.StudentView.class)
    private double avgMark;
    @ManyToOne
    @JoinTable(name = "group_student",joinColumns =@JoinColumn(name = "student_id")
            ,inverseJoinColumns = {@JoinColumn(name = "group_id")})
    @JsonView(Views.StudentView.class)
    private Group group;

    public Student(String name, int age, double avgMark, Group group) {
        this.name = name;
        this.age = age;
        this.avgMark = avgMark;
        this.group = group;
    }
}
