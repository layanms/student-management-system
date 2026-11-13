package com.mycompany.studentmanagementsystem.controller;

import com.mycompany.studentmanagementsystem.App;
import com.mycompany.studentmanagementsystem.model.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class AddGradeController implements Initializable {

    // Linking UI elements from FXML
    @FXML
    private ComboBox<String> studentIds;
    @FXML
    private TextField gradeField;
    @FXML
    private Label resultLabel;

    // Initializes the ComboBox with existing student IDs
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentIds.getItems().clear();
        if (App.allStudents.isEmpty()) {
            resultLabel.setText("No students, please add students first");
        } else {
            for (Student s : App.allStudents) {
                studentIds.getItems().add(s.getID());
            }
            studentIds.getSelectionModel().select(0);
        }
    }

    // Handles saving the grade to the selected student
    @FXML
    private void addGrade() {
        if (App.allStudents.isEmpty()) {
            resultLabel.setText("No students found");
        } else if (gradeField.getText().isEmpty()) {
            resultLabel.setText("Please enter a grade");
        } else {
            try {
                String id = studentIds.getSelectionModel().getSelectedItem();
                double grade = Double.parseDouble(gradeField.getText());
// Validate grade is within acceptable range
                if (grade < 0 || grade > 100) {
                    resultLabel.setText("Grade must be between 0 and 100");
                    resultLabel.setStyle("-fx-text-fill: red;");

                    return;
                }

                // Find the student by ID and update their score
                for (Student s : App.allStudents) {
                    if (s.getID().equals(id)) {
                        s.setScore(grade);
                        resultLabel.setText("Grade added successfully");
                        gradeField.clear();
                        // Auto-save after adding a grade
                        try (PrintWriter writer = new PrintWriter(new FileWriter("students.csv"))) {
                            for (Student st : App.allStudents) {
                                writer.println(st.getID() + "," + st.getName() + "," + st.getMajor() + "," + st.getScore());
                            }
                        } catch (IOException ex) {
                            resultLabel.setText("Error saving: " + ex.getMessage());
                        }
                        return; // Exit after successful update
                    }
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Grade must be a number");
            }
        }
    }

    // Navigates back to the Dashboard
    @FXML
    private void goBack() throws IOException {
        App.setRoot("Dashboard");
    }
}
