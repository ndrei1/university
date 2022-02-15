package com.university.application.service;

import com.university.application.model.TimeTable;
import com.university.application.repository.TimeTableRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = {TimeTableService.class})
@RunWith(SpringRunner.class)
class TimeTableServiceTest {

    @Autowired
    private TimeTableService timeTableService;

    @MockBean
    private TimeTableRepository tableRepository;

    @Test
    void getAllTimeTable() {
        List<TimeTable> timeTables = timeTableService.getAllTimeTable();
        Mockito.verify(tableRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteTimeTable() {
        TimeTable timeTable = new TimeTable();
        timeTableService.deleteTimeTable(timeTable);
        Mockito.verify(tableRepository, Mockito.times(1)).delete(timeTable);
    }

    @Test
    void saveTimeTable() {
        TimeTable timeTable = new TimeTable();
        timeTableService.saveTimeTable(timeTable);
        Mockito.verify(tableRepository, Mockito.times(1)).save(timeTable);
    }
}