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
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ClassroomView.class)
    private int id;
    @JsonView(Views.ClassroomView.class)
    private int quantityOfSeats;
    @JsonView(Views.ClassroomNumber.class)
    private int number;
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.REMOVE)
    private List<Plan> plan;

    public Classroom(int quantityOfSeats, int number) {
        this.quantityOfSeats = quantityOfSeats;
        this.number = number;
    }
}
