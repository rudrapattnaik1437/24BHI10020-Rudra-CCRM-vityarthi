# USAGE GUIDE - Campus Course & Records Manager (CCRM)

This guide provides detailed instructions for using the CCRM application, including sample data and common workflows.

## Quick Start

### 1. Compilation and Running

```bash
# Navigate to project directory
cd "c:\Users\ashup\OneDrive\Desktop\java project"

# Compile all source files
javac -d bin -cp src src\edu\ccrm\**\*.java

# Run the application
java -cp bin edu.ccrm.CCRM

# Run with assertions enabled (recommended)
java -ea -cp bin edu.ccrm.CCRM
```

### 2. First Launch

When you first run CCRM, you'll see:

```
Campus Course & Records Manager (CCRM) v1.0
========================================
Java Platform: Java SE 17.0.x - Platform independent application running on Windows 11

==================================================
CAMPUS COURSE & RECORDS MANAGER
==================================================
1. Student Management
2. Course Management
3. Enrollment Management
4. Grade Management
5. File Operations (Import/Export)
6. Reports & Analytics
7. Backup Operations
8. Platform Information
0. Exit
--------------------------------------------------
```

## Menu Navigation Guide

### 1. Student Management (Menu Option 1)

#### Adding a Student
```
Choose: 1 → 1 (Add Student)

Enter Student ID: S009
Enter Registration Number: REG009
Enter First Name: John
Enter Last Name: Smith
Enter Email: john.smith@university.edu
```

#### Searching Students
```
Choose: 1 → 3 (Search Students)
Enter search query: John
```
*Searches by name, registration number, or ID*

#### Viewing Student Profile
```
Choose: 1 → 4 (View Student Profile)
Enter Student ID: S001
```
*Shows complete profile including transcript if grades exist*

### 2. Course Management (Menu Option 2)

#### Adding a Course
```
Choose: 2 → 1 (Add Course)

Enter Course Code (e.g., CS101): CS201
Enter Course Title: Advanced Programming
Enter Credits: 4
Enter Instructor: Dr. Tech Expert
Enter Department: Computer Science

Select Semester:
1. Spring Semester
2. Summer Semester
3. Fall Semester
4. Winter Semester
Enter semester choice: 3
```

#### Filtering Courses
```
# By Department
Choose: 2 → 4 (Filter by Department)
Enter department: Computer Science

# By Instructor
Choose: 2 → 5 (Filter by Instructor)
Enter instructor name: Dr. Alice Wilson

# By Semester
Choose: 2 → 6 (Filter by Semester)
Select semester: 1 (Spring)
```

### 3. Enrollment Management (Menu Option 3)

#### Enrolling a Student
```
Choose: 3 → 1 (Enroll Student in Course)
Enter Student ID: S001
Enter Course Code: CS101
```

#### Viewing Enrollments
```
Choose: 3 → 3 (View Student Enrollments)
Enter Student ID: S001
```

### 4. Grade Management (Menu Option 4)

#### Recording Grades
```
Choose: 4 → 1 (Record Grade)
Enter Student ID: S001
Enter Course Code: CS101

Select Grade:
1. S (10.0) - Outstanding
2. A (9.0) - Very Good
3. B (8.0) - Good
4. C (7.0) - Average
5. D (6.0) - Below Average
6. E (5.0) - Poor
7. F (0.0) - Fail
Enter grade choice: 2
```

#### Generating Transcript
```
Choose: 4 → 3 (Generate Transcript)
Enter Student ID: S001
```

Sample output:
```
============================================================
OFFICIAL TRANSCRIPT
============================================================
Student: John Doe
ID: S001
Registration No: REG001
Status: ACTIVE
------------------------------------------------------------
COURSES AND GRADES:
Code       Title                          Credits  Grade Points
------------------------------------------------------------
CS101      Introduction to Programming    3        A     9.0
MATH201    Calculus II                    4        B     8.0
------------------------------------------------------------
Total Credits: 7
GPA: 8.43
============================================================
```

### 5. File Operations (Menu Option 5)

#### Importing Data
```
# Import Students
Choose: 5 → 1 (Import Students from CSV)
Enter CSV filename: sample_students.csv

# Import Courses  
Choose: 5 → 2 (Import Courses from CSV)
Enter CSV filename: sample_courses.csv
```

#### Exporting Data
```
# Export Students
Choose: 5 → 3 (Export Students to CSV)
Enter filename for export: exported_students_2024.csv

# Export Enrollments
Choose: 5 → 5 (Export Enrollments to CSV)
Enter filename for export: enrollments_report.csv
```

### 6. Reports & Analytics (Menu Option 6)

#### GPA Distribution
```
Choose: 6 → 1 (GPA Distribution)
```
Shows distribution of students by GPA ranges.

#### Top Students Report
```
Choose: 6 → 2 (Top Students)
Enter number of top students to show: 5
```

#### Course Statistics
```
Choose: 6 → 3 (Course Statistics)
```
Shows total courses and breakdown by department.

### 7. Backup Operations (Menu Option 7)

#### Creating a Backup
```
Choose: 7 → 1 (Create Backup)
```
Creates timestamped backup in `backups/backup_YYYY-MM-DD_HH-mm-ss/`

#### Listing Backups
```
Choose: 7 → 2 (List Backups)
```
Shows all available backups with creation times and sizes.

## Sample Data Files

### Student CSV Format (`sample_students.csv`)
```csv
ID,RegNo,Name,Email
S001,REG001,John Doe,john.doe@email.com
S002,REG002,Jane Smith,jane.smith@email.com
```

### Course CSV Format (`sample_courses.csv`)
```csv
Code,Title,Credits,Instructor,Semester,Department
CS101,Introduction to Programming,3,Dr. Alice Wilson,FALL,Computer Science
MATH201,Calculus II,4,Prof. Robert Brown,SPRING,Mathematics
```

## Common Workflows

### Workflow 1: New Semester Setup
1. Import courses from CSV (Option 5 → 2)
2. Import students from CSV (Option 5 → 1)
3. Bulk enroll students (Option 3 → 1, repeat)
4. Create initial backup (Option 7 → 1)

### Workflow 2: Grade Recording
1. Enroll students in courses (Option 3 → 1)
2. Record grades throughout semester (Option 4 → 1)
3. Generate transcripts (Option 4 → 3)
4. Export final reports (Option 5 → 5)

### Workflow 3: Semester End Reports
1. View top students (Option 6 → 2)
2. Check GPA distribution (Option 6 → 1)
3. Export all data (Options 5 → 3, 4, 5)
4. Create final backup (Option 7 → 1)

## Advanced Features

### Enabling Assertions
Run with `-ea` flag to enable assertions:
```bash
java -ea -cp bin edu.ccrm.CCRM
```

This enables validation checks like:
- GPA bounds (0.0 - 10.0)
- Credit limits (1-6 per course)
- Data integrity validations

### Search Capabilities
The search functionality supports:
- **Student Search**: By name, ID, or registration number
- **Course Search**: By title, code, instructor, or department
- **Case-insensitive matching**
- **Partial string matching**

### File Structure After Use
```
java project/
├── data/
│   ├── exported_students.csv
│   ├── exported_courses.csv
│   └── enrollments_report.csv
├── backups/
│   ├── backup_2024-01-15_14-30-22/
│   └── backup_2024-01-15_16-45-10/
└── test-data/
    ├── sample_students.csv
    └── sample_courses.csv
```

## Troubleshooting

### Common Issues

**Issue**: "Course code format invalid"
**Solution**: Use format like CS101, MATH201 (2-4 letters + 3-4 digits)

**Issue**: "Student already enrolled"
**Solution**: Check current enrollments before attempting enrollment

**Issue**: "File not found" during import
**Solution**: Ensure CSV files are in the `data/` directory

**Issue**: "GPA calculation error"
**Solution**: Ensure student has recorded grades for enrolled courses

### Performance Notes

- Use streams for large data operations
- Backup operations use recursive file processing
- Memory usage scales with data size
- For production use, consider database integration

## Command Reference

| Operation | Menu Path | Description |
|-----------|-----------|-------------|
| Add Student | 1 → 1 | Create new student record |
| Add Course | 2 → 1 | Create new course with builder pattern |
| Enroll Student | 3 → 1 | Link student to course |
| Record Grade | 4 → 1 | Assign grade to student-course pair |
| Import CSV | 5 → 1/2 | Load data from CSV files |
| Generate Report | 6 → 1/2/3 | Analytics and statistics |
| Create Backup | 7 → 1 | Recursive backup with timestamp |
| Platform Info | 8 | Show Java platform details |

## Exit and Cleanup

To exit the application:
1. Choose option `0` from main menu
2. Application will close gracefully
3. All data remains in memory (not persisted unless exported)
4. Use export functions before exiting to save data

---

For additional help or questions about Java concepts demonstrated in this application, refer to the comprehensive README.md file.