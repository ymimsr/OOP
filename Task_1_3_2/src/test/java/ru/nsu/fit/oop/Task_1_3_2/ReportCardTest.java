package ru.nsu.fit.oop.Task_1_3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportCardTest {

    private final ReportCard notExcellentReportCard = new ReportCard();
    private final ReportCard excellentReportCard = new ReportCard();

    @BeforeEach
    public void beforeReportCardTests() {
        Student notExcellentStudent = new Student("Рамиль", "Салахов", "Радикович");
        Student excellentStudent = new Student("Точно", "Не", "Рамиль");

        List<Course> notExcellentCourses = new ArrayList<>();
        notExcellentCourses.add(
                new Course(
                        "Введение в алгебру и анализ",
                        Course.Expertise.OPK_1,
                        Course.CertificationType.EXAM,
                        new Teacher("Владимир", "Васкевич", "Леонтьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2021, Month.JANUARY, 11),
                        5
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Иностранный язык",
                        Course.Expertise.UK_4,
                        Course.CertificationType.TEST,
                        new Teacher("Ольга", "Хоцкина", "Валерьевна"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2021, Month.FEBRUARY, 2),
                        1
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Физическая культура и спорт (элективная дисциплина)",
                        Course.Expertise.UK_7,
                        Course.CertificationType.TEST,
                        new Teacher("Григорий", "Опарин", "Андреевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 25),
                        1
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Введение в дискретную математику и математическую логику",
                        Course.Expertise.OPK_1,
                        Course.CertificationType.EXAM,
                        new Teacher("Дмитрий", "Власов", "Юрьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2021, Month.JANUARY, 18),
                        5
                )
        );
        notExcellentCourses.add(
                new Course(
                        "История",
                        Course.Expertise.UK_5,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Рената", "Оплаканская", "Валерьевна"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 28),
                        4
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Декларативное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Владимир", "Власов", "Николаевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 29),
                        5
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Основы культуры речи",
                        Course.Expertise.UK_4,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Татьяна", "Заворина", "Ивановна"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 28),
                        5
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Императивное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Татьяна", "Нестеренко", "Викторовна"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 25),
                        4
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Физическая культура и спорт",
                        Course.Expertise.UK_7,
                        Course.CertificationType.TEST,
                        new Teacher("Григорий", "Опарин", "Андреевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 25),
                        1
                )
        );
        notExcellentCourses.add(
                new Course(
                        "Декларативное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.TEST,
                        new Teacher("Анатолий", "Ананько", "Григорьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 23),
                        1
                )
        );

        List<Course> excellentCourses = new ArrayList<>();
        excellentCourses.add(
                new Course(
                        "Введение в алгебру и анализ",
                        Course.Expertise.OPK_1,
                        Course.CertificationType.EXAM,
                        new Teacher("Владимир", "Васкевич", "Леонтьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2021, Month.JANUARY, 11),
                        5
                )
        );
        excellentCourses.add(
                new Course(
                        "Иностранный язык",
                        Course.Expertise.UK_4,
                        Course.CertificationType.TEST,
                        new Teacher("Ольга", "Хоцкина", "Валерьевна"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2021, Month.FEBRUARY, 2),
                        1
                )
        );
        excellentCourses.add(
                new Course(
                        "Физическая культура и спорт (элективная дисциплина)",
                        Course.Expertise.UK_7,
                        Course.CertificationType.TEST,
                        new Teacher("Григорий", "Опарин", "Андреевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 25),
                        1
                )
        );
        excellentCourses.add(
                new Course(
                        "Введение в дискретную математику и математическую логику",
                        Course.Expertise.OPK_1,
                        Course.CertificationType.EXAM,
                        new Teacher("Дмитрий", "Власов", "Юрьевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2021, Month.JANUARY, 18),
                        5
                )
        );
        excellentCourses.add(
                new Course(
                        "История",
                        Course.Expertise.UK_5,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Рената", "Оплаканская", "Валерьевна"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 28),
                        5
                )
        );
        excellentCourses.add(
                new Course(
                        "Декларативное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Владимир", "Власов", "Николаевич"),
                        Course.Semester.FIRST_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 29),
                        5
                )
        );
        excellentCourses.add(
                new Course(
                        "Основы культуры речи",
                        Course.Expertise.UK_4,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Татьяна", "Заворина", "Ивановна"),
                        Course.Semester.SECOND_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 28),
                        5
                )
        );
        excellentCourses.add(
                new Course(
                        "Императивное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.GRADED_TEST,
                        new Teacher("Татьяна", "Нестеренко", "Викторовна"),
                        Course.Semester.SECOND_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 25),
                        5
                )
        );
        excellentCourses.add(
                new Course(
                        "Физическая культура и спорт",
                        Course.Expertise.UK_7,
                        Course.CertificationType.TEST,
                        new Teacher("Григорий", "Опарин", "Андреевич"),
                        Course.Semester.SECOND_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 25),
                        1
                )
        );
        excellentCourses.add(
                new Course(
                        "Декларативное программирование",
                        Course.Expertise.OPK_8,
                        Course.CertificationType.TEST,
                        new Teacher("Анатолий", "Ананько", "Григорьевич"),
                        Course.Semester.SECOND_SEMESTER,
                        LocalDate.of(2020, Month.DECEMBER, 23),
                        1
                )
        );

        notExcellentReportCard.setStudent(notExcellentStudent);
        notExcellentReportCard.setCourses(notExcellentCourses);

        excellentReportCard.setStudent(excellentStudent);
        excellentReportCard.setCourses(excellentCourses);
    }

    @Test
    public void averageGradeTest() {
        double expNotExcAvgGrade = 28.0 / 6;
        double notExcAvgGrade = notExcellentReportCard.averageGrade();

        double expExcAvgGrade = 5;
        double excAvgGrade = excellentReportCard.averageGrade();

        assertEquals(expNotExcAvgGrade, notExcAvgGrade);
        assertEquals(expExcAvgGrade, excAvgGrade);
    }

    @Test
    public void honorsDegreeTest() {
        boolean notExcResult = notExcellentReportCard.isHonorsDegreeAvailable();
        boolean excResult = excellentReportCard.isHonorsDegreeAvailable();

        assertTrue(excResult);
        assertFalse(notExcResult);
    }

    @Test
    public void increasedScholarshipTest() {
        boolean notExcResult = notExcellentReportCard.isGettingIncreasedScholarship(Course.Semester.FIRST_SEMESTER);
        boolean excResult = excellentReportCard.isGettingIncreasedScholarship(Course.Semester.SECOND_SEMESTER);

        assertTrue(excResult);
        assertFalse(notExcResult);
    }
}
