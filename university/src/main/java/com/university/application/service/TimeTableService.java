package com.university.application.service;

import com.university.application.model.TimeTable;
import com.university.application.repository.TimeTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;

    public TimeTableService(TimeTableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }

    public List<TimeTable> getAllTimeTable() {
        return timeTableRepository.findAll();
    }

    public void deleteTimeTable(TimeTable timeTable) {
        timeTableRepository.delete(timeTable);
    }

    public void saveTimeTable(TimeTable timeTable) {
        timeTableRepository.save(timeTable);
    }
}