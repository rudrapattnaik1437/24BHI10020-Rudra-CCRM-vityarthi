package edu.ccrm.util;

import edu.ccrm.domain.*;
import java.util.Comparator;

/**
 * Comparator utilities demonstrating lambda expressions and method references
 * Shows functional programming concepts in Java
 */
public class ComparatorUtils {
    
    // Lambda expression comparators
    public static final Comparator<Student> BY_NAME = 
        (s1, s2) -> s1.getName().getFullName().compareTo(s2.getName().getFullName());
    
    public static final Comparator<Student> BY_GPA = 
        (s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()); // Descending order
    
    public static final Comparator<Student> BY_REG_NO = 
        (s1, s2) -> s1.getRegNo().compareTo(s2.getRegNo());
    
    public static final Comparator<Student> BY_TOTAL_CREDITS = 
        (s1, s2) -> Integer.compare(s2.getTotalCredits(), s1.getTotalCredits()); // Descending
    
    // Method reference comparators
    public static final Comparator<Course> BY_COURSE_CODE = 
        Comparator.comparing(course -> course.getCode().getCode());
    
    public static final Comparator<Course> BY_TITLE = 
        Comparator.comparing(Course::getTitle);
    
    public static final Comparator<Course> BY_CREDITS = 
        Comparator.comparing(Course::getCredits).reversed();
    
    public static final Comparator<Course> BY_DEPARTMENT = 
        Comparator.comparing(Course::getDepartment);
    
    public static final Comparator<Course> BY_INSTRUCTOR = 
        Comparator.comparing(Course::getInstructor);
    
    // Composite comparators (chaining)
    public static final Comparator<Student> BY_STATUS_THEN_GPA = 
        Comparator.comparing((Student s) -> s.getStatus().toString())
                  .thenComparing(BY_GPA);
    
    public static final Comparator<Course> BY_DEPARTMENT_THEN_CODE = 
        BY_DEPARTMENT.thenComparing(BY_COURSE_CODE);
    
    // Grade comparators
    public static final Comparator<Grade> BY_GRADE_POINTS = 
        Comparator.comparing(Grade::getGradePoints).reversed();
    
    // Anonymous inner class example (older style, for demonstration)
    public static final Comparator<Student> BY_CREATION_DATE = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getCreatedDate().compareTo(s2.getCreatedDate());
        }
    };
    
    // Null-safe comparators
    public static final Comparator<Student> BY_NAME_NULL_SAFE = 
        Comparator.comparing(s -> s.getName().getFullName(), 
                           Comparator.nullsLast(String::compareTo));
    
    // Utility methods for sorting
    public static Comparator<Student> getStudentComparator(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "name" -> BY_NAME;
            case "gpa" -> BY_GPA;
            case "regno" -> BY_REG_NO;
            case "credits" -> BY_TOTAL_CREDITS;
            case "date" -> BY_CREATION_DATE;
            default -> BY_NAME;
        };
    }
    
    public static Comparator<Course> getCourseComparator(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "code" -> BY_COURSE_CODE;
            case "title" -> BY_TITLE;
            case "credits" -> BY_CREDITS;
            case "department" -> BY_DEPARTMENT;
            case "instructor" -> BY_INSTRUCTOR;
            default -> BY_COURSE_CODE;
        };
    }
}