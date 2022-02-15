package com.university.application.service;

import com.university.application.model.Teacher;
import com.university.application.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {TeacherService.class})
@RunWith(SpringRunner.class)
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @MockBean
    private PlanService planService;

    @Test
    void getAllTeacher() {
        List<Teacher> teachers = teacherService.getAllTeacher();
        Mockito.verify(teacherRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteTeacher() {
        Teacher teacher = new Teacher();
        teacherService.deleteTeacher(teacher);
        Mockito.verify(teacherRepository, Mockito.times(1)).delete(teacher);
        Mockito.verify(planService, Mockito.times(1)).deleteAllPlans(teacher.getPlan());
    }

    @Test
    void saveTeacher() {
        Teacher teacher = new Teacher();
        teacherService.saveTeacher(teacher);
        Mockito.verify(teacherRepository, Mockito.times(1)).save(teacher);
    }

    @Test
    void saveAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherService.saveAllTeachers(teachers);
        Mockito.verify(teacherRepository, Mockito.times(1)).saveAll(teachers);
    }
}