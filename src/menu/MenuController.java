package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.util.Scanner;

/**
 * The type Menu controller.
 */
public class MenuController {
    private static String username;
    private static int level;
    private static String password;
    /**
     * The constant dumb.
     */
    public static boolean dumb=false;

    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblLevel;

    /**
     * Login.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void login(ActionEvent event) throws Exception{
        boolean validLogin = false;
        File file = new File("information/info.txt");
        Scanner read = new Scanner(file);
        String line;
        String[] tokens = null;
        while (read.hasNextLine()) {
            line = read.nextLine();
            tokens = line.split(" ");
            if (txtUsername.getText().equals(tokens[0]) && txtPassword.getText().equals(tokens[1])) {
                validLogin = true;
                lblStatus.setText("logged in successfully.");
                this.username = tokens[0];
                this.level = Integer.parseInt(tokens[2]);
                this.password = tokens[1];
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                primaryStage.setTitle("Menu");
                primaryStage.setScene(new Scene(root, 400, 600));
                primaryStage.show();
                break;
            }
        }
        if(!validLogin){
            lblStatus.setText("login failed.");
        }
        System.out.println(username);
    }

    /**
     * Start game dumb.
     *
     * @param event the event
     * @throws Exception the exception
     */
    @FXML
    public void startGameDumb(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ClashRoyal.fxml"));
        primaryStage.setTitle("ClashRoyal");
        primaryStage.setScene(new Scene(root, 600, 750));
        primaryStage.show();
        dumb = true;

    }

    /**
     * Start game dumber.
     *
     * @param event the event
     * @throws Exception the exception
     */
    @FXML
    public void startGameDumber(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ClashRoyal.fxml"));
        primaryStage.setTitle("ClashRoyal");
        primaryStage.setScene(new Scene(root, 600, 750));
        primaryStage.show();
        dumb = false;

    }

    /**
     * Exit.
     *
     * @param event the event
     */
    public void exit(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


    /**
     * Music.
     */
    public void music(){
        Media sound = new Media(getClass().getResource("music/intro.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Profile.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void profile(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        primaryStage.setTitle("Profile");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();

    }

    /**
     * Update.
     *
     * @param event the event
     */
    public void update(ActionEvent event){
        lblUsername.setText(lblUsername.getText()+this.username);
        lblPassword.setText(lblPassword.getText()+password);
        lblLevel.setText(lblLevel.getText()+Integer.toString(level));
    }

    /**
     * Menu.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void menu(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }

    /**
     * Deck.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void Deck(ActionEvent event)throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeckViewer.fxml"));
        primaryStage.setTitle("Deck");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }

    /**
     * Choose your bot.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void chooseYourBot(ActionEvent event)throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Bot.fxml"));
        primaryStage.setTitle("bot");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }
}
