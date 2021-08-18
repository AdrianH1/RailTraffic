/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ch.teko;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Request request = new Request();
        request.doCall("100");

        primaryStage.setTitle("Rail Traffic");
        try {
            File fxmlFile = new File ("./src/main/java/ch/teko/RailTrafficView.fxml");
            Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }


}
