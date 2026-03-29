# Campus Course & Records Manager (CCRM) by Rudranarayan Pattnaik(24BHI10020)

A comprehensive Java SE console application demonstrating advanced programming concepts including OOP, design patterns, file I/O, streams, and functional programming.

## Table of Contents

- [Project Overview](#project-overview)
- [Java Platform Overview](#java-platform-overview)
- [Installation & Setup](#installation--setup)
- [Architecture & Design](#architecture--design)
- [Features](#features)
- [Usage](#usage)
- [Technical Requirements Mapping](#technical-requirements-mapping)
- [Build & Run](#build--run)
- [Contributing](#contributing)

## Project Overview

CCRM is a console-based Java application that manages:

- **Students**: Registration, enrollment, profile management
- **Courses**: Creation, assignment, department management  
- **Grades & Transcripts**: Grade recording, GPA calculation, transcript generation
- **File Operations**: CSV import/export, backup/restore functionality

### Key Features

✅ Complete OOP implementation with all four pillars  
✅ Design patterns (Singleton, Builder)  
✅ Advanced exception handling  
✅ NIO.2 file operations with streams  
✅ Functional programming with lambdas  
✅ Recursive algorithms  
✅ Date/Time API integration  
✅ Menu-driven CLI interface  

## Java Platform Overview

### Evolution of Java

- **1995**: Java 1.0 - Initial release by Sun Microsystems
- **1997**: Java 1.1 - Inner classes, JDBC, RMI
- **1998**: Java 1.2 - Collections Framework, Swing
- **2000**: Java 1.3 - HotSpot JVM, JNDI
- **2002**: Java 1.4 - Assertions, NIO, logging
- **2004**: Java 5 - Generics, annotations, autoboxing
- **2006**: Java 6 - Performance improvements
- **2011**: Java 7 - Try-with-resources, diamond operator
- **2014**: Java 8 - Lambda expressions, streams, date/time API
- **2017**: Java 9 - Modules, JShell
- **2018**: Java 10 - Local variable type inference (var)
- **2018**: Java 11 - LTS release, new HTTP client
- **2019**: Java 12-13 - Switch expressions, text blocks
- **2020**: Java 14-15 - Pattern matching, records
- **2021**: Java 16-17 - LTS release, sealed classes
- **2022**: Java 18-19 - Virtual threads preview
- **2023**: Java 20-21 - LTS release, string templates

### Java Platform Editions

| Edition | Description | Use Cases | Key Features |
|---------|-------------|-----------|--------------|
| **Java SE** | Standard Edition | Desktop apps, command-line tools, libraries | Core APIs, JVM, basic libraries |
| **Java EE** | Enterprise Edition | Large-scale enterprise applications | Servlets, EJB, JPA, web services |
| **Java ME** | Micro Edition | Mobile and embedded devices | Lightweight runtime, minimal APIs |

### Java Architecture: JDK, JRE, JVM

```
┌─────────────────────────────────────┐
│              JDK (Java Development Kit)              │
│  ┌─────────────────────────────────┐ │
│  │         JRE (Java Runtime Environment)         │ │
│  │  ┌─────────────────────────────┐ │ │
│  │  │      JVM (Java Virtual Machine)      │ │ │
│  │  │  - Bytecode interpreter     │ │ │
│  │  │  - Garbage collector        │ │ │
│  │  │  - Memory management        │ │ │
│  │  └─────────────────────────────┘ │ │
│  │  - Core Libraries (java.lang, java.util) │ │
│  │  - Standard APIs                │ │
│  └─────────────────────────────────┘ │
│  - Compiler (javac)                  │
│  - Documentation (javadoc)           │
│  - Debugging tools                   │
└─────────────────────────────────────┘
```

**Interaction Flow:**
1. **JDK** contains development tools and the JRE
2. **JRE** provides runtime environment and core libraries
3. **JVM** executes Java bytecode and manages memory
4. Source code (.java) → Compiler → Bytecode (.class) → JVM → Machine code

## Installation & Setup

### Windows Installation Steps

1. **Download JDK**
   - Visit [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
   - Download JDK 17 or later for Windows x64

2. **Install JDK**
   - Run the installer as Administrator
   - Choose installation directory (default: `C:\Program Files\Java\jdk-17`)
   - Complete installation wizard

3. **Set Environment Variables**
   ```cmd
   # Set JAVA_HOME
   setx JAVA_HOME "C:\Program Files\Java\jdk-17"
   
   # Add to PATH
   setx PATH "%PATH%;%JAVA_HOME%\bin"
   ```

4. **Verify Installation**
   ```cmd
   java -version
   javac -version
   ```

   Expected output:
   ```
   java version "17.0.x"
   Java(TM) SE Runtime Environment (build 17.0.x+x)
   Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+x, mixed mode)
   ```

### Eclipse IDE Setup

1. **Download Eclipse**
   - Visit [Eclipse Downloads](https://www.eclipse.org/downloads/)
   - Download "Eclipse IDE for Java Developers"

2. **Install Eclipse**
   - Extract to desired location
   - Run `eclipse.exe`

3. **Create New Project**
   - File → New → Java Project
   - Project name: "CCRM"
   - Use default JRE
   - Create project

4. **Configure Build Path**
   - Right-click project → Properties
   - Java Build Path → Libraries
   - Ensure correct JRE version

5. **Run Configuration**
   - Right-click `CCRM.java` → Run As → Java Application
   - Or use Run button in toolbar

## Architecture & Design

### Package Structure

```
edu.ccrm/
├── cli/           # Command-line interface
├── domain/        # Domain models and entities  
├── service/       # Business logic and services
├── io/           # File I/O operations
├── util/         # Utility classes
├── config/       # Configuration and patterns
└── CCRM.java     # Main application class
```

### Design Patterns Used

- **Singleton Pattern**: `AppConfig` class for application configuration
- **Builder Pattern**: `Course.Builder` and `TranscriptBuilder` for complex object creation
- **Strategy Pattern**: Comparator utilities for different sorting strategies
- **Factory Pattern**: Service implementations with interface abstraction

### OOP Principles Demonstrated

1. **Encapsulation**: Private fields with public getters/setters
2. **Inheritance**: `Person` → `Student`/`Instructor` hierarchy
3. **Abstraction**: Abstract `Person` class and service interfaces
4. **Polymorphism**: Interface implementations and method overriding

## Features

### Student Management
- Add, update, deactivate students
- Search and filter functionality
- Enrollment tracking
- Profile and transcript generation

### Course Management
- Course creation with validation
- Department and instructor assignment
- Semester-based organization
- Search and filtering options

### Grade Management
- Grade recording with enum validation
- GPA calculation using streams
- Transcript generation with builder pattern
- Analytics and reporting

### File Operations
- CSV import/export using NIO.2
- Backup and restore functionality
- Recursive directory operations
- Stream-based file processing

## Usage

### Running the Application

```bash
# Compile all Java files
javac -d bin -cp src src/edu/ccrm/**/*.java

# Run the application
java -cp bin edu.ccrm.CCRM

# Enable assertions (recommended)
java -ea -cp bin edu.ccrm.CCRM
```

### Sample Operations

1. **Add Student**
   - Choose option 1 from main menu
   - Select "Add Student"
   - Enter student details

2. **Create Course**
   - Choose option 2 from main menu
   - Select "Add Course"
   - Use builder pattern via prompts

3. **Record Grades**
   - Choose option 4 from main menu
   - Select student and course
   - Assign grade from enum options

4. **Generate Reports**
   - Choose option 6 from main menu
   - View GPA distribution
   - Show top students

## Technical Requirements Mapping

| Requirement | Implementation | Location |
|-------------|----------------|----------|
| **OOP - Encapsulation** | Private fields + getters/setters | `Person`, `Student`, `Course` |
| **OOP - Inheritance** | Person → Student/Instructor | `domain/` package |
| **OOP - Abstraction** | Abstract Person class | `Person.java` |
| **OOP - Polymorphism** | Interface implementations | Service classes |
| **Interfaces** | Searchable, StudentService | `service/` package |
| **Enums** | Grade, Semester, StudentStatus | `domain/` package |
| **Immutable Classes** | Name, CourseCode | `domain/` package |
| **Nested Classes** | TranscriptBuilder, FileCount | Various classes |
| **Lambda Expressions** | Comparators, predicates | `util/ComparatorUtils` |
| **Streams** | File processing, collections | Throughout application |
| **NIO.2** | File operations, backup | `io/` package |
| **Date/Time API** | Timestamps, enrollment dates | Domain classes |
| **Recursion** | Directory traversal, math | `util/RecursiveUtils` |
| **Exception Handling** | Custom exceptions, try-catch | Throughout application |
| **Design Patterns** | Singleton, Builder | `config/`, various |

### Exception Handling Examples

```java
// Custom checked exception
public class DuplicateEnrollmentException extends Exception

// Custom unchecked exception  
public class MaxCreditLimitExceededException extends RuntimeException

// Multi-catch example
try {
    studentService.addStudent(student);
} catch (IllegalArgumentException | DuplicateEnrollmentException e) {
    System.err.println("Error: " + e.getMessage());
}
```

### Assertions Usage

Enable assertions with `-ea` flag:

```bash
java -ea -cp bin edu.ccrm.CCRM
```

Example assertions in code:
```java
assert gpa >= 0.0 && gpa <= 10.0 : "Invalid GPA: " + gpa;
assert credits > 0 : "Credits must be positive";
```

## Build & Run

### Prerequisites
- JDK 17 or later
- Windows 10/11 (tested platform)

### Compilation Commands

```cmd
# Create bin directory
mkdir bin

# Compile all source files
javac -d bin -cp src src\edu\ccrm\**\*.java

# Run with assertions enabled
java -ea -cp bin edu.ccrm.CCRM
```

### IDE Setup (Eclipse)

1. Import as existing Java project
2. Set build path to JDK 17+
3. Run `CCRM.java` as Java Application
4. Add VM argument `-ea` for assertions

### Sample Data

The application loads sample data on startup:
- 3 students (John Doe, Jane Smith, Bob Johnson)
- 3 courses (CS101, MATH201, ENG101)
- Sample enrollments and grades

## Project Structure

```
java project/
├── src/
│   └── edu/ccrm/
│       ├── cli/MainMenu.java
│       ├── domain/
│       │   ├── Person.java (abstract)
│       │   ├── Student.java
│       │   ├── Instructor.java
│       │   ├── Course.java (with Builder)
│       │   ├── Enrollment.java
│       │   ├── Grade.java (enum)
│       │   ├── Semester.java (enum)
│       │   ├── Name.java (immutable)
│       │   ├── CourseCode.java (immutable)
│       │   └── *Exception.java (custom)
│       ├── service/
│       │   ├── Searchable.java (interface)
│       │   ├── *Service.java (interfaces)
│       │   └── *ServiceImpl.java
│       ├── io/
│       │   ├── ImportExportService.java
│       │   └── BackupService.java
│       ├── util/
│       │   ├── ValidationUtils.java
│       │   ├── ComparatorUtils.java
│       │   └── RecursiveUtils.java
│       ├── config/
│       │   └── AppConfig.java (Singleton)
│       └── CCRM.java (main)
├── data/               # CSV files and exports
├── test-data/          # Sample CSV files
├── screenshots/        # Installation and demo images
├── README.md
└── USAGE.md
```

## Contributing

This is an academic project demonstrating Java SE concepts. For learning purposes:

1. Review the code structure and patterns
2. Understand OOP implementations  
3. Study design pattern usage
4. Examine exception handling strategies
5. Analyze stream and functional programming usage

## Acknowledgments

- Java documentation and tutorials
- Oracle Java SE specifications
- Design pattern references
- Academic coursework requirements

---

**Note**: This project demonstrates Java SE capabilities and should not be used in production environments without proper security and performance optimizations.
