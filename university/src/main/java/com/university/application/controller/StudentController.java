package com.university.application.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.Plan;
import com.university.application.model.Student;
import com.university.application.model.view.Views;
import com.university.application.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}/{date}")
    @JsonView(Views.StudentsTimeTable.class)
    public List<Plan> get(@PathVariable int id, @PathVariable String date) {
        return studentService.getTimeTable(id, date);
    }

    @GetMapping
    @JsonView(Views.StudentView.class)
    public List<Student> getAll() {
        return studentService.getAllStudent();
    }

    @GetMapping("{id}")
    @JsonView(Views.StudentView.class)
    public Student get(@PathVariable("id") Student student) {
        return student;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        studentService.saveStudent(student);
        return student;
    }

    @PutMapping("{id}")
    public Student update(@RequestBody Student student, @PathVariable("id") Student studentFromDb) {
        BeanUtils.copyProperties(student, studentFromDb, "id");
        studentService.saveStudent(studentFromDb);
        return studentFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Student student) {
        studentService.deleteStudent(student);
    }
}
