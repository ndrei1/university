package com.university.application.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.Lesson;
import com.university.application.model.view.Views;
import com.university.application.service.LessonService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    @JsonView(Views.LessonView.class)
    public List<Lesson> getAll() {
        return lessonService.getAllLesson();
    }

    @GetMapping("{id}")
    @JsonView(Views.LessonView.class)
    public Lesson get(@PathVariable("id") Lesson lesson) {
        return lesson;
    }

    @PostMapping
    public Lesson add(@RequestBody Lesson lesson) {
        lessonService.saveLesson(lesson);
        return lesson;
    }

    @PutMapping("{id}")
    public Lesson update(@RequestBody Lesson lesson, @PathVariable("id") Lesson lessonFromDb) {
        BeanUtils.copyProperties(lesson, lessonFromDb, "id");
        lessonService.saveLesson(lessonFromDb);
        return lessonFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Lesson lesson) {
        lessonService.deleteLesson(lesson);
    }
}
