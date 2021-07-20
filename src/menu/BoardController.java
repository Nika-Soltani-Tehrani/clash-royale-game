package menu;

import ClashRoyal.Creature;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Callback;
import javafx.util.Duration;

import java.util.Timer;

public class BoardController implements EventHandler<KeyEvent> {

    final private static double FRAMES_PER_SECOND = 5.0;
    @FXML private Label scoreLabel;
    @FXML private Label levelLabel;
    @FXML private Label gameOverLabel;
    @FXML private BoardGame boardGame;
    @FXML private ListView<Card> cards;
    private BoardManager boardManager;
    private static final String levelFile = "levels/level1.txt";
    private Timer timer;
    private static int ghostEatingModeCounter;
    private boolean paused;
    private boolean start=false;
    private boolean done = false;
    private String selectedCardName;
    private Card selectedCard;

    public BoardController() {
        this.paused = false;
        this.start = false;
    }

    /**
     * Initialize and update the model and view from the first txt file and starts the timer.
     */
    public void initialize() {
        boardManager = new BoardManager();
        update(BoardManager.Direction.NONE);
        ghostEatingModeCounter = 25;
        if(DeckViewerController.newCards != null){
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
                                    start = true;

                                }
                            }
                    );
            boardGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(mouseEvent.getX());
                    System.out.println(mouseEvent.getY());
                    if(start){
                       int row= (int)(mouseEvent.getX()/19.6);
                       int column = (int)(mouseEvent.getY()/19.6);
                       boardGame.getCellViews()[column][row].setImage(selectedCard.getBoardImage());
                       PathTransition transition1 = newPathTransition(boardGame.getCellViews()[column][row],84.36,342.4,
                                                                        mouseEvent.getX(),mouseEvent.getY());
                       transition1.play();
                       done = true;

                    }
                }
            });
        }
        //startTimer();
    }


    /**
     * Schedules the model to update based on the timer.
     */
    /*private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update(boardManager.getCurrentDirection());
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }*/

    /**
     * Steps the PacManModel, updates the view, updates score and level, displays Game Over/You Won, and instructions of how to play
     * @param direction the most recently inputted direction for PacMan to move in
     */
    private void update(BoardManager.Direction direction) {
        //this.boardManager.step(direction);
        this.boardGame.update(boardManager);
        this.scoreLabel.setText(String.format("Score: %d", this.boardManager.getScore()));
        this.levelLabel.setText(String.format("Level: %d", this.boardManager.getLevel()));
        if(done){
            cards.getItems().remove(cards.getSelectionModel().getSelectedItem());
        }
        start = false;
        done = false;
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

    public static PathTransition newPathTransition(ImageView image, double toX, double toY, double column, double row){
        Path path = new Path();
        path.getElements().add(new MoveTo(image.getX(),image.getY()));
        path.getElements().add(new LineTo(toX,toY));
        path.getElements().add(new LineTo(84.36,83.2));
        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setNode(image);
        transition.setDelay(Duration.seconds(1));
        transition.setDuration(Duration.seconds(6));

        return transition;

    }

    public ObservableList<Card> getFourCards(ObservableList<Card> cards){
        ObservableList<Card> fourCards = FXCollections.observableArrayList();
        for(int i=0;i<4;i++){
            fourCards.add(cards.get(i));
        }
        return fourCards;
    }

}
