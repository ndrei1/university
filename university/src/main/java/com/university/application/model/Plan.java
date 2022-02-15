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
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.PlanView.class)
    private int id;
    @ManyToOne
    @JsonView({Views.PlanView.class, Views.ClassroomNumber.class})
    private Classroom classroom;
    @ManyToOne
    @JsonView({Views.PlanView.class, Views.LessonNameAndType.class})
    private Lesson lesson;
    @ManyToOne
    @JsonView({Views.PlanView.class, Views.TeacherName.class})
    private Teacher teacher;
    @ManyToMany(mappedBy = "plans")
    private List<TimeTable> timeTables;

    public Plan(Classroom classroom, Lesson lesson, Teacher teacher) {
        this.classroom = classroom;
        this.lesson = lesson;
        this.teacher = teacher;
    }
}
