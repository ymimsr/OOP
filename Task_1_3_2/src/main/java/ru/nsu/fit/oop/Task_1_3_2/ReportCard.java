package ru.nsu.fit.oop.Task_1_3_2;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class ReportCard {

    private Student student;
    private List<Course> courses;

    /**
     * Calculates average grade for all semesters
     *
     * @return average grade
     */
    public double averageGrade() {
        OptionalDouble avgGrade = courses.stream()
                .filter(Course::isGraded)
                .mapToInt(Course::getGrade)
                .average();

        if (avgGrade.isEmpty()) throw new NullPointerException();

        return avgGrade.getAsDouble();
    }

    /**
     * Calculates average grade for given semester
     *
     * @param semester semester to calculate average grade
     * @return average grade for given semester
     */
    public double averageGrade(Course.Semester semester) {
        OptionalDouble avgGrade = courses.stream()
                .filter(course -> course.isGraded() && course.getSemester() == semester)
                .mapToInt(Course::getGrade)
                .average();

        if (avgGrade.isEmpty()) throw new NullPointerException();

        return avgGrade.getAsDouble();
    }

    /**
     * Determines if honors degree is available
     *
     * @return boolean value
     */
    public boolean isHonorsDegreeAvailable() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::isGraded, Collectors.averagingInt(Course::getGrade)))
                .entrySet().stream()
                .allMatch(average -> average.getKey() ? average.getValue() >= 4.75 : average.getValue() == 1)
                &&
                courses.stream().filter(Course::isGraded)
                        .allMatch(course -> course.getGrade() >= 4);
    }

    /**
     * Determines if student is getting increased scholarship
     *
     * @param semester semester from which student is getting increased scholarship
     * @return boolean valye
     */
    public boolean isGettingIncreasedScholarship(Course.Semester semester) {
        if (semester == Course.Semester.FIRST_SEMESTER) return false;

        return courses.stream()
                .filter(course -> course.getSemester() == semester || course.getSemester() == semester.prevSemester())
                .collect(Collectors.groupingBy(Course::isGraded, Collectors.averagingInt(Course::getGrade)))
                .entrySet().stream()
                .allMatch(average -> average.getKey() ? average.getValue() == 5 : average.getValue() == 1);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
