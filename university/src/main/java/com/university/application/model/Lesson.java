package com.university.application.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.enums.LessonType;
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
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.LessonView.class)
    private int id;
    @JsonView(Views.LessonNameAndType.class)
    private String name;
    @JsonView(Views.LessonNameAndType.class)
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.REMOVE)
    private List<Plan> plan;

    public Lesson(String name, LessonType lessonType) {
        this.name = name;
        this.lessonType = lessonType;
    }
}
