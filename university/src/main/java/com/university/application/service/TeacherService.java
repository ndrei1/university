package com.university.application.service;

import com.university.application.model.Teacher;
import com.university.application.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PlanService planService;

    public TeacherService(TeacherRepository teacherRepository, PlanService planService) {
        this.teacherRepository = teacherRepository;
        this.planService = planService;
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public void deleteTeacher(Teacher teacher) {
        planService.deleteAllPlans(teacher.getPlan());
        teacherRepository.delete(teacher);
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void saveAllTeachers(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
    }

}

