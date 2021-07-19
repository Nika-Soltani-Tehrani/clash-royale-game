package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;


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

    public static void main(String[] args) {
        launch(args);
    }
}
