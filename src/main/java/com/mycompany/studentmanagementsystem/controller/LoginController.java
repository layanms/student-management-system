package com.mycompany.studentmanagementsystem.controller;

import com.mycompany.studentmanagementsystem.App;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label resultlbl;

    // Handles the login process by checking the users.csv file
    @FXML
    private void handleLogin() {
        String inputUser = usernameField.getText();
        String inputPass = passwordField.getText();
        boolean authenticated = false;

        // Read the users file to verify credentials
        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                // Check if username and password match
                if (credentials.length == 2) {
                    if (credentials[0].trim().equals(inputUser) && credentials[1].trim().equals(inputPass)) {
                        authenticated = true;
                        break;
                    }
                }
            }

            if (authenticated) {
                // Login successful, navigate to Dashboard
                App.setRoot("Dashboard");
            } else {
                // Show error if login fails
                resultlbl.setText("Username or password are not correct");
            }
        } catch (IOException e) {
            resultlbl.setText("Error: User database not found.");
        }
    }
}