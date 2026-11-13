package com.mycompany.studentmanagementsystem.controller;

import com.mycompany.studentmanagementsystem.App;
import com.mycompany.studentmanagementsystem.model.Student;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddStudentController {

    // UI elements linked from the FXML file
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField majorField;
    @FXML
    private Label resultLabel;

    /**
     * Handles the process of adding a new student to the system.
     * Includes validation for empty fields and duplicate student IDs.
     */
    @FXML
    private void addStudent() {
        // Validate that all input fields are filled
        if (idField.getText().isEmpty() || nameField.getText().isEmpty() || majorField.getText().isEmpty()) {
            resultLabel.setText("All fields are required");
            resultLabel.setStyle("-fx-text-fill: red;");
            return; // Stop execution if fields are empty
        }

        // Check if the ID already exists in the system to prevent duplicates
        for (Student s : App.allStudents) {
            if (s.getID().equals(idField.getText())) {
                resultLabel.setText("Error: Student ID already exists in the system");
                resultLabel.setStyle("-fx-text-fill: red;");
                return; // Stop the process if a duplicate is found
            }
        }

        try {
            // Extract inputs from the UI
            String ID = idField.getText();
            String name = nameField.getText();
            String major = majorField.getText();

            // Add the new student to the global list in memory
            App.allStudents.add(new Student(name, ID, major));

            // Persist the student list to the CSV file
            try (PrintWriter writer = new PrintWriter(new FileWriter("students.csv"))) {
                for (Student s : App.allStudents) {
                    writer.println(s.getID() + "," + s.getName() + "," + s.getMajor() + "," + s.getScore());
                }
            }

            // Provide success feedback and clear fields
            resultLabel.setText("Student added successfully");
            resultLabel.setStyle("-fx-text-fill: green;");
            idField.clear();
            nameField.clear();
            majorField.clear();

        } catch (IOException ex) {
            // Handle potential file I/O errors
            resultLabel.setText("Error saving: " + ex.getMessage());
            resultLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Navigates the user back to the main Dashboard.
     */
    @FXML
    private void goBack() throws IOException {
        App.setRoot("Dashboard");
    }
}