package com.university.application.service;

import com.university.application.model.Classroom;
import com.university.application.repository.ClassroomRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final PlanService planService;

    public ClassroomService(ClassroomRepository classroomRepository, PlanService planService) {
        this.classroomRepository = classroomRepository;
        this.planService = planService;
    }

    public List<Classroom> getAllClassroom() {
        return classroomRepository.findAll();
    }

    public void deleteClassroom(Classroom classroom) {
        planService.deleteAllPlans(classroom.getPlan());
        classroomRepository.delete(classroom);
    }

    public void saveClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }


    public void saveAllClassrooms(List<Classroom> classrooms) {
        classroomRepository.saveAll(classrooms);
    }
}
