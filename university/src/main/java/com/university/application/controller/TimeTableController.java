package com.university.application.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.TimeTable;
import com.university.application.model.view.Views;
import com.university.application.service.TimeTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("timeTables")
public class TimeTableController {

    private final TimeTableService timeTableService;

    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @GetMapping
    @JsonView(Views.TimeTableView.class)
    public List<TimeTable> getAll() {
        return timeTableService.getAllTimeTable();
    }

    @GetMapping("{id}")
    @JsonView(Views.TimeTableView.class)
    public TimeTable get(@PathVariable("id") TimeTable timeTable) {
        return timeTable;
    }

    @PostMapping
    public TimeTable add(@RequestBody TimeTable timeTable) {
        timeTableService.saveTimeTable(timeTable);
        return timeTable;
    }

    @PutMapping("{id}")
    public TimeTable update(@RequestBody TimeTable timeTable, @PathVariable("id") TimeTable timeTableFromDb) {
        BeanUtils.copyProperties(timeTable, timeTableFromDb, "id");
        timeTableService.saveTimeTable(timeTableFromDb);
        return timeTableFromDb;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") TimeTable timeTable) {
        timeTableService.deleteTimeTable(timeTable);
    }
}
