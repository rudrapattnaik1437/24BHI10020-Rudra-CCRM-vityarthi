package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.*;
import edu.ccrm.config.AppConfig;
import java.util.*;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Main menu implementation demonstrating CLI operations
 * Uses switch statements, loops, and exception handling
 */
public class MainMenu {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final TranscriptService transcriptService;
    private final ImportExportService importExportService;
    private final BackupService backupService;
    private final AppConfig config;
    private boolean running;
    
    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentServiceImpl();
        this.courseService = new CourseServiceImpl();
        this.transcriptService = new TranscriptServiceImpl(studentService, courseService);
        this.importExportService = new ImportExportService();
        this.backupService = new BackupService();
        this.config = AppConfig.getInstance();
        this.running = true;
        
        // Load sample data
        loadSampleData();
    }
    
    // Main run loop demonstrating while loop and break
    public void run() {
        System.out.println("Welcome to Campus Course & Records Manager by 24BCE10528");
        
        while (running) { // while loop demonstration
            try {
                displayMainMenu();
                int choice = getIntInput("Enter your choice: ");
                
                // Enhanced switch statement (Java 14+)
                switch (choice) {
                    case 1 -> handleStudentMenu();
                    case 2 -> handleCourseMenu();
                    case 3 -> handleEnrollmentMenu();
                    case 4 -> handleGradeMenu();
                    case 5 -> handleFileOperations();
                    case 6 -> handleReports();
                    case 7 -> handleBackupOperations();
                    case 8 -> showPlatformInfo();
                    case 0 -> {
                        System.out.println("Thank you for using CCRM!");
                        running = false; // break equivalent for while loop
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CAMPUS COURSE & RECORDS MANAGER");
        System.out.println("=".repeat(50));
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Grade Management");
        System.out.println("5. File Operations (Import/Export)");
        System.out.println("6. Reports & Analytics");
        System.out.println("7. Backup Operations");
        System.out.println("8. Platform Information");
        System.out.println("0. Exit");
        System.out.println("-".repeat(50));
    }
    
    // Student management menu
    private void handleStudentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. List All Students");
        System.out.println("3. Search Students");
        System.out.println("4. View Student Profile");
        System.out.println("5. Update Student");
        System.out.println("6. Deactivate Student");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter choice: ");
        
        // Classic switch statement demonstration
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                listAllStudents();
                break;
            case 3:
                searchStudents();
                break;
            case 4:
                viewStudentProfile();
                break;
            case 5:
                updateStudent();
                break;
            case 6:
                deactivateStudent();
                break;
            case 0:
                return; // return to main menu
            default:
                System.out.println("Invalid choice.");
                break; // break demonstration
        }
    }
    
    private void addStudent() {
        try {
            System.out.println("\n--- Add New Student ---");
            String id = getStringInput("Enter Student ID: ");
            String regNo = getStringInput("Enter Registration Number: ");
            String firstName = getStringInput("Enter First Name: ");
            String lastName = getStringInput("Enter Last Name: ");
            String email = getStringInput("Enter Email: ");
            
            Name name = new Name(firstName, lastName);
            Student student = new Student(id, name, email, regNo);
            
            studentService.addStudent(student);
            System.out.println("Student added successfully!");
            
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }
    
    private void listAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        System.out.printf("%-10s %-15s %-25s %-30s %-10s%n", 
                "ID", "Reg No", "Name", "Email", "Status");
        System.out.println("-".repeat(90));
        
        // Enhanced for loop demonstration
        for (Student student : students) {
            System.out.printf("%-10s %-15s %-25s %-30s %-10s%n",
                    student.getId(),
                    student.getRegNo(),
                    student.getName().getFullName(),
                    student.getEmail(),
                    student.getStatus());
        }
    }
    
    private void searchStudents() {
        String query = getStringInput("Enter search query: ");
        List<Student> results = studentService.search(query);
        
        if (results.isEmpty()) {
            System.out.println("No students found matching: " + query);
            return;
        }
        
        System.out.println("Search Results:");
        // Stream with forEach demonstration
        results.forEach(student -> System.out.println("- " + student.getDisplayInfo()));
    }
    
    private void viewStudentProfile() {
        String studentId = getStringInput("Enter Student ID: ");
        Optional<Student> studentOpt = studentService.findById(studentId);
        
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            System.out.println("\n--- Student Profile ---");
            System.out.println(student.toString());
            System.out.println("\nEnrolled Courses:");
            student.getEnrolledCourses().forEach(course -> 
                System.out.println("- " + course.getCode() + ": " + course.getTitle()));
            
            if (!student.getGrades().isEmpty()) {
                System.out.println("\nTranscript:");
                String transcript = transcriptService.generateTranscript(studentId);
                System.out.println(transcript);
            }
        } else {
            System.out.println("Student not found.");
        }
    }
    
    private void updateStudent() {
        // Implementation for update student
        System.out.println("Update student functionality - Implementation here");
    }
    
    private void deactivateStudent() {
        String studentId = getStringInput("Enter Student ID to deactivate: ");
        studentService.deactivateStudent(studentId);
        System.out.println("Student deactivated successfully.");
    }
    
    // Course management menu
    private void handleCourseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add Course");
        System.out.println("2. List All Courses");
        System.out.println("3. Search Courses");
        System.out.println("4. Filter by Department");
        System.out.println("5. Filter by Instructor");
        System.out.println("6. Filter by Semester");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> addCourse();
            case 2 -> listAllCourses();
            case 3 -> searchCourses();
            case 4 -> filterByDepartment();
            case 5 -> filterByInstructor();
            case 6 -> filterBySemester();
            case 0 -> { return; }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    private void addCourse() {
        try {
            System.out.println("\n--- Add New Course ---");
            String code = getStringInput("Enter Course Code (e.g., CS101): ");
            String title = getStringInput("Enter Course Title: ");
            int credits = getIntInput("Enter Credits: ");
            String instructor = getStringInput("Enter Instructor: ");
            String department = getStringInput("Enter Department: ");
            
            System.out.println("Select Semester:");
            // Array demonstration
            Semester[] semesters = Semester.values();
            for (int i = 0; i < semesters.length; i++) {
                System.out.println((i + 1) + ". " + semesters[i]);
            }
            
            int semesterChoice = getIntInput("Enter semester choice: ") - 1;
            if (semesterChoice < 0 || semesterChoice >= semesters.length) {
                throw new IllegalArgumentException("Invalid semester choice");
            }
            
            CourseCode courseCode = new CourseCode(code);
            Course course = new Course.Builder(courseCode, title)
                    .credits(credits)
                    .instructor(instructor)
                    .semester(semesters[semesterChoice])
                    .department(department)
                    .build();
            
            courseService.addCourse(course);
            System.out.println("Course added successfully!");
            
        } catch (Exception e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }
    
    private void listAllCourses() {
        System.out.println("\\n--- All Courses ---");
        List<Course> courses = courseService.getAllCourses();
        
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        
        System.out.printf("%-10s %-30s %-8s %-20s %-15s%n", 
                "Code", "Title", "Credits", "Instructor", "Department");
        System.out.println("-".repeat(90));
        
        courses.forEach(course -> {
            String title = course.getTitle().length() > 28 ? 
                    course.getTitle().substring(0, 25) + "..." : course.getTitle();
            System.out.printf("%-10s %-30s %-8d %-20s %-15s%n",
                    course.getCode().getCode(),
                    title,
                    course.getCredits(),
                    course.getInstructor(),
                    course.getDepartment());
        });
    }
    
    private void searchCourses() {
        String query = getStringInput("Enter search query: ");
        List<Course> results = courseService.search(query);
        
        if (results.isEmpty()) {
            System.out.println("No courses found matching: " + query);
            return;
        }
        
        System.out.println("Search Results:");
        results.forEach(course -> System.out.println("- " + course.toString()));
    }
    
    private void filterByDepartment() {
        String department = getStringInput("Enter department: ");
        List<Course> courses = courseService.findByDepartment(department);
        displayCourseList(courses, "Courses in " + department + " Department");
    }
    
    private void filterByInstructor() {
        String instructor = getStringInput("Enter instructor name: ");
        List<Course> courses = courseService.findByInstructor(instructor);
        displayCourseList(courses, "Courses by " + instructor);
    }
    
    private void filterBySemester() {
        System.out.println("Select Semester:");
        Semester[] semesters = Semester.values();
        
        // for loop demonstration with continue
        for (int i = 0; i < semesters.length; i++) {
            if (semesters[i] == null) continue; // continue demonstration (hypothetical)
            System.out.println((i + 1) + ". " + semesters[i]);
        }
        
        int choice = getIntInput("Enter semester choice: ") - 1;
        if (choice >= 0 && choice < semesters.length) {
            List<Course> courses = courseService.findBySemester(semesters[choice]);
            displayCourseList(courses, semesters[choice] + " Courses");
        }
    }
    
    private void displayCourseList(List<Course> courses, String title) {
        System.out.println("\n--- " + title + " ---");
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(course -> System.out.println("- " + course.toString()));
        }
    }
    
    // Enrollment management
    private void handleEnrollmentMenu() {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. Unenroll Student from Course");
        System.out.println("3. View Student Enrollments");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> enrollStudent();
            case 2 -> unenrollStudent();
            case 3 -> viewStudentEnrollments();
            case 0 -> { return; }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    private void enrollStudent() {
        try {
            String studentId = getStringInput("Enter Student ID: ");
            String courseCode = getStringInput("Enter Course Code: ");
            
            Optional<Course> courseOpt = courseService.findById(courseCode);
            if (courseOpt.isPresent()) {
                studentService.enrollStudentInCourse(studentId, courseOpt.get());
                System.out.println("Student enrolled successfully!");
            } else {
                System.out.println("Course not found.");
            }
        } catch (Exception e) {
            System.err.println("Enrollment failed: " + e.getMessage());
        }
    }
    
    private void unenrollStudent() {
        try {
            String studentId = getStringInput("Enter Student ID: ");
            String courseCode = getStringInput("Enter Course Code: ");
            
            Optional<Course> courseOpt = courseService.findById(courseCode);
            if (courseOpt.isPresent()) {
                studentService.unenrollStudentFromCourse(studentId, courseOpt.get());
                System.out.println("Student unenrolled successfully!");
            } else {
                System.out.println("Course not found.");
            }
        } catch (Exception e) {
            System.err.println("Unenrollment failed: " + e.getMessage());
        }
    }
    
    private void viewStudentEnrollments() {
        String studentId = getStringInput("Enter Student ID: ");
        Optional<Student> studentOpt = studentService.findById(studentId);
        
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            System.out.println("\n--- Enrollments for " + student.getName() + " ---");
            if (student.getEnrolledCourses().isEmpty()) {
                System.out.println("No enrollments found.");
            } else {
                student.getEnrolledCourses().forEach(course -> 
                    System.out.println("- " + course.getCode() + ": " + course.getTitle()));
            }
        } else {
            System.out.println("Student not found.");
        }
    }
    
    // Grade management
    private void handleGradeMenu() {
        System.out.println("\n--- Grade Management ---");
        System.out.println("1. Record Grade");
        System.out.println("2. View Student GPA");
        System.out.println("3. Generate Transcript");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> recordGrade();
            case 2 -> viewStudentGPA();
            case 3 -> generateTranscript();
            case 0 -> { return; }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    private void recordGrade() {
        try {
            String studentId = getStringInput("Enter Student ID: ");
            String courseCode = getStringInput("Enter Course Code: ");
            
            System.out.println("Select Grade:");
            Grade[] grades = Grade.values();
            for (int i = 0; i < grades.length; i++) {
                System.out.println((i + 1) + ". " + grades[i]);
            }
            
            int gradeChoice = getIntInput("Enter grade choice: ") - 1;
            if (gradeChoice >= 0 && gradeChoice < grades.length) {
                transcriptService.recordGrade(studentId, courseCode, grades[gradeChoice]);
                System.out.println("Grade recorded successfully!");
            } else {
                System.out.println("Invalid grade choice.");
            }
        } catch (Exception e) {
            System.err.println("Error recording grade: " + e.getMessage());
        }
    }
    
    private void viewStudentGPA() {
        String studentId = getStringInput("Enter Student ID: ");
        try {
            double gpa = transcriptService.calculateGPA(studentId);
            System.out.printf("Student GPA: %.2f%n", gpa);
        } catch (Exception e) {
            System.err.println("Error calculating GPA: " + e.getMessage());
        }
    }
    
    private void generateTranscript() {
        String studentId = getStringInput("Enter Student ID: ");
        try {
            String transcript = transcriptService.generateTranscript(studentId);
            System.out.println("\n" + transcript);
        } catch (Exception e) {
            System.err.println("Error generating transcript: " + e.getMessage());
        }
    }
    
    // File operations
    private void handleFileOperations() {
        System.out.println("\n--- File Operations ---");
        System.out.println("1. Import Students from CSV");
        System.out.println("2. Import Courses from CSV");
        System.out.println("3. Export Students to CSV");
        System.out.println("4. Export Courses to CSV");
        System.out.println("5. Export Enrollments to CSV");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> importStudents();
            case 2 -> importCourses();
            case 3 -> exportStudents();
            case 4 -> exportCourses();
            case 5 -> exportEnrollments();
            case 0 -> { return; }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    private void importStudents() {
        String filename = getStringInput("Enter CSV filename (in data directory): ");
        try {
            List<Student> students = importExportService.importStudents(filename);
            students.forEach(studentService::addStudent);
            System.out.println("Imported " + students.size() + " students successfully!");
        } catch (IOException e) {
            System.err.println("Import failed: " + e.getMessage());
        }
    }
    
    private void importCourses() {
        String filename = getStringInput("Enter CSV filename (in data directory): ");
        try {
            List<Course> courses = importExportService.importCourses(filename);
            courses.forEach(courseService::addCourse);
            System.out.println("Imported " + courses.size() + " courses successfully!");
        } catch (IOException e) {
            System.err.println("Import failed: " + e.getMessage());
        }
    }
    
    private void exportStudents() {
        String filename = getStringInput("Enter filename for export: ");
        try {
            List<Student> students = studentService.getAllStudents();
            importExportService.exportStudents(students, filename);
            System.out.println("Exported " + students.size() + " students to " + filename);
        } catch (IOException e) {
            System.err.println("Export failed: " + e.getMessage());
        }
    }
    
    private void exportCourses() {
        String filename = getStringInput("Enter filename for export: ");
        try {
            List<Course> courses = courseService.getAllCourses();
            importExportService.exportCourses(courses, filename);
            System.out.println("Exported " + courses.size() + " courses to " + filename);
        } catch (IOException e) {
            System.err.println("Export failed: " + e.getMessage());
        }
    }
    
    private void exportEnrollments() {
        String filename = getStringInput("Enter filename for export: ");
        try {
            List<Student> students = studentService.getAllStudents();
            importExportService.exportEnrollments(students, filename);
            System.out.println("Exported enrollments to " + filename);
        } catch (IOException e) {
            System.err.println("Export failed: " + e.getMessage());
        }
    }
    
    // Reports and analytics
    private void handleReports() {
        System.out.println("\n--- Reports & Analytics ---");
        System.out.println("1. GPA Distribution");
        System.out.println("2. Top Students");
        System.out.println("3. Course Statistics");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> showGPADistribution();
            case 2 -> showTopStudents();
            case 3 -> showCourseStatistics();
            case 0 -> { return; }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    private void showGPADistribution() {
        System.out.println("\n--- GPA Distribution ---");
        Map<String, Double> distribution = transcriptService.getGPADistribution();
        distribution.forEach((category, avgGPA) -> 
            System.out.printf("%-25s: %.2f%n", category, avgGPA));
    }
    
    private void showTopStudents() {
        int count = getIntInput("Enter number of top students to show: ");
        List<Student> topStudents = transcriptService.getTopStudents(count);
        
        System.out.println("\n--- Top " + count + " Students ---");
        System.out.printf("%-15s %-25s %-10s%n", "Reg No", "Name", "GPA");
        System.out.println("-".repeat(50));
        
        topStudents.forEach(student -> 
            System.out.printf("%-15s %-25s %-10.2f%n", 
                student.getRegNo(), 
                student.getName().getFullName(), 
                student.calculateGPA()));
    }
    
    private void showCourseStatistics() {
        System.out.println("\n--- Course Statistics ---");
        List<Course> courses = courseService.getAllCourses();
        
        System.out.println("Total Courses: " + courses.size());
        
        // Group by department using streams
        Map<String, Long> departmentCounts = courses.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                    Course::getDepartment, 
                    java.util.stream.Collectors.counting()));
        
        System.out.println("\nCourses by Department:");
        departmentCounts.forEach((dept, count) -> 
            System.out.println("- " + dept + ": " + count));
    }
    
    // Backup operations
    private void handleBackupOperations() {
        System.out.println("\n--- Backup Operations ---");
        System.out.println("1. Create Backup");
        System.out.println("2. List Backups");
        System.out.println("3. Calculate Backup Size");
        System.out.println("4. Clean Old Backups");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter choice: ");
        
        switch (choice) {
            case 1 -> createBackup();
            case 2 -> listBackups();
            case 3 -> calculateBackupSize();
            case 4 -> cleanOldBackups();
            case 0 -> { return; }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    private void createBackup() {
        try {
            Path backupPath = backupService.createBackup();
            long size = backupService.calculateBackupSize(backupPath);
            System.out.printf("Backup created successfully!%nSize: %d bytes%n", size);
        } catch (IOException e) {
            System.err.println("Backup failed: " + e.getMessage());
        }
    }
    
    private void listBackups() {
        try {
            backupService.listBackups();
        } catch (IOException e) {
            System.err.println("Failed to list backups: " + e.getMessage());
        }
    }
    
    private void calculateBackupSize() {
        try {
            System.out.println("\n--- Recursive Backup Size Calculation ---");
            backupService.listBackups();
        } catch (IOException e) {
            System.err.println("Failed to calculate backup sizes: " + e.getMessage());
        }
    }
    
    private void cleanOldBackups() {
        int keepCount = getIntInput("Enter number of backups to keep: ");
        try {
            backupService.cleanOldBackups(keepCount);
            System.out.println("Old backups cleaned successfully!");
        } catch (IOException e) {
            System.err.println("Failed to clean backups: " + e.getMessage());
        }
    }
    
    private void showPlatformInfo() {
        System.out.println("\n--- Platform Information ---");
        System.out.println("Application: Campus Course & Records Manager");
        System.out.println("Version: " + config.getVersion());
        System.out.println("Platform: " + config.getPlatformInfo());
        System.out.println();
        System.out.println("Java Platform Comparison:");
        System.out.println("• Java SE (Standard Edition): Desktop and server applications");
        System.out.println("• Java EE (Enterprise Edition): Large-scale enterprise applications");
        System.out.println("• Java ME (Micro Edition): Mobile and embedded applications");
        System.out.println();
        System.out.println("This application demonstrates Java SE capabilities.");
    }
    
    // Helper methods for input handling
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    // Load sample data for demonstration
    private void loadSampleData() {
        try {
            // Sample students
            studentService.addStudent(new Student("S001", new Name("John", "Doe"), "john.doe@email.com", "REG001"));
            studentService.addStudent(new Student("S002", new Name("Jane", "Smith"), "jane.smith@email.com", "REG002"));
            studentService.addStudent(new Student("S003", new Name("Bob", "Johnson"), "bob.johnson@email.com", "REG003"));
            
            // Sample courses
            Course cs101 = new Course.Builder(new CourseCode("CS101"), "Introduction to Programming")
                    .credits(3)
                    .instructor("Dr. Alice Wilson")
                    .semester(Semester.FALL)
                    .department("Computer Science")
                    .build();
            
            Course math201 = new Course.Builder(new CourseCode("MATH201"), "Calculus II")
                    .credits(4)
                    .instructor("Prof. Robert Brown")
                    .semester(Semester.SPRING)
                    .department("Mathematics")
                    .build();
            
            Course eng101 = new Course.Builder(new CourseCode("ENG101"), "English Composition")
                    .credits(3)
                    .instructor("Dr. Sarah Davis")
                    .semester(Semester.FALL)
                    .department("English")
                    .build();
            
            courseService.addCourse(cs101);
            courseService.addCourse(math201);
            courseService.addCourse(eng101);
            
            // Sample enrollments and grades
            studentService.enrollStudentInCourse("S001", cs101);
            studentService.enrollStudentInCourse("S001", math201);
            studentService.enrollStudentInCourse("S002", cs101);
            studentService.enrollStudentInCourse("S002", eng101);
            studentService.enrollStudentInCourse("S003", math201);
            studentService.enrollStudentInCourse("S003", eng101);
            
            // Sample grades
            transcriptService.recordGrade("S001", "CS101", Grade.A);
            transcriptService.recordGrade("S001", "MATH201", Grade.B);
            transcriptService.recordGrade("S002", "CS101", Grade.S);
            transcriptService.recordGrade("S002", "ENG101", Grade.A);
            transcriptService.recordGrade("S003", "MATH201", Grade.C);
            transcriptService.recordGrade("S003", "ENG101", Grade.B);
            
        } catch (Exception e) {
            System.err.println("Warning: Failed to load sample data: " + e.getMessage());
        }
    }
}