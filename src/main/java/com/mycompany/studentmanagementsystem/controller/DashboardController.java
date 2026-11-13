package com.mycompany.studentmanagementsystem.controller;

import com.mycompany.studentmanagementsystem.App;
import com.mycompany.studentmanagementsystem.model.Student;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label resultLabel;

    // Navigation buttons
    @FXML
    private void goToAddStudent() throws IOException {
        App.setRoot("AddStudent");
    }

    @FXML
    private void goToViewStudents() throws IOException {
        App.setRoot("ViewStudents");
    }

    @FXML
    private void goToAddGrade() throws IOException {
        App.setRoot("AddGrade");
    }

    @FXML
    private void goToReport() throws IOException {
        App.setRoot("Report");
    }

    // File handling: Saves current student list to CSV
    @FXML
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.csv"))) {
            for (Student s : App.allStudents) {
               writer.println(s.getID() + "," + s.getName() + "," + s.getMajor() + "," + s.getScore());
            }
            resultLabel.setText("Saved " + App.allStudents.size() + " students successfully");
        } catch (IOException e) {
            resultLabel.setText("Error saving: " + e.getMessage());
        }
    }

    // File handling: Loads student data from CSV
    @FXML
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.csv"))) {
            App.allStudents.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Create new Student object from CSV data
                Student s = new Student(parts[1],(parts[0]), parts[2]);
                s.setScore(Double.parseDouble(parts[3]));
                App.allStudents.add(s);
            }
            resultLabel.setText("Loaded " + App.allStudents.size() + " students successfully");
        } catch (Exception e) {
            resultLabel.setText("Error loading file.");
        }
    }
}
