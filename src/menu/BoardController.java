package menu;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Board controller.
 */
public class BoardController{

    final private static double FRAMES_PER_SECOND = 2.0;
    @FXML private Label scoreLabel;
    @FXML private Label levelLabel;
    @FXML private BoardGame boardGame;
    @FXML private ListView<Card> cards;
    @FXML private Label xpTimer;
    @FXML private Label gameTime;
    @FXML private Label result;
    private int xpResult;
    private BoardManager boardManager;
    private static final String levelFile = "levels/level1.txt";
    /**
     * The Four cards.
     */
    ObservableList<Card> fourCards = FXCollections.observableArrayList();
    private Timer timer;
    private static int STARTTIME = 4;
    private static final int STARTGAME= 180;
    private Timeline gameTimeLine;
    private final IntegerProperty gameSeconds = new SimpleIntegerProperty(STARTGAME);
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private boolean start=false;
    private boolean done = false;
    private boolean desDone = false;
    private Card selectedCard;
    private static int curColumn;
    private static int curRow;
    private BoardManager.CellValue[][] saveCell = new BoardManager.CellValue[32][18];
    private HashMap<Point2D, BoardManager.CellValue> save = new HashMap<>();
    private static boolean  end=false;

    /**
     * Instantiates a new Board controller.
     */
    public BoardController() {
        this.start = false;
    }

    /**
     * Initialize and update the model and view from the first txt file and starts the timer.
     */
    public void initialize() {
        boardManager = new BoardManager();
        if(!end) {
            update();
            gameTime.textProperty().bind(gameSeconds.asString());
            gameTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));
            gameTimeLine.setCycleCount(Animation.INDEFINITE);
            gameSeconds.set(STARTGAME);
            gameTimeLine.play();
            xpTimer.textProperty().bind(timeSeconds.asString());
            timeline = new Timeline(new KeyFrame(Duration.seconds(2), evt -> updateXp()));
            timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
            timeSeconds.set(STARTTIME);
            timeline.play();
            if (DeckViewerController.newCards != null) {
                cards.setItems(getFourCards(DeckViewerController.newCards));
                cards.setCellFactory(
                        new Callback<ListView<Card>, ListCell<Card>>() {
                            @Override
                            public ListCell<Card> call(ListView<Card> listView) {
                                return new CellViewerController();
                            }
                        }
                );
                cards.getSelectionModel().selectedItemProperty().
                        addListener(
                                new ChangeListener<Card>() {

                                    @Override
                                    public void changed(ObservableValue<? extends Card> ov,
                                                        Card oldValue, Card newValue) {
                                        selectedCard = newValue;
                                        if (selectedCard != null) {
                                            if (selectedCard.getCost() <= timeSeconds.get()) {
                                                start = true;
                                            }
                                        }

                                    }
                                }
                        );
                boardGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println(mouseEvent.getX());
                        System.out.println(mouseEvent.getY());
                        if (start) {
                            curColumn = (int) (mouseEvent.getX() / 19.6);
                            curRow = (int) (mouseEvent.getY() / 19.6);
                            boardManager.makeFriends(selectedCard.getTitle(),new Point2D(curRow,curColumn));
                            desDone = false;
                            STARTTIME = timeSeconds.get() - selectedCard.getCost();
                            timeSeconds.set(STARTTIME);
                            timeline.play();
                            done = true;

                        }
                    }
                });
            }
            startTimer();
        }else{
            result.setText("Your score is:"+xpResult);
        }
    }


    /**
     * Schedules the model to update based on the timer.
     */
    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        endGameCheck();
                            update();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /**
     * Steps the PacManModel, updates the view, updates score and level, displays Game Over/You Won, and instructions of how to play
     */
    private void update() {
        this.boardManager.step();
        this.boardGame.update(boardManager);
        this.scoreLabel.setText(String.format("XP: %d", this.boardManager.getScore()));
        this.levelLabel.setText(String.format("Level: %d", this.boardManager.getLevel()));
        this.checkActionEnd();
        this.endGameCheck();
    }
    private void updateXp() {
        if(timeSeconds.get()>=10){
           timeSeconds.set(10);
        }else {
            int xp = timeSeconds.get();
            if(gameSeconds.get()>60) {
                timeSeconds.set(xp + 1);
            }else{
                timeSeconds.set(xp + 2);
            }
        }
    }
    private void updateTime(){
        int seconds = gameSeconds.get();
        gameSeconds.set(seconds-1);
    }


    /**
     * Gets board width.
     *
     * @return the board width
     */
    public double getBoardWidth() {
        return BoardGame.CELL_WIDTH * this.boardGame.getColumnCount();
    }

    /**
     * Gets board height.
     *
     * @return the board height
     */
    public double getBoardHeight() {
        return BoardGame.CELL_WIDTH * this.boardGame.getRowCount();
    }


    /**
     * Gets level file.
     *
     * @return the level file
     */
    public static String getLevelFile()
    {
        return levelFile;
    }

    /**
     * Check action end.
     */
    public void checkActionEnd(){
        if(done){
            DeckViewerController.newCards.remove(selectedCard);
            cards.setItems(getFourCards(DeckViewerController.newCards));
            cards.getSelectionModel().clearSelection();
            done = false;
            start = false;
            desDone = false;

        }
    }

    /**
     * Get four cards observable list.
     *
     * @param cards the cards
     * @return the observable list
     */
    public ObservableList<Card> getFourCards(ObservableList<Card> cards){
        if(done) {
            fourCards.clear();
        }
            if (cards.size() >= 4) {
                for (int i = 0; i < 4; i++) {
                    fourCards.add(cards.get(i));
                }
            } else {
                for(int i=0; i<DeckViewerController.newCards.size() ;i++){
                    fourCards.add(cards.get(i));
                }
            }


        return fourCards;

    }

    /**
     * End game check.
     */
    public void endGameCheck() {
        if(gameSeconds.get()==0 || boardManager.isEndGame()){
            Stage stage =(Stage) boardGame.getScene().getWindow();
            xpResult=boardManager.getXP();
            stage.close();
            timer.cancel();
            gameTimeLine.stop();
            boardManager.setWinnerXP();
            end = true;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/menu/Result.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Result");
                primaryStage.setScene(new Scene(root, 300, 200));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * Back to menu.
     *
     * @param event the event
     * @throws Exception the exception
     */
    public void backToMenu(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }

}
