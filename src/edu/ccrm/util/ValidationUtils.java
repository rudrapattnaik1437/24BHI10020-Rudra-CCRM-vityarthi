package edu.ccrm.util;

import edu.ccrm.domain.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Utility class with validators using functional programming
 * Demonstrates lambdas, method references, and predicates
 */
public class ValidationUtils {
    
    // Email validation using regex
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    // Functional interface implementations with lambdas
    public static final Predicate<String> IS_VALID_EMAIL = 
        email -> email != null && EMAIL_PATTERN.matcher(email).matches();
    
    public static final Predicate<String> IS_NOT_EMPTY = 
        str -> str != null && !str.trim().isEmpty();
    
    public static final Predicate<Integer> IS_POSITIVE = 
        num -> num != null && num > 0;
    
    // Method reference examples
    public static boolean validateStudentId(String id) {
        return IS_NOT_EMPTY.test(id) && id.length() >= 3;
    }
    
    public static boolean validateEmail(String email) {
        return IS_VALID_EMAIL.test(email);
    }
    
    public static boolean validateCredits(Integer credits) {
        return IS_POSITIVE.test(credits) && credits <= 6; // Max 6 credits per course
    }
    
    // Complex validation using multiple predicates
    public static boolean validateStudent(Student student) {
        return student != null &&
               validateStudentId(student.getId()) &&
               validateEmail(student.getEmail()) &&
               IS_NOT_EMPTY.test(student.getRegNo());
    }
    
    public static boolean validateCourse(Course course) {
        return course != null &&
               IS_NOT_EMPTY.test(course.getCode().getCode()) &&
               IS_NOT_EMPTY.test(course.getTitle()) &&
               validateCredits(course.getCredits()) &&
               IS_NOT_EMPTY.test(course.getInstructor()) &&
               IS_NOT_EMPTY.test(course.getDepartment());
    }
    
    // Assertion demonstration (enable with -ea flag)
    public static void assertValidGPA(double gpa) {
        assert gpa >= 0.0 && gpa <= 10.0 : "GPA must be between 0.0 and 10.0, got: " + gpa;
    }
    
    public static void assertValidCredits(int credits) {
        assert credits > 0 && credits <= 6 : "Credits must be between 1 and 6, got: " + credits;
    }
}