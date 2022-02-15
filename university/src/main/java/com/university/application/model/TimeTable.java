package com.university.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.view.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "timetable")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.TimeTableView.class)
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView(Views.TimeTableView.class)
    private Date date;
    @ManyToOne
    @JoinTable(name = "group_timeTables", joinColumns = @JoinColumn(name = "timeTable_id"),
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    @JsonView(Views.TimeTableView.class)
    private Group group;
    @ManyToMany
    @JsonView(Views.TimeTableView.class)
    private List<Plan> plans;


    public TimeTable(Date date, List<Plan> plans) {
        this.date = date;
        this.plans = plans;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
