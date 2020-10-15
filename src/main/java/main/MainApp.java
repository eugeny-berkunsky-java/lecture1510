package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @SneakyThrows
    @Override
    public void start(Stage primaryStage) {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Comp Graphics");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
}
