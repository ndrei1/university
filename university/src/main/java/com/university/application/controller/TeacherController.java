package com.university.application.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.Teacher;
import com.university.application.model.view.Views;
import com.university.application.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @JsonView(Views.TeacherView.class)
    public List<Teacher> getAll() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("{id}")
    @JsonView(Views.TeacherView.class)
    public Teacher get(@PathVariable("id") Teacher teacher) {
        return teacher;
    }

    @PostMapping
    public Teacher add(@RequestBody Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return teacher;
    }

    @PutMapping("{id}")
    public Teacher update(@RequestBody Teacher teacher, @PathVariable("id") Teacher teacherFromDb) {
        BeanUtils.copyProperties(teacher, teacherFromDb, "id");
        teacherService.saveTeacher(teacherFromDb);
        return teacherFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Teacher teacher) {
        teacherService.deleteTeacher(teacher);
    }

}
