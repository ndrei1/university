package com.university.application.model.view;

public class Views {
    public interface StudentView extends GroupNumber{}
    public interface GroupView extends GroupNumber{}
    public interface GroupNumber {}
    public interface PlanView extends ClassroomNumber,TeacherName,LessonNameAndType{}
    public interface TeacherView extends TeacherName{}
    public interface ClassroomView extends ClassroomNumber {}
    public interface TimeTableView extends GroupNumber,ClassroomNumber,TeacherName,LessonNameAndType{}
    public interface LessonView extends LessonNameAndType{}
    public interface ClassroomNumber {}
    public interface TeacherName {}
    public interface LessonNameAndType {}
    public interface StudentsTimeTable extends ClassroomNumber,TeacherName,LessonNameAndType {}
}
