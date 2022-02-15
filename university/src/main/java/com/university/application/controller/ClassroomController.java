package com.university.application.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.Classroom;
import com.university.application.model.view.Views;
import com.university.application.service.ClassroomService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    @JsonView(Views.ClassroomView.class)
    public List<Classroom> getAll() {
        return classroomService.getAllClassroom();
    }

    @GetMapping("{id}")
    @JsonView(Views.ClassroomView.class)
    public Classroom get(@PathVariable("id") Classroom classroom) {
        return classroom;
    }

    @PostMapping
    public Classroom add(@RequestBody Classroom classroom) {
        classroomService.saveClassroom(classroom);
        return classroom;
    }

    @PutMapping("{id}")
    public Classroom update(@RequestBody Classroom classroom, @PathVariable("id") Classroom classroomFromDb) {
        BeanUtils.copyProperties(classroom, classroomFromDb, "id");
        classroomService.saveClassroom(classroomFromDb);
        return classroomFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Classroom classroom) {
        classroomService.deleteClassroom(classroom);
    }


}
