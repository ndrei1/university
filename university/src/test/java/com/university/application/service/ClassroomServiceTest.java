package com.university.application.service;

import com.university.application.model.Classroom;
import com.university.application.repository.ClassroomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = {ClassroomService.class})
@RunWith(SpringRunner.class)
class ClassroomServiceTest {

    @Autowired
    private ClassroomService classroomService;

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private PlanService planService;


    @Test
    void getAllClassroom() {
        List<Classroom> classrooms = classroomService.getAllClassroom();
        Mockito.verify(classroomRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteClassroom() {
        Classroom classroom = new Classroom();
        classroomService.deleteClassroom(classroom);
        Mockito.verify(classroomRepository, Mockito.times(1)).delete(classroom);
        Mockito.verify(planService, Mockito.times(1)).deleteAllPlans(classroom.getPlan());
    }

    @Test
    void saveClassroom() {
        Classroom classroom = new Classroom();
        classroomService.saveClassroom(classroom);
        Mockito.verify(classroomRepository, Mockito.times(1)).save(classroom);
    }
}