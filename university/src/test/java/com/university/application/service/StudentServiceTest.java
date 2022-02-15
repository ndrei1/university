package com.university.application.service;

import com.university.application.model.Student;
import com.university.application.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = {StudentService.class})
@RunWith(SpringRunner.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;


    @Test
    void getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteStudent() {
        Student student = new Student();
        studentService.deleteStudent(student);
        Mockito.verify(studentRepository, Mockito.times(1)).delete(student);

    }

    @Test
    void saveStudent() {
        Student student = new Student();
        studentService.saveStudent(student);
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
    }

    @Test
    void saveAllStudents() {
        List<Student> students = new ArrayList<>();
        studentService.saveAllStudents(students);
        Mockito.verify(studentRepository, Mockito.times(1)).saveAll(students);
    }
}