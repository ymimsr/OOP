package ru.nsu.fit.oop.Task_1_3_2;

import java.time.LocalDate;

public class Course {

    private final String title;
    private final Expertise expertise;
    private final CertificationType certificationType;
    private final Teacher teacher;
    private final Semester semester;
    private final LocalDate passDate;

    // if certificationType is TEST then grade is 1 (course is passed) or 0 (course is failed)
    // otherwise grade is 2, 3, 4, or 5
    private final int grade;

    public Course(
            String title,
            Expertise expertise,
            CertificationType certificationType,
            Teacher teacher, Semester semester,
            LocalDate passDate,
            int grade
    ) {
        this.title = title;
        this.expertise = expertise;
        this.certificationType = certificationType;
        this.teacher = teacher;
        this.semester = semester;
        this.passDate = passDate;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public boolean isGraded() {
        return certificationType == CertificationType.EXAM
                || certificationType == CertificationType.GRADED_TEST;
    }

    public Semester getSemester() {
        return semester;
    }

    public enum Expertise {

        OPK_1("ОПК-1"),
        OPK_8("ОПК-8"),
        UK_4("УК-4"),
        UK_5("УК-5"),
        UK_7("УК-7");

        private final String title;

        Expertise(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

    }

    public enum CertificationType {

        EXAM("Экзамен"),
        GRADED_TEST("Диффернцированный зачет"),
        TEST("Зачет");

        private final String title;

        CertificationType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum Semester {

        FIRST_SEMESTER,
        SECOND_SEMESTER,
        THIRD_SEMESTER,
        FOURTH_SEMESTER,
        FIFTH_SEMESTER,
        SIXTH_SEMESTER,
        SEVENTH_SEMESTER,
        EIGHT_SEMESTER;

        public Semester prevSemester() {
            return switch (this) {
                case FIRST_SEMESTER -> null;
                case SECOND_SEMESTER -> FIRST_SEMESTER;
                case THIRD_SEMESTER -> SECOND_SEMESTER;
                case FOURTH_SEMESTER -> THIRD_SEMESTER;
                case FIFTH_SEMESTER -> FOURTH_SEMESTER;
                case SIXTH_SEMESTER -> FIFTH_SEMESTER;
                case SEVENTH_SEMESTER -> SIXTH_SEMESTER;
                case EIGHT_SEMESTER -> SEVENTH_SEMESTER;
            };
        }
    }

}
