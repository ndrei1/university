package com.university.application.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.enums.Faculty;
import com.university.application.model.enums.GroupType;
import com.university.application.model.enums.Speciality;
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
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.GroupView.class)
    private int id;
    @JsonView(Views.GroupNumber.class)
    private int number;
    @Enumerated(EnumType.STRING)
    @JsonView(Views.GroupView.class)
    private Speciality speciality;
    @Enumerated(EnumType.STRING)
    @JsonView(Views.GroupView.class)
    private Faculty faculty;
    @Enumerated(EnumType.STRING)
    @JsonView(Views.GroupView.class)
    private GroupType groupType;
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "group")
    private List<TimeTable> timeTables;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "group")
    private List<Student> studentList;

    public Group(int number, Speciality speciality, Faculty faculty, GroupType groupType, List<TimeTable> timeTables) {
        this.number = number;
        this.speciality = speciality;
        this.faculty = faculty;
        this.groupType = groupType;
        this.timeTables = timeTables;
    }
}
