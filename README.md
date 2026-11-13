# Student Management System

A desktop application for managing students and their grades, built with **Java** and **JavaFX** using the **MVC** pattern.

## Features
- User login (credentials read from `users.csv`)
- Add new students (ID, name, major)
- Enter and update student grades
- View all students in a table
- Statistical report: class average, number of passing and failing students, and students above and below the average

## Tech Stack
- Java 11
- JavaFX 21 (FXML, CSS)
- Maven
- CSV files for data storage

## Project Structure
```
src/main/java/com/mycompany/studentmanagementsystem/
├── App.java            # Application entry point
├── model/              # Student model
└── controller/         # Login, Dashboard, AddStudent, AddGrade, ViewStudents, Report
src/main/resources/
├── fxml/               # Screen layouts
└── style.css
users.csv               # Demo login accounts
students.csv            # Sample student data
```

## How to Run
Requirements: JDK 11 or later and Maven.

```bash
mvn clean javafx:run
```

Run the command from the project root, because the app reads `users.csv` and `students.csv` from the working directory.

**Demo login:** username `demo`, password `demo123`

## Notes
This is a university course project. Passwords are stored in plain text in a CSV file for simplicity and should not be used in a real application.

## Author
Layan Alsubhi, Computer Science student at Umm Al-Qura University
