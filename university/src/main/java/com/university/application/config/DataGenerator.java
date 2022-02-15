package com.university.application.config;

import com.university.application.model.*;
import com.university.application.model.enums.Faculty;
import com.university.application.model.enums.GroupType;
import com.university.application.model.enums.LessonType;
import com.university.application.model.enums.Speciality;
import com.university.application.service.*;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DataGenerator {

    private final ClassroomService classroomService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final LessonService lessonService;
    private final PlanService planService;

    List<String> names = Arrays.asList("Colby", "Ignacio", "Winston", "Gavin",
            "Cadence", "Christina", "Stella", "Yesenia",
            "Ophelia", "Lauryn", "Nellie", "Kayla", "Omega", "Veronika", "Beatrix", "Tallulah");
    List<String> surNames = Arrays.asList(
            "Ross", "Hayes", "Jones", "Griffin", "Lewis", "Rodriguez", "Johnson", "Lee",
            "Cooper", "Perez", "Brown", "White");

    public DataGenerator(ClassroomService classroomService, GroupService groupService, TeacherService teacherService,
                         StudentService studentService, LessonService lessonService,
                         PlanService planService) {
        this.classroomService = classroomService;
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.lessonService = lessonService;
        this.planService = planService;
    }

    public void dataGenerate() {
        List<Classroom> classrooms = classroomGenerate();
        List<Teacher> teachers = teacherGenerate();
        List<Lesson> lessons = lessonGenerate();
        List<Plan> plans = plansGenerate(teachers, classrooms, lessons);
        List<Group> groups = groupGenerate(plans);
        List<Student> students = studentGenerate(groups);

        classroomService.saveAllClassrooms(classrooms);
        teacherService.saveAllTeachers(teachers);
        lessonService.saveAllLesson(lessons);
        planService.saveAllPlan(plans);
        groupService.saveAllGroup(groups);
        studentService.saveAllStudents(students);
    }


    private List<Classroom> classroomGenerate() {
        List<Classroom> classrooms = new ArrayList<>();
        List<Classroom> classroomLectureList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                classroomLectureList.add(new Classroom(120, i * 100 + j));
            }
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 6; j <= 10; j++) {
                classrooms.add(new Classroom(30, i * 100 + j));
            }
        }
        classrooms.addAll(classroomLectureList);
        return classrooms;
    }

    private List<Teacher> teacherGenerate() {
        List<Teacher> teachers = new ArrayList<>();
        for (int j = 0; j <= 20; j++) {
            Random random = new Random();
            teachers.add(new Teacher(names.get(random.nextInt(names.size()))
                    + " " + surNames.get(random.nextInt(surNames.size())), 20 + j, (int) (4 + (Math.random() * 6))));
        }
        return teachers;
    }

    private List<Plan> plansGenerate(List<Teacher> teachers, List<Classroom> classrooms, List<Lesson> lessons) {
        List<Plan> plans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            Teacher teacher = teachers.get(random.nextInt(teachers.size()));
            Classroom classroom = classrooms.get(random.nextInt(classrooms.size()));
            Lesson lesson = lessons.get(random.nextInt(lessons.size()));
            plans.add(new Plan(classroom, lesson, teacher));
        }
        return plans;
    }

    private List<Student> studentGenerate(List<Group> groups) {
        List<Student> students = new ArrayList<>();
        for (Group group : groups) {
            for (int j = 0; j <= 30; j++) {
                Random random = new Random();
                students.add(new Student(names.get(random.nextInt(names.size()))
                        + " " + surNames.get(random.nextInt(surNames.size())),
                        18 + (int) (Math.random() * 12), 4 + (Math.random() * 6), group));
            }
        }
        return students;
    }


    private List<Lesson> lessonGenerate() {
        List<Lesson> lessons = new ArrayList<>();
        List<String> lessonsName = Arrays.asList(
                "Fundamentals of algorithms and programming",
                "Fundamentals of Object-Oriented Programming",
                "Database",
                "Fundamentals of Networking",
                "Network Application Programming",
                "Information systems design",
                "Distributed information processing systems",
                "Corporate information systems",
                "Fundamentals of information security",
                "Metrology, standardization and certification in information technology",
                "Marketing disciplines",
                "Marketing Basics",
                "Information technology in marketing",
                "Product policy and brand management",
                "Marketing research",
                "Marketing communications",
                "Distribution channels and marketing logistics",
                "Strategic Marketing",
                "Sociology",
                "Technologies of sales, business negotiations and presentations",
                "Mathematical Methods and Models for Making Marketing Decisions",
                "Industrial Marketing",
                "International marketing and foreign economic activity",
                "Consumer Behavior",
                "Industry Marketing",
                "Software product and service marketing",
                "Legal regulation of marketing activities",
                "Engineering computer graphics",
                "Human life safety",
                "Foreign language",
                "Theory of Probability and Mathematical Statistics",
                "General theory of statistics",
                "Mathematics",
                "Natural sciences and general professional disciplines",
                "Innovation management",
                "Financial mathematics and financial management",
                "Accounting",
                "Organization economics",
                "Macroeconomics",
                "Microeconomics"
        );
        for (String name : lessonsName) {
            lessons.add(new Lesson(name, LessonType.LECTURE));
        }
        for (int i = 0; i < lessonsName.size(); i += 4) {
            lessons.add(new Lesson(lessonsName.get(i), LessonType.PRACTICAL_WORK));
            lessons.add(new Lesson(lessonsName.get(i + 1), LessonType.LABORATORY_WORK));
            lessons.add(new Lesson(lessonsName.get(i + 2), LessonType.DIPLOMA_DESIGN));
            lessons.add(new Lesson(lessonsName.get(i + 3), LessonType.COURSE_DESIGN));
        }
        return lessons;
    }


    private List<TimeTable> timeTableGenerate(List<Plan> plans) {
        List<TimeTable> timeTablesInTheGroup = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int j = 0; j < 365; j++) {
            List<Plan> timeTableElementsInTheGroup = new ArrayList<>();
            Random random = new Random();
            for (int k = 0; k < 5; k++) {
                timeTableElementsInTheGroup.add(plans.get(random.nextInt(plans.size())));
            }
            calendar.add(Calendar.DATE, 1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(calendar.getTime());
            try {
                Date dateFromDb = formatter.parse(date);
                timeTablesInTheGroup.add(new TimeTable(dateFromDb, timeTableElementsInTheGroup));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return timeTablesInTheGroup;
    }

    private List<Group> groupGenerate(List<Plan> plans) {
        List<Group> groups = new ArrayList<>();
        Speciality[] specialities = Speciality.values();
        Faculty[] faculties = Faculty.values();
        GroupType[] groupTypes = GroupType.values();
        for (int i = 0; i < 10; i++) {
            List<TimeTable> timeTablesInTheGroup = timeTableGenerate(plans);
            Random random = new Random();
            int indexSpecialities = random.nextInt(specialities.length);
            int indexFaculties = random.nextInt(faculties.length);
            int indexGroupTypes = random.nextInt(groupTypes.length);
            Group group = new Group(100 + i,
                    specialities[indexSpecialities],
                    faculties[indexFaculties],
                    groupTypes[indexGroupTypes], timeTablesInTheGroup);
            for (TimeTable t : timeTablesInTheGroup) {
                t.setGroup(group);
            }
            groups.add(group);
        }
        return groups;
    }


}