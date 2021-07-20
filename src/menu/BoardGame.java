package menu;

import ClashRoyal.BoardManager.CellValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardGame extends Group {
    public final static double CELL_WIDTH = 19.6;

    private int rowCount;
    private int columnCount;
    private ImageView[][] cellViews;
    private Image pacmanRightImage;
    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image ghost1Image;
    private Image ghost2Image;
    private Image blueGhostImage;
    private Image wallImage;
    private Image bigDotImage;
    private Image smallDotImage;

    private Image edgeImage;
    private Image treeImage;
    private Image bridgeImage;
    private Image blueKingImage;
    private Image blueQueenImage;
    private Image redKingImage;
    private Image redQueenImage;
    private Image waterImage;
    private Image roadImage;
    private Image grassImage;
    private Image element;


    /**
     * Initializes the values of the image instance variables from files
     */
    public BoardGame() {
        this.pacmanRightImage = new Image(getClass().getResourceAsStream("/pic/pacmanRight.gif"));
        this.pacmanUpImage = new Image(getClass().getResourceAsStream("/pic/pacmanUp.gif"));
        this.pacmanDownImage = new Image(getClass().getResourceAsStream("/pic/pacmanDown.gif"));
        this.pacmanLeftImage = new Image(getClass().getResourceAsStream("/pic/pacmanLeft.gif"));
        this.ghost1Image = new Image(getClass().getResourceAsStream("/pic/redghost.gif"));
        this.ghost2Image = new Image(getClass().getResourceAsStream("/pic/ghost2.gif"));
        this.blueGhostImage = new Image(getClass().getResourceAsStream("/pic/blueghost.gif"));
        this.wallImage = new Image(getClass().getResourceAsStream("/pic/wall.png"));
        this.bigDotImage = new Image(getClass().getResourceAsStream("/pic/whitedot.png"));
        this.smallDotImage = new Image(getClass().getResourceAsStream("/pic/smalldot.png"));



        this.edgeImage = new Image(getClass().getResourceAsStream("/pic/EDGE.png"));
        this.treeImage = new Image(getClass().getResourceAsStream("/pic/TREE.png"));
        this.bridgeImage = new Image(getClass().getResourceAsStream("/pic/BRIDGE.png"));
        this.blueKingImage = new Image(getClass().getResourceAsStream("/pic/BLUEKING.png"));
        this.blueQueenImage = new Image(getClass().getResourceAsStream("/pic/BLUEQUEEN.png"));
        this.redKingImage = new Image(getClass().getResourceAsStream("/pic/REDKING.png"));
        this.redQueenImage = new Image(getClass().getResourceAsStream("/pic/REDQUEEN.png"));
        this.roadImage = new Image(getClass().getResourceAsStream("/pic/ROAD.png"));
        this.waterImage = new Image(getClass().getResourceAsStream("/pic/WATER.png"));
        this.grassImage = new Image(getClass().getResourceAsStream("/pic/GRASS.png"));
        this.element = new Image(getClass().getResourceAsStream("/images/board/archers.png"));
    }

    /**
     * Constructs an empty grid of ImageViews
     */
    private void initializeGrid() {
        cellViews = new ImageView[rowCount][columnCount];
        System.out.println(rowCount);
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView imageView = new ImageView();
                imageView.setX(column * CELL_WIDTH);
                imageView.setY(row * CELL_WIDTH);
                imageView.setFitWidth(CELL_WIDTH);
                imageView.setFitHeight(CELL_WIDTH);
                cellViews[row][column] = imageView;
                this.getChildren().add(imageView);
            }
        }
    }

    /** Updates the view to reflect the state of the model
     *
     *
     */
    public void update(BoardManager model) {
        //for each ImageView, set the image to correspond with the CellValue of that cell
        for (int row = 0; row < rowCount; row++){
            for (int column = 0; column < columnCount; column++){
                BoardManager.CellValue value = model.getCellValue(row, column);
                if (value == BoardManager.CellValue.GRASS) {
                    cellViews[row][column].setImage(grassImage);
                }
                else if (value == BoardManager.CellValue.WATER) {
                    cellViews[row][column].setImage(waterImage);
                }
                else if (value == BoardManager.CellValue.ROAD) {
                    cellViews[row][column].setImage(roadImage);
                }
                else if (value == BoardManager.CellValue.BLUEQUEEN) {
                    cellViews[row][column].setImage(blueQueenImage);
                }
                else if (value == BoardManager.CellValue.BLUEKING) {
                    cellViews[row][column].setImage(blueKingImage);
                }
                else if (value == BoardManager.CellValue.REDQUEEN) {
                    cellViews[row][column].setImage(redQueenImage);
                }
                else if (value == BoardManager.CellValue.REDKING) {
                    cellViews[row][column].setImage(redKingImage);
                }
                else if (value == BoardManager.CellValue.BRIDGE) {
                    cellViews[row][column].setImage(bridgeImage);
                }
                else if (value == BoardManager.CellValue.EDGE) {
                    cellViews[row][column].setImage(edgeImage);
                }
                else if (value == BoardManager.CellValue.TREE) {
                    cellViews[row][column].setImage(treeImage);
                }
                else if(value == BoardManager.CellValue.ELEMENT) {
                    cellViews[row][column].setImage(element);
                }
                //check which direction PacMan is going in and display the corresponding image
                /*if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY()) {
                    if ((BoardManager.getLastDirection() == BoardManager.Direction.RIGHT || BoardManager.getLastDirection() == BoardManager.Direction.NONE)) {
                        cellViews[row][column].setImage(pacmanRightImage);
                    }
                    else if (BoardManager.getLastDirection() == BoardManager.Direction.LEFT) {
                        cellViews[row][column].setImage(pacmanLeftImage);
                    }
                    else if (BoardManager.getLastDirection() == BoardManager.Direction.UP) {
                        cellViews[row][column].setImage(pacmanUpImage);
                    }
                    else if (BoardManager.getLastDirection() == BoardManager.Direction.DOWN) {
                        cellViews[row][column].setImage(pacmanDownImage);
                    }
                }

                Image ghostNormal;
                Image ghostEating;
                if (row == model.getGhost1Location().getX() && column == model.getGhost1Location().getY()) {
                    ghostNormal = ghost1Image;
                    ghostEating = blueGhostImage;
                }
                else if (row == model.getGhost2Location().getX() && column == model.getGhost2Location().getY()){
                    ghostNormal = ghost2Image;
                    ghostEating = blueGhostImage;
                }
                else
                    continue;

                //make ghosts "blink" towards the end of ghostEatingMode (display regular ghost images on alternating updates of the counter)
                if (BoardManager.isGhostEatingMode()) {
                    if (BoardController.getGhostEatingModeCounter() <= 6 && (BoardController.getGhostEatingModeCounter() % 2 == 0)) {
                        cellViews[row][column].setImage(ghostNormal);
                    }
                    //display blue ghosts in ghostEatingMode
                    else {
                        cellViews[row][column].setImage(ghostEating);
                    }
                }
                //dispaly regular ghost images otherwise
                else {
                    cellViews[row][column].setImage(ghostNormal);
                }*/
            }
        }
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        this.initializeGrid();
    }
    public ImageView[][] getCellViews(){
        return cellViews;
    }
    public Image getImageTree(){
        return treeImage;
    }
}
