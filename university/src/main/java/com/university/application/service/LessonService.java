package com.university.application.service;

import com.university.application.model.Lesson;
import com.university.application.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final PlanService planService;

    public LessonService(LessonRepository lessonRepository, PlanService planService) {
        this.lessonRepository = lessonRepository;
        this.planService = planService;
    }

    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    public void deleteLesson(Lesson lesson) {
        planService.deleteAllPlans(lesson.getPlan());
        lessonRepository.delete(lesson);
    }

    public void saveLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void saveAllLesson(List<Lesson> lessons) {
        lessonRepository.saveAll(lessons);
    }

}