package com.mycompany.studentmanagementsystem.controller;

import com.mycompany.studentmanagementsystem.App;
import com.mycompany.studentmanagementsystem.model.Student;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ReportController implements Initializable {

    @FXML private Label resultLabel;

    // Generates and displays statistical analysis of students' grades
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (App.allStudents.isEmpty()) {
            resultLabel.setText("No data available to generate report.");
            return;
        }

        double total = 0;
        int countPass = 0, countFail = 0, countAbove = 0, countBelow = 0;

        // Calculate total and count various statistics
        for (Student s : App.allStudents) {
            total += s.getScore();
        }
        double average = total / App.allStudents.size();

        for (Student s : App.allStudents) {
            // Updated passing threshold to 60 as discussed
            if (s.getScore() >= 60) countPass++;
            else countFail++;

            if (s.getScore() > average) countAbove++;
            else countBelow++;
        }

        // Display the calculated report in the label
        // Using Locale.US to force English numeric format and prevent encoding issues
        resultLabel.setText(
            String.format(Locale.US, 
                "Total Students: %d" +
                "\nAverage Score: %.2f" +
                "\nPassing Students: %d" +
                "\nFailing Students: %d" +
                "\nAbove Average: %d" +
                "\nBelow Average: %d", 
                App.allStudents.size(), average, countPass, countFail, countAbove, countBelow
            )
        );
    }

    // Navigates back to the Dashboard
    @FXML
    private void goBack() throws IOException {
        App.setRoot("Dashboard");
    }
}