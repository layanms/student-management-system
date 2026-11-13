package com.mycompany.studentmanagementsystem.controller;

import com.mycompany.studentmanagementsystem.App;
import com.mycompany.studentmanagementsystem.model.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewStudentsController {

    @FXML
    private Label resultLabel;

    // Runs automatically when window opens
    @FXML
    public void initialize() {

        // Load students from file
        loadFromFile();

        // Check if list is empty
        if (App.allStudents.isEmpty()) {

            resultLabel.setText("No students found");

        } else {

            StringBuilder output = new StringBuilder();

            // Display all students
            for (Student s : App.allStudents) {

                output.append("Name: ")
                        .append(s.getName())
                        .append("\nID: ")
                        .append(s.getID())
                        .append("\nMajor: ")
                        .append(s.getMajor())
                        .append("\nGrade: ")
                        .append(s.getScore())
                        .append("\n-------------------\n");

            }

            resultLabel.setText(output.toString());
        }
    }

    // Reads students data from CSV file
    private void loadFromFile() {

        try (BufferedReader reader =
                new BufferedReader(
                        new FileReader("students.csv"))) {

            App.allStudents.clear();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length >= 4) {

                    Student s =
                            new Student(
                                    parts[1],
                                    parts[0],
                                    parts[2]
                            );

                    s.setScore(
                            Double.parseDouble(parts[3])
                    );

                    App.allStudents.add(s);

                }
            }

        } catch (IOException e) {

            resultLabel.setText(
                    "Error loading file: "
                    + e.getMessage()
            );
        }
    }

    // Return to dashboard screen
    @FXML
    private void goBack() throws IOException {

        App.setRoot("Dashboard");

    }
}