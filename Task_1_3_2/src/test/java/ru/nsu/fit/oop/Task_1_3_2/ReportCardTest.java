package ru.nsu.fit.oop.Task_1_3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReportCardTest {
    private final ReportCard reportCard = new ReportCard();

    @BeforeEach
    public void beforeReportCardTests() {
        Student student = new Student("Рамиль", "Салахов", "Радикович");

        List<Course> courses = new ArrayList<>();
        courses.add(
                new Course(
                        "Введение в алгебру и анализ",
                        Course.Expertise.OPK_1,
                        Course.CertificationType.EXAM,
                        new Teacher("Владимир", "Васкевич", "Леонтьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2021, Calendar.JANUARY, 11),
                        5
                )
        );
        courses.add(
                new Course(
                        "Иностранный язык",
                        Course.Expertise.UK_4,
                        Course.CertificationType.TEST,
                        new Teacher("Ольга", "Хоцкина", "Валерьевна"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2021, Calendar.FEBRUARY, 2),
                        1
                )
        );
        courses.add(
                new Course(
                        "Физическая культура и спорт (элективная дисциплина)",
                        Course.Expertise.UK_7,
                        Course.CertificationType.TEST,
                        new Teacher("Григорий", "Опарин", "Андреевич"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 25),
                        1
                )
        );
        courses.add(
                new Course(
                        "Введение в дискретную математику и математическую логику",
                        Course.Expertise.OPK_1,
                        Course.CertificationType.EXAM,
                        new Teacher("Дмитрий", "Власов", "Юрьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2021, Calendar.JANUARY, 18),
                        5
                )
        );
        courses.add(
                new Course(
                        "История",
                        Course.Expertise.UK_5,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Рената", "Оплаканская", "Валерьевна"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 28),
                        4
                )
        );
        courses.add(
                new Course(
                        "Декларативное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Владимир", "Власов", "Николаевич"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 29),
                        5
                )
        );
        courses.add(
                new Course(
                        "Основы культуры речи",
                        Course.Expertise.UK_4,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Татьяна", "Заворина", "Ивановна"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 28),
                        5
                )
        );
        courses.add(
                new Course(
                        "Императивное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Татьяна", "Нестеренко", "Викторовна"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 25),
                        4
                )
        );
        courses.add(
                new Course(
                        "Физическая культура и спорт",
                        Course.Expertise.UK_7,
                        Course.CertificationType.TEST,
                        new Teacher("Григорий", "Опарин", "Андреевич"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 25),
                        1
                )
        );
        courses.add(
                new Course(
                        "Декларативное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.TEST,
                        new Teacher("Анатолий", "Ананько", "Григорьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 23),
                        1
                )
        );

        reportCard.setStudent(student);
        reportCard.setCourses(courses);
    }

    @Test
    public void averageGradeTest() {
        double expAvgGrade = 28.0 / 6;
        double avgGrade = reportCard.averageGrade();

        assertEquals(expAvgGrade, avgGrade);
    }

    @Test
    public void honorsDegreeTest() {
        boolean result = reportCard.isHonorsDegreeAvailable();
        assertFalse(result);
    }

    @Test
    public void increasedScholarshipTest() {
        boolean result = reportCard.isGettingIncreasedScholarship(Course.Semester.FIRST_SEMESTER);
        assertFalse(result);
    }
}
