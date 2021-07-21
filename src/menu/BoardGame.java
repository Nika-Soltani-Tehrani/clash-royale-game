package menu;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardGame extends Group {
    public final static double CELL_WIDTH = 19.6;

    private int rowCount;
    private int columnCount;
    private ImageView[][] cellViews;
    private Image bArcherImage;
    private Image bArrowImage;
    private Image bBabyDragonImage;
    private Image bBarbarianImage;
    private Image bCannonImage;
    private Image bFireballImage;
    private Image bGiantImage;
    private Image bInfernoTowerImage;
    private Image bMiniPekkaImage;
    private Image bRageImage;
    private Image bValkyarieImage;
    private Image bWizardImage;

    private Image rArcherImage;
    private Image rArrowImage;
    private Image rBabyDragonImage;
    private Image rBarbarianImage;
    private Image rCannonImage;
    private Image rFireballImage;
    private Image rGiantImage;
    private Image rInfernoTowerImage;
    private Image rMiniPekkaImage;
    private Image rRageImage;
    private Image rValkyarieImage;
    private Image rWizardImage;


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



    /**
     * Initializes the values of the image instance variables from files
     */
    public BoardGame() {
        this.bArcherImage = new Image(getClass().getResourceAsStream("/pic/bArcherImage.png"));
        this.bArrowImage = new Image(getClass().getResourceAsStream("/pic/bArrowImage.png"));
        this.bBabyDragonImage = new Image(getClass().getResourceAsStream("/pic/bBabyDragonImage.png"));
        this.bBarbarianImage = new Image(getClass().getResourceAsStream("/pic/bBarbarianImage.png"));
        this.bCannonImage = new Image(getClass().getResourceAsStream("/pic/bCannonImage.png"));
        this.bFireballImage = new Image(getClass().getResourceAsStream("/pic/bFireballImage.png"));
        this.bGiantImage = new Image(getClass().getResourceAsStream("/pic/bGiantImage.png"));
        this.bInfernoTowerImage = new Image(getClass().getResourceAsStream("/pic/bInfernoTowerImage.png"));
        this.bMiniPekkaImage = new Image(getClass().getResourceAsStream("/pic/bMiniPekkaImage.png"));
        this.bRageImage = new Image(getClass().getResourceAsStream("/pic/bRageImage.png"));
        this.bValkyarieImage = new Image(getClass().getResourceAsStream("/pic/bValkyarieImage.png"));
        this.bWizardImage = new Image(getClass().getResourceAsStream("/pic/bWizardImage.png"));

        this.rArcherImage = new Image(getClass().getResourceAsStream("/pic/rArcherImage.png"));
        this.rArrowImage = new Image(getClass().getResourceAsStream("/pic/rArrowImage.png"));
        this.rBabyDragonImage = new Image(getClass().getResourceAsStream("/pic/rBabyDragonImage.png"));
        this.rBarbarianImage = new Image(getClass().getResourceAsStream("/pic/rBarbarianImage.png"));
        this.rCannonImage = new Image(getClass().getResourceAsStream("/pic/rCannonImage.png"));
        this.rFireballImage = new Image(getClass().getResourceAsStream("/pic/rFireballImage.png"));
        this.rGiantImage = new Image(getClass().getResourceAsStream("/pic/rGiantImage.png"));
        this.rInfernoTowerImage = new Image(getClass().getResourceAsStream("/pic/rInfernoTowerImage.png"));
        this.rMiniPekkaImage = new Image(getClass().getResourceAsStream("/pic/rMiniPekkaImage.png"));
        this.rRageImage = new Image(getClass().getResourceAsStream("/pic/rRageImage.png"));
        this.rValkyarieImage = new Image(getClass().getResourceAsStream("/pic/rValkyarieImage.png"));
        this.rWizardImage = new Image(getClass().getResourceAsStream("/pic/rWizardImage.png"));

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
                else if (value == BoardManager.CellValue.bARCHER) {
                    cellViews[row][column].setImage(bArcherImage);
                }
                else if (value == BoardManager.CellValue.bARROW) {
                    cellViews[row][column].setImage(bArrowImage);
                }
                else if (value == BoardManager.CellValue.bBABYDRAGON) {
                    cellViews[row][column].setImage(bBabyDragonImage);
                }
                else if (value == BoardManager.CellValue.bBARBARIAN) {
                    cellViews[row][column].setImage(bBarbarianImage);
                }
                else if (value == BoardManager.CellValue.bCANNON) {
                    cellViews[row][column].setImage(bCannonImage);
                }
                else if (value == BoardManager.CellValue.bFIREBALL) {
                    cellViews[row][column].setImage(bFireballImage);
                }
                else if (value == BoardManager.CellValue.bGIANT) {
                    cellViews[row][column].setImage(bGiantImage);
                }
                else if (value == BoardManager.CellValue.bINFERNOTOWER) {
                    cellViews[row][column].setImage(bMiniPekkaImage);
                }
                else if (value == BoardManager.CellValue.bRAGE) {
                    cellViews[row][column].setImage(bRageImage);
                }
                else if (value == BoardManager.CellValue.bVALKYARIE) {
                    cellViews[row][column].setImage(bValkyarieImage);
                }
                else if (value == BoardManager.CellValue.bWIZARD) {
                    cellViews[row][column].setImage(bWizardImage);
                }
                else if (value == BoardManager.CellValue.rARCHER) {
                    cellViews[row][column].setImage(rArcherImage);
                }
                else if (value == BoardManager.CellValue.rARROW) {
                    cellViews[row][column].setImage(rArrowImage);
                }
                else if (value == BoardManager.CellValue.rBABYDRAGON) {
                    cellViews[row][column].setImage(rBabyDragonImage);
                }
                else if (value == BoardManager.CellValue.rBARBARIAN) {
                    cellViews[row][column].setImage(rBarbarianImage);
                }
                else if (value == BoardManager.CellValue.rCANNON) {
                    cellViews[row][column].setImage(rCannonImage);
                }
                else if (value == BoardManager.CellValue.rFIREBALL) {
                    cellViews[row][column].setImage(rFireballImage);
                }
                else if (value == BoardManager.CellValue.rGIANT) {
                    cellViews[row][column].setImage(rGiantImage);
                }
                else if (value == BoardManager.CellValue.rINFERNOTOWER) {
                    cellViews[row][column].setImage(rMiniPekkaImage);
                }
                else if (value == BoardManager.CellValue.rRAGE) {
                    cellViews[row][column].setImage(rRageImage);
                }
                else if (value == BoardManager.CellValue.rVALKYARIE) {
                    cellViews[row][column].setImage(rValkyarieImage);
                }
                else if (value == BoardManager.CellValue.rWIZARD) {
                    cellViews[row][column].setImage(rWizardImage);
                }
                else {
                    cellViews[row][column].setImage(null);
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
}