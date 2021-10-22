package ru.nsu.fit.oop.Task_1_3_2;

import java.util.List;

public class ReportCard {

    private Student student;
    private List<Course> courses;

    /**
     * Calculates average grade for all semesters
     *
     * @return average grade
     */
    public double averageGrade() {
        int grades = 0;
        int count = 0;
        for (Course course : courses) {
            if (course.isGraded()) {
                grades += course.getGrade();
                count++;
            }
        }

        return 1.0 * grades / count;
    }

    /**
     * Calculates average grade for given semester
     *
     * @param semester semester to calculate average grade
     * @return average grade for given semester
     */
    public double averageGrade(Course.Semester semester) {
        int grades = 0;
        int count = 0;
        for (Course course : courses) {
            if (course.isGraded() && course.getSemester() == semester) {
                grades += course.getGrade();
                count++;
            }
        }

        return 1.0 * grades / count;
    }

    /**
     * Determines if honors degree is available
     *
     * @return boolean value
     */
    public boolean isHonorsDegreeAvailable() {
        for (Course course : courses) {
            if (course.isGraded()) {
                if (course.getGrade() != 5) return false;
            } else {
                if (course.getGrade() != 1) return false;
            }
        }

        return true;
    }

    /**
     * Determines if student is getting increased scholarship
     *
     * @param semester semester from which student is getting increased scholarship
     * @return boolean valye
     */
    public boolean isGettingIncreasedScholarship(Course.Semester semester) {
        if (semester == Course.Semester.FIRST_SEMESTER) return false;

        for (Course course : courses) {
            if (course.getSemester() == semester ||
                    course.getSemester() == semester.prevSemester()) {
                if (course.isGraded()) {
                    if (course.getGrade() != 5) return false;
                } else {
                    if (course.getGrade() != 1) return false;
                }
            }
        }

        return true;
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
