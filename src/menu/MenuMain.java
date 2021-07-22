package menu;
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.*;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


/**
 * The type Menu main.
 */
public class MenuMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        File file = new File(".");
        for(String fileNames : file.list()) System.out.println(fileNames);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login Panel");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
