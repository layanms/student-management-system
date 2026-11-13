package com.mycompany.studentmanagementsystem;

import com.mycompany.studentmanagementsystem.model.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    private static Scene scene;
    
    // Global list to store student data
    public static ArrayList<Student> allStudents = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the login screen
        scene = new Scene(loadFXML("Login"), 400, 350);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        
        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();
    }

    // Changes the current view
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Loads FXML files from the resources folder
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}