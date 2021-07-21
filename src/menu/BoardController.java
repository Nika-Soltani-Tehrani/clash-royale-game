package menu;

import ClashRoyal.Creature;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class BoardController implements EventHandler<KeyEvent> {

    final private static double FRAMES_PER_SECOND = 5.0;
    @FXML private Label scoreLabel;
    @FXML private Label levelLabel;
    @FXML private Label gameOverLabel;
    @FXML private BoardGame boardGame;
    @FXML private ListView<Card> cards;
    @FXML private Label xpTimer;
    @FXML private Label gameTime;
    @FXML private BorderPane gameBoard;
    private BoardManager boardManager;
    private static final String levelFile = "levels/level1.txt";
    ObservableList<Card> fourCards = FXCollections.observableArrayList();
    private Timer timer;
    private static int ghostEatingModeCounter;
    private static int STARTTIME = 4;
    private static final int STARTGAME= 180;
    private Timeline gameTimeLine;
    private final IntegerProperty gameSeconds = new SimpleIntegerProperty(STARTGAME);
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private boolean paused;
    private boolean start=false;
    private boolean done = false;
    private boolean desDone = false;
    private boolean miniDes = false;
    private Card selectedCard;
    private static int curColumn;
    private static int curRow;
    private int clearColumn;
    private int clearRow ;
    private BoardManager.CellValue value;
    private BoardManager.CellValue[][] saveCell = new BoardManager.CellValue[32][18];
    private HashMap<Point2D, BoardManager.CellValue> save = new HashMap<>();
    private int i =0;
    private static boolean  end=false;

    public BoardController() {
        this.paused = false;
        this.start = false;
    }

    /**
     * Initialize and update the model and view from the first txt file and starts the timer.
     */
    public void initialize() {
        boardManager = new BoardManager();
        if(!end) {
            update(BoardManager.Direction.NONE);
            ghostEatingModeCounter = 25;
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
                                //return new ImageTextCell();
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
                            //boardManager.setCellValue(curRow,curColumn, BoardManager.CellValue.ELEMENT);
                            boardGame.getCellViews()[curRow][curColumn].setImage(selectedCard.getBoardImage());
                            desDone = false;
                            STARTTIME = timeSeconds.get() - selectedCard.getCost();
                            timeSeconds.set(STARTTIME);
                            timeline.play();
                       /*PathTransition transition1 = newPathTransition(boardGame.getCellViews()[column][row],84.36,342.4,
                                                                        mouseEvent.getX(),mouseEvent.getY());
                       transition1.play();*/
                            done = true;

                        }
                    }
                });
            }
            startTimer();
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
                        if(!end) {
                            update(boardManager.getCurrentDirection());
                        }
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /**
     * Steps the PacManModel, updates the view, updates score and level, displays Game Over/You Won, and instructions of how to play
     * @param direction the most recently inputted direction for PacMan to move in
     */
    private void update(BoardManager.Direction direction) {
        //this.boardManager.step(direction);
        this.boardGame.update(boardManager);
        this.scoreLabel.setText(String.format("Score: %d", this.boardManager.getScore()));
        this.levelLabel.setText(String.format("Level: %d", this.boardManager.getLevel()));
        this.setPosition();
        this.checkActionEnd();
        //this.endGameCheck();
        //if(done){
          //  cards.getItems().remove(cards.getSelectionModel().getSelectedItem());
        //}
        //start = false;
        //done = false;
        /*if (boardManager.isGameOver()) {
            this.gameOverLabel.setText("GAME OVER");
            pause();
        }
        if (boardManager.isYouWon()) {
            this.gameOverLabel.setText("YOU WON!");
        }
        //when PacMan is in ghostEatingMode, count down the ghostEatingModeCounter to reset ghostEatingMode to false when the counter is 0
        if (boardManager.isGhostEatingMode()) {
            ghostEatingModeCounter--;
        }
        if (ghostEatingModeCounter == 0 && boardManager.isGhostEatingMode()) {
            boardManager.setGhostEatingMode(false);
        }*/
    }
    private void updateXp() {
        // increment seconds
        if(timeSeconds.get()>=10){
           timeSeconds.set(10);
        }else {
            int xp = timeSeconds.get();
            timeSeconds.set(xp + 1);
        }
    }
    private void updateTime(){
        int seconds = gameSeconds.get();
        gameSeconds.set(seconds-1);
    }

    /**
     * Takes in user keyboard input to control the movement of creatures and start new games
     * @param keyEvent user's key click
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        boolean keyRecognized = true;
        KeyCode code = keyEvent.getCode();
        BoardManager.Direction direction = BoardManager.Direction.NONE;
        if (code == KeyCode.LEFT) {
            direction = BoardManager.Direction.LEFT;
        } else if (code == KeyCode.RIGHT) {
            direction = BoardManager.Direction.RIGHT;
        } else if (code == KeyCode.UP) {
            direction = BoardManager.Direction.UP;
        } else if (code == KeyCode.DOWN) {
            direction = BoardManager.Direction.DOWN;
        } else if (code == KeyCode.G) {
            pause();
            this.boardManager.startNewGame();
            this.gameOverLabel.setText("");
            paused = false;
            //this.startTimer();
        } else {
            keyRecognized = false;
        }
        if (keyRecognized) {
            keyEvent.consume();
            boardManager.setCurrentDirection(direction);
        }
    }

    /**
     * Pause the timer
     */
    public void pause() {
            this.timer.cancel();
            this.paused = true;
    }

    public double getBoardWidth() {
        return BoardGame.CELL_WIDTH * this.boardGame.getColumnCount();
    }

    public double getBoardHeight() {
        return BoardGame.CELL_WIDTH * this.boardGame.getRowCount();
    }

    public static void setGhostEatingModeCounter() {
        ghostEatingModeCounter = 25;
    }

    public static int getGhostEatingModeCounter() {
        return ghostEatingModeCounter;
    }

    public static String getLevelFile()
    {
        return levelFile;
    }

    public boolean getPaused() {
        return paused;
    }

    public void newPathTransition(ImageView image, double toX, double toY, double column, double row){
        Path path = new Path();
        path.getElements().add(new MoveTo(image.getX(),image.getY()));
        path.getElements().add(new LineTo(toX,toY));
        path.getElements().add(new LineTo(84.36,83.2));
        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setNode(image);
        transition.setDelay(Duration.seconds(1));
        transition.setDuration(Duration.seconds(6));

        //return transition;

    }
    public void move(int desColumn,int desRow){
        if(curColumn!=desColumn || curRow != desRow) {
            if (curColumn <= 9 && curRow > 4) {
                if (curColumn > 4) {
                    curColumn--;
                } else if (curColumn < 4) {
                    curColumn++;
                }
            } if (curColumn > 9 && curRow > 4) {
                if (curColumn < 14) {
                    curColumn++;
                } else if (curColumn > 14) {
                    curColumn--;
                }
            } if ((curColumn == 4 || curColumn == 14)) {
                curRow--;
            }
            miniDes = true;
        }
        else{
            desDone = true;
        }
        clearColumn = curColumn;
        clearRow = curRow;
        value = boardManager.getCellValue(clearRow,clearColumn);
        save.put(new Point2D(clearRow,clearColumn),value);
        System.out.println(value.toString());
    }

    public void setPosition(){
        if(selectedCard != null && done) {
            move(4, 4);
            //elements are needed
            boardManager.setCellValue(curRow,curColumn, BoardManager.CellValue.ELEMENT);
            //boardGame.getCellViews()[curRow][curColumn].setImage(selectedCard.getBoardImage());
            i++;
            //boardManager.setCellValue(clearRow,clearColumn,value);
            //boardGame.getCellViews()[curRow][curColumn].setImage(selectedCard.getBoardImage());
            clearSave();
        }
    }

    public void clearSave(){
        if(i==2) {
            for (Point2D point2D : save.keySet()) {
                boardManager.setCellValue((int) point2D.getX(), (int) point2D.getY(), save.get(point2D));
            }
            i=0;
        }
    }

    public void checkActionEnd(){
        if(desDone){
            //cards.getItems().remove(cards.getSelectionModel().getSelectedItem());
            DeckViewerController.newCards.remove(cards.getSelectionModel().getSelectedItem());
            cards.setItems(getFourCards(DeckViewerController.newCards));
            cards.getSelectionModel().clearSelection();
            done = false;
            start = false;
            desDone = false;

        }
    }

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

    public void endGameCheck() {
        if(gameSeconds.get()==0){
            Stage stage =(Stage) boardGame.getScene().getWindow();
            stage.close();
            timer.cancel();
            gameTimeLine.stop();
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


        }if(boardManager.getCellValue(4,4) != BoardManager.CellValue.REDQUEEN&&
            boardManager.getCellValue(14,14) != BoardManager.CellValue.REDQUEEN&&
            boardManager.getCellValue(3,9) != BoardManager.CellValue.REDKING){


        }if(boardManager.getCellValue(4,4) != BoardManager.CellValue.BLUEQUEEN&&
                boardManager.getCellValue(14,14) != BoardManager.CellValue.BLUEQUEEN&&
                boardManager.getCellValue(3,9) != BoardManager.CellValue.BLUEQUEEN){


        }


    }

}
