package com.university.application.service;

import com.university.application.model.Lesson;
import com.university.application.repository.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {LessonService.class})
@RunWith(SpringRunner.class)
class LessonServiceTest {

    @Autowired
    private LessonService lessonService;

    @MockBean
    private LessonRepository lessonRepository;

    @MockBean
    private PlanService planService;

    @Test
    void getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLesson();
        Mockito.verify(lessonRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteLesson() {
        Lesson lesson = new Lesson();
        lessonService.deleteLesson(lesson);
        Mockito.verify(lessonRepository, Mockito.times(1)).delete(lesson);
        Mockito.verify(planService, Mockito.times(1)).deleteAllPlans(lesson.getPlan());
    }

    @Test
    void saveLesson() {
        Lesson lesson = new Lesson();
        lessonService.saveLesson(lesson);
        Mockito.verify(lessonRepository, Mockito.times(1)).save(lesson);
    }

    @Test
    void saveAllLesson() {
        List<Lesson> lessons = new ArrayList<>();
        lessonService.saveAllLesson(lessons);
        Mockito.verify(lessonRepository, Mockito.times(1)).saveAll(lessons);
    }
}