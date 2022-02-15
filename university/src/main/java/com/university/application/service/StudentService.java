package com.university.application.service;

import com.university.application.model.Group;
import com.university.application.model.Plan;
import com.university.application.model.Student;
import com.university.application.model.TimeTable;
import com.university.application.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Plan> getTimeTable(int studentId, String requireDate) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Group group = studentRepository.getById(studentId).getGroup();
        for (TimeTable timetable : group.getTimeTables()) {
            String date = format.format(timetable.getDate());
            if (date.equals(requireDate)) {
                return timetable.getPlans();
            }
        }
        return Collections.emptyList();
    }

    public void saveAllStudents(List<Student> students) {
        studentRepository.saveAll(students);
    }

}
