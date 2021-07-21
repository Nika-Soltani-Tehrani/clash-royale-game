package menu;

import javafx.geometry.Point2D;
import java.io.*;
import java.util.*;
import java.util.Iterator;

public class BoardManager {

    //ArrayList(object of creatures)
    public enum CellValue {
        GRASS, WATER, ROAD, BLUEQUEEN, BLUEKING, REDQUEEN, REDKING, BRIDGE, TREE, EDGE,DESTROY,
        //red creatures
        rARCHER,rARROW,rBABYDRAGON,rBARBARIAN,rCANNON,rFIREBALL,rGIANT,rINFERNOTOWER,rMINIPEKKA,
        rRAGE,rVALKYARIE,rWIZARD,
        //blue creatures
        bARCHER,bARROW,bBABYDRAGON,bBARBARIAN,bCANNON,bFIREBALL,bGIANT,bINFERNOTOWER,bMINIPEKKA,
        bRAGE,bVALKYARIE,bWIZARD
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    private int rowCount;
    private int columnCount;
    private CellValue[][] grid;
    private int XP;
    private int robotXP;
    private int level;
    public int rPrincess = 2;
    public int bPrincess = 2;
    private static boolean gameOver;
    private static boolean youWon;
    private static boolean ghostEatingMode;
    private static Direction lastDirection;
    private static Direction currentDirection;
    private KingTower blueKingTower = new KingTower("blue",new Point2D(28,9),0,CellValue.BLUEKING);
    private PrincessTower blue1PrincessTower = new PrincessTower("blue",new Point2D(27,4),0,CellValue.BLUEQUEEN);
    private PrincessTower blue2PrincessTower = new PrincessTower("blue",new Point2D(27,14),1,CellValue.BLUEQUEEN);
    private KingTower redKingTower = new KingTower("red",new Point2D(3,9),0,CellValue.REDKING);
    private PrincessTower red1PrincessTower = new PrincessTower("red",new Point2D(4,4),0,CellValue.REDQUEEN);
    private PrincessTower red2PrincessTower = new PrincessTower("red",new Point2D(4,14),1,CellValue.REDQUEEN);
    private ArrayList<Creature> creatures = new ArrayList<>();
    private ArrayList<Creature> enemies = new ArrayList<>();
    private ArrayList<Creature> aliveEnemies = new ArrayList<>();
    private ArrayList<Creature> friends = new ArrayList<>();

    /**
     * Start a new game upon initialization
     */
    public BoardManager() {
        this.startNewGame();

    }

    /**
     * Configure the grid CellValues based on the txt file.
     * "G" indicates grass, "E" indicates the edge of the water, "W" indicates water, "R" indicates
     * the road to move, "BQ" or "RQ" indicates the Blue/Red queen' tower, "RQ" or "RQ" indicates the Blue/Red king' tower
     * ,"T" indicates Trees and "B" indicates Bridge.
     *
     * @param fileName txt file containing the board configuration
     */
    public void initializeLevel(String fileName) {
        rowCount = 32;
        columnCount = 19;

        File file = new File(fileName);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        grid = new CellValue[rowCount][columnCount];
        int row = 0;

        while(sc.hasNextLine()){
            int column = 0;
            String line= sc.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()){
                String value = lineScanner.next();
                CellValue thisValue;
                switch (value) {
                    case "T":
                        thisValue = CellValue.TREE;
                        break;
                    case "W":
                        thisValue = CellValue.WATER;
                        break;
                    case "R":
                        thisValue = CellValue.ROAD;
                        break;
                    case "BQ":
                        thisValue = CellValue.BLUEQUEEN;
                        break;
                    case "BK":
                        thisValue = CellValue.BLUEKING;

                        break;
                    case "RQ":
                        thisValue = CellValue.REDQUEEN;

                        break;
                    case "RK":
                        thisValue = CellValue.REDKING;
                        break;
                    case "B":
                        thisValue = CellValue.BRIDGE;

                        break;
                    case "E":
                        thisValue = CellValue.EDGE;

                        break;
                    default:
                        thisValue = CellValue.GRASS;
                        break;
                }
                grid[row][column] = thisValue;
                column++;
            }
            row++;
        }
        currentDirection = Direction.NONE;
        lastDirection = Direction.NONE;
    }

    /** Initialize values of instance variables and initialize level map
     */
    public void startNewGame() {
        gameOver = false;
        youWon = false;
        rowCount = 0;
        columnCount = 0;
        XP = 0;
        robotXP = 0;
        level = 1;
        makeEnemies();
        initializeEnemies();
        initializeLevel(BoardController.getLevelFile());
    }

    /** Initialize the level map for the next level
     *
     */
    /*public void startNextLevel() {
        if (this.isLevelComplete()) {
            this.level++;
            rowCount = 0;
            columnCount = 0;
            youWon = false;
            try {
                this.initializeLevel(BoardController.getLevelFile(level - 1));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //if there are no levels left in the level array, the game ends
                youWon = true;
                gameOver = true;
                level--;
            }
        }
    }*/

    /**
     * Move PacMan based on the direction indicated by the user (based on keyboard input from the BoardController)
     * @param direction the most recently inputted direction for PacMan to move in
     */
    /*public void movePacman(Direction direction) {
        Point2D potentialPacmanVelocity = changeVelocity(direction);
        Point2D potentialPacmanLocation = pacmanLocation.add(potentialPacmanVelocity);
        //determine whether PacMan should change direction or continue in its most recent direction
        //if most recent direction input is the same as previous direction input, check for walls
        if (direction.equals(lastDirection)) {
            //if moving in the same direction would result in hitting a wall, stop moving
            if (grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.WALL){
                pacmanVelocity = changeVelocity(Direction.NONE);
                setLastDirection(Direction.NONE);
            }
            else {
                pacmanVelocity = potentialPacmanVelocity;
                pacmanLocation = potentialPacmanLocation;
            }
        }
        //if most recent direction input is not the same as previous input, check for walls and corners before going in a new direction
        else {
            //if PacMan would hit a wall with the new direction input, check to make sure he would not hit a different wall if continuing in his previous direction
            if (grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.WALL){
                potentialPacmanVelocity = changeVelocity(lastDirection);
                potentialPacmanLocation = pacmanLocation.add(potentialPacmanVelocity);
                //if changing direction would hit another wall, stop moving
                if (grid[(int) potentialPacmanLocation.getX()][(int) potentialPacmanLocation.getY()] == CellValue.WALL){
                    pacmanVelocity = changeVelocity(Direction.NONE);
                    setLastDirection(Direction.NONE);
                }
                else {
                    pacmanVelocity = changeVelocity(lastDirection);
                    pacmanLocation = pacmanLocation.add(pacmanVelocity);
                }
            }
            //otherwise, change direction and keep moving
            else {
                pacmanVelocity = potentialPacmanVelocity;
                pacmanLocation = potentialPacmanLocation;
                setLastDirection(direction);
            }
        }
    }*/

    /**
     * Move ghosts to follow PacMan as established in moveAGhost() method
     */
    /*public void moveGhosts() {
        Point2D[] ghost1Data = moveAGhost(ghost1Velocity, ghost1Location);
        Point2D[] ghost2Data = moveAGhost(ghost2Velocity, ghost2Location);
        ghost1Velocity = ghost1Data[0];
        ghost1Location = ghost1Data[1];
        ghost2Velocity = ghost2Data[0];
        ghost2Location = ghost2Data[1];
    }*/

    /**
     * Move a ghost to follow PacMan if he is in the same row or column, or move away from PacMan if in ghostEatingMode, otherwise move randomly when it hits a wall.
     * @param velocity the current velocity of the specified ghost
     * @param location the current location of the specified ghost
     * @return an array of Point2Ds containing a new velocity and location for the ghost
     */
    /*public Point2D[] moveAGhost(Point2D velocity, Point2D location){
        Random generator = new Random();
        //if the ghost is in the same row or column as PacMan and not in ghostEatingMode,
        // go in his direction until you get to a wall, then go a different direction
        //otherwise, go in a random direction, and if you hit a wall go in a different random direction
        if (!ghostEatingMode) {
            //check if ghost is in PacMan's column and move towards him
            if (location.getY() == pacmanLocation.getY()) {
                if (location.getX() > pacmanLocation.getX()) {
                    velocity = changeVelocity(Direction.UP);
                } else {
                    velocity = changeVelocity(Direction.DOWN);
                }
                Point2D potentialLocation = location.add(velocity);
                //if the ghost would go offscreen, wrap around
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                //generate new random directions until ghost can move without hitting a wall
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
            //check if ghost is in PacMan's row and move towards him
            else if (location.getX() == pacmanLocation.getX()) {
                if (location.getY() > pacmanLocation.getY()) {
                    velocity = changeVelocity(Direction.LEFT);
                } else {
                    velocity = changeVelocity(Direction.RIGHT);
                }
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
            //move in a consistent random direction until it hits a wall, then choose a new random direction
            else{
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while(grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL){
                    int randomNum = generator.nextInt( 4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
        }
        //if the ghost is in the same row or column as Pacman and in ghostEatingMode, go in the opposite direction
        // until it hits a wall, then go a different direction
        //otherwise, go in a random direction, and if it hits a wall go in a different random direction
        if (ghostEatingMode) {
            if (location.getY() == pacmanLocation.getY()) {
                if (location.getX() > pacmanLocation.getX()) {
                    velocity = changeVelocity(Direction.DOWN);
                } else {
                    velocity = changeVelocity(Direction.UP);
                }
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            } else if (location.getX() == pacmanLocation.getX()) {
                if (location.getY() > pacmanLocation.getY()) {
                    velocity = changeVelocity(Direction.RIGHT);
                } else {
                    velocity = changeVelocity(Direction.LEFT);
                }
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while (grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
            else{
                Point2D potentialLocation = location.add(velocity);
                potentialLocation = setGoingOffscreenNewLocation(potentialLocation);
                while(grid[(int) potentialLocation.getX()][(int) potentialLocation.getY()] == CellValue.WALL){
                    int randomNum = generator.nextInt( 4);
                    Direction direction = intToDirection(randomNum);
                    velocity = changeVelocity(direction);
                    potentialLocation = location.add(velocity);
                }
                location = potentialLocation;
            }
        }
        return new Point2D[]{velocity, location};
    }*/

    /**
     * Connects each Direction to an integer 0-3
     * @param x an integer
     * @return the corresponding Direction
     */
    public Direction intToDirection(int x){
        if (x == 0){
            return Direction.LEFT;
        }
        else if (x == 1){
            return Direction.RIGHT;
        }
        else if(x == 2){
            return Direction.UP;
        }
        else{
            return Direction.DOWN;
        }
    }


    /**
     * Inserts a creature to its starting point location
     */
    public void sendCreatureToHome(Creature creature, CellValue creatureCellValue) {
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                if (grid[row][column] == creatureCellValue) {
                    creature.setLocation(new Point2D(row, column));
                }
            }
        }
        creature.setVelocity(new Point2D(-1, 0));
    }

    /**
     * Updates the model to reflect the movement of creatures. Switches game state to or from ghost-eating mode.
     * @param direction the most recently inputted direction for PacMan to move in
     */
    /*public void step(Direction direction) {
        this.movePacman(direction);
        //if PacMan is on a small dot, delete small dot
        CellValue pacmanLocationCellValue = grid[(int) pacmanLocation.getX()][(int) pacmanLocation.getY()];
        if (pacmanLocationCellValue == CellValue.SMALLDOT) {
            grid[(int) pacmanLocation.getX()][(int) pacmanLocation.getY()] = CellValue.EMPTY;
            dotCount--;
            score += 10;
        }
        //if PacMan is on a big dot, delete big dot and change game state to ghost-eating mode and initialize the counter
        if (pacmanLocationCellValue == CellValue.BIGDOT) {
            grid[(int) pacmanLocation.getX()][(int) pacmanLocation.getY()] = CellValue.EMPTY;
            dotCount--;
            score += 50;
            ghostEatingMode = true;
            BoardController.setGhostEatingModeCounter();
        }
        //send ghost back to ghosthome if PacMan is on a ghost in ghost-eating mode
        if (ghostEatingMode) {
            if (pacmanLocation.equals(ghost1Location)) {
                sendGhost1Home();
                score += 100;
            }
            if (pacmanLocation.equals(ghost2Location)) {
                sendGhost2Home();
                score += 100;
            }
        }
        //game over if PacMan is eaten by a ghost
        else {
            if (pacmanLocation.equals(ghost1Location)) {
                gameOver = true;
                pacmanVelocity = new Point2D(0,0);
            }
            if (pacmanLocation.equals(ghost2Location)) {
                gameOver = true;
                pacmanVelocity = new Point2D(0,0);
            }
        }
        //move ghosts and checks again if ghosts or PacMan are eaten (repeating these checks helps account for even/odd numbers of squares between ghosts and PacMan)
        this.moveGhosts();
        if (ghostEatingMode) {
            if (pacmanLocation.equals(ghost1Location)) {
                sendGhost1Home();
                score += 100;
            }
            if (pacmanLocation.equals(ghost2Location)) {
                sendGhost2Home();
                score += 100;
            }
        }
        else {
            if (pacmanLocation.equals(ghost1Location)) {
                gameOver = true;
                pacmanVelocity = new Point2D(0,0);
            }
            if (pacmanLocation.equals(ghost2Location)) {
                gameOver = true;
                pacmanVelocity = new Point2D(0,0);
            }
        }
        //start a new level if level is complete
        if (this.isLevelComplete()) {
            pacmanVelocity = new Point2D(0,0);
            startNextLevel();
        }
    }*/

    /**
     * Connects each direction to Point2D velocity vectors (Left = (-1,0), Right = (1,0), Up = (0,-1), Down = (0,1))
     * @param direction defines direction of moving of features
     * @return Point2D velocity vector
     */
    public Point2D changeVelocity(Direction direction){
        if(direction == Direction.LEFT){
            return new Point2D(0,-1);//Up
        }
        else if(direction == Direction.RIGHT){
            return new Point2D(0,1);//Down
        }
        else if(direction == Direction.UP){
            return new Point2D(-1,0);//Left
        }
        else if(direction == Direction.DOWN){
            return new Point2D(1,0);//Right
        }
        else{
            return new Point2D(0,0);
        }
    }


    public static boolean isYouWon() {
        return youWon;
    }



    /**
     * @param row of the cell
     * @param column of the cell
     * @return the Cell Value of cell (row, column)
     */
    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        //System.out.println(grid[row][column].toString());
        return this.grid[row][column];
    }


    public int getScore() {
        return XP;
    }

    public Creature enemyFinder(CellValue cellValue,String color)
    {
        if(color.equals("red")){
            if (cellValue == CellValue.bARCHER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("archer"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bARROW) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("arrow"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bBABYDRAGON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("babyDragon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bBARBARIAN) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("barbarian"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bCANNON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("cannon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bFIREBALL) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("fireball"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bGIANT) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("giant"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bINFERNOTOWER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("infernoTower"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bMINIPEKKA) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("miniPekka"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bRAGE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("rage"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bVALKYARIE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("valkyarie"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bWIZARD) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("wizard"))
                        return creature;
                }
            }

        }
        else
        {
            if (cellValue == CellValue.rARCHER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("archer"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rARROW) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("arrow"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rBABYDRAGON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("babyDragon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rBARBARIAN) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("barbarian"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rCANNON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("cannon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rFIREBALL) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("fireball"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rGIANT) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("giant"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rINFERNOTOWER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("infernoTower"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rMINIPEKKA) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("miniPekka"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rRAGE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("rage"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rVALKYARIE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("valkyarie"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rWIZARD) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("wizard"))
                        return creature;
                }
            }
        }
        return null;
    }


    public Creature friendFinder(CellValue cellValue,String color)
    {
        if(color.equals("blue")){
            if (cellValue == CellValue.bARCHER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("archer"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bARROW) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("arrow"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bBABYDRAGON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("babyDragon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bBARBARIAN) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("barbarian"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bCANNON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("cannon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bFIREBALL) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("fireball"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bGIANT) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("giant"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bINFERNOTOWER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("infernoTower"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bMINIPEKKA) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("miniPekka"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bRAGE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("rage"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bVALKYARIE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("valkyarie"))
                        return creature;
                }
            }
            if (cellValue == CellValue.bWIZARD) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("wizard"))
                        return creature;
                }
            }

        }
        else
        {
            if (cellValue == CellValue.rARCHER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("archer"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rARROW) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("arrow"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rBABYDRAGON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("babyDragon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rBARBARIAN) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("barbarian"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rCANNON) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("cannon"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rFIREBALL) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("fireball"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rGIANT) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("giant"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rINFERNOTOWER) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("infernoTower"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rMINIPEKKA) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("miniPekka"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rRAGE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("rage"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rVALKYARIE) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("valkyarie"))
                        return creature;
                }
            }
            if (cellValue == CellValue.rWIZARD) {
                for (Creature creature : creatures) {
                    if (creature.getName().equals("wizard"))
                        return creature;
                }
            }
        }
        return null;
    }

    public int getLevel() {
        return level;
    }



    public CellValue[][] getGrid() {
        return grid;
    }

    public void makeFriends(String name,Point2D location)
    {
        int count;
        int x = (int)location.getX();
        int y = (int)location.getY();
        if (name.equals("archer"))
        {
            Archer bArcher = new Archer("blue",new Point2D(x,y),-1,CellValue.bARCHER);
            count = bArcher.getCount();
            for (int i = 0; i < count; i++)
            {
                bArcher = new Archer("blue",new Point2D(x + i,y),i,CellValue.bARCHER);
                friends.add(bArcher);
                creatures.add(bArcher);
                setCellValue(x+i,y,CellValue.bARCHER);
            }

        }
        if (name.equals("arrow"))
        {
            Arrow bArrow = new Arrow("blue",new Point2D(x,y),CellValue.bARROW);
            friends.add(bArrow);
            creatures.add(bArrow);
            setCellValue(x,y,CellValue.bARROW);
        }
        if (name.equals("babyDragon"))
        {
            BabyDragon bBabyDragon = new BabyDragon("blue",new Point2D(x,y),-1,CellValue.bBABYDRAGON);
            count = bBabyDragon.getCount();
            for (int i = 0; i < count; i++)
            {
                bBabyDragon = new BabyDragon("blue",new Point2D(x + i,y),i,CellValue.bBABYDRAGON);
                friends.add(bBabyDragon);
                creatures.add(bBabyDragon);
                setCellValue(x+i,y,CellValue.bBABYDRAGON);
            }

        }
        if (name.equals("barbarian"))
        {
            Barbarian bBarbarian = new Barbarian("blue",new Point2D(x,y),-1,CellValue.bBARBARIAN);
            count = bBarbarian.getCount();
            for (int i = 0; i < count; i++)
            {
                bBarbarian = new Barbarian("blue",new Point2D(x + i,y),i,CellValue.bBARBARIAN);
                friends.add(bBarbarian);
                creatures.add(bBarbarian);
                setCellValue(x+i,y,CellValue.bBARBARIAN);
            }

        }
        if (name.equals("cannon"))
        {
            Cannon bCannon = new Cannon("blue",new Point2D(x,y),CellValue.bCANNON);
            friends.add(bCannon);
            creatures.add(bCannon);
            setCellValue(x,y,CellValue.bCANNON);
        }
        if (name.equals("fireball"))
        {
            Fireball bFireball = new Fireball("blue",new Point2D(x,y),CellValue.bFIREBALL);
            friends.add(bFireball);
            creatures.add(bFireball);
            setCellValue(x,y,CellValue.bFIREBALL);
        }
        if (name.equals("giant"))
        {
            Giant bGiant = new Giant("blue",new Point2D(x,y),-1,CellValue.bGIANT);
            count = bGiant.getCount();
            for (int i = 0; i < count; i++)
            {
                bGiant = new Giant("blue",new Point2D(x + i,y),i,CellValue.bGIANT);
                friends.add(bGiant);
                creatures.add(bGiant);
                setCellValue(x+i,y,CellValue.bGIANT);
            }

        }
        if (name.equals("infernoTower"))
        {
            InfernoTower bInfernoTower = new InfernoTower("blue",new Point2D(x,y),CellValue.bINFERNOTOWER);
            friends.add(bInfernoTower);
            creatures.add(bInfernoTower);
            setCellValue(x,y,CellValue.bINFERNOTOWER);
        }
        if (name.equals("miniPekka"))
        {
            MiniPekka bMiniPekka = new MiniPekka("blue",new Point2D(x,y),-1,CellValue.bMINIPEKKA);
            count = bMiniPekka.getCount();
            for (int i = 0; i < count; i++)
            {
                bMiniPekka = new MiniPekka("blue",new Point2D(x + i,y),i,CellValue.bMINIPEKKA);
                friends.add(bMiniPekka);
                creatures.add(bMiniPekka);
                setCellValue(x,y,CellValue.bMINIPEKKA);
            }

        }
        if (name.equals("rage"))
        {
            Rage bRage = new Rage("blue",new Point2D(x,y),CellValue.bRAGE);
            friends.add(bRage);
            creatures.add(bRage);
            setCellValue(x,y,CellValue.bRAGE);
        }
        if (name.equals("valkyrie"))
        {
            Valkyarie bValkyarie = new Valkyarie("blue",new Point2D(x,y),-1,CellValue.bVALKYARIE);
            count = bValkyarie.getCount();
            for (int i = 0; i < count; i++)
            {
                bValkyarie = new Valkyarie("blue",new Point2D(x + i,y),i,CellValue.bVALKYARIE);
                friends.add(bValkyarie);
                creatures.add(bValkyarie);
                setCellValue(x+i,y,CellValue.bVALKYARIE);
            }

        }
        if (name.equals("wizard"))
        {
            Wizard bWizard = new Wizard("blue",new Point2D(x,y),-1,CellValue.bWIZARD);
            count = bWizard.getCount();
            for (int i = 0; i < count; i++)
            {
                bWizard = new Wizard("blue",new Point2D(x + i,y),i,CellValue.bWIZARD);
                friends.add(bWizard);
                creatures.add(bWizard);
                setCellValue(x+i,y,CellValue.bWIZARD);
            }

        }
    }

    public void makeEnemies()
    {
        int count;

        Archer rArcher = new Archer("red",new Point2D(3,8),-1,CellValue.rARCHER);
        count = rArcher.getCount();
        for (int i = 0; i < count; i++)
        {
            rArcher = new Archer("red",new Point2D(3 + i,8),i,CellValue.rARCHER);
            enemies.add(rArcher);
            //creatures.add(rArcher);
        }
        Arrow rArrow = new Arrow("red",new Point2D(3,8),CellValue.rARROW);
        enemies.add(rArrow);
        //creatures.add(rArrow);
        BabyDragon rBabyDragon = new BabyDragon("red",new Point2D(3,8),-1,CellValue.rBABYDRAGON);
        count = rBabyDragon.getCount();
        for (int i = 0; i < count; i++)
        {
            rBabyDragon = new BabyDragon("red",new Point2D(3 + i,8),i,CellValue.rBABYDRAGON);
            enemies.add(rBabyDragon);
            //creatures.add(rBabyDragon);
        }
        Barbarian rBarbarian = new Barbarian("red",new Point2D(3,8),-1,CellValue.rBARBARIAN);
        count = rBarbarian.getCount();
        for (int i = 0; i < count; i++)
        {
            rBarbarian = new Barbarian("red",new Point2D(3 + i,8),i,CellValue.rBARBARIAN);
            enemies.add(rBabyDragon);
            //creatures.add(rBarbarian);
        }
        Cannon rCannon = new Cannon("red",new Point2D(3,8),CellValue.rCANNON);
        enemies.add(rCannon);
        //creatures.add(rCannon);

        Fireball rFireball = new Fireball("red",new Point2D(3,8),CellValue.rFIREBALL);
        enemies.add(rFireball);
        //creatures.add(rFireball);
        Giant rGiant = new Giant("red",new Point2D(3,8),-1,CellValue.rGIANT);
        count = rGiant.getCount();
        for (int i = 0; i < count; i++)
        {
            rGiant = new Giant("red",new Point2D(3 + i,8),i,CellValue.rGIANT);
            enemies.add(rGiant);
            //creatures.add(rGiant);
        }
        InfernoTower rInfernoTower = new InfernoTower("red",new Point2D(3,8),CellValue.rINFERNOTOWER);
        enemies.add(rInfernoTower);
        //creatures.add(rInfernoTower);
        MiniPekka rMiniPekka = new MiniPekka("red",new Point2D(3,8),-1,CellValue.rMINIPEKKA);
        count = rMiniPekka.getCount();
        for (int i = 0; i < count; i++)
        {
            rMiniPekka = new MiniPekka("red",new Point2D(3 + i,8),i,CellValue.rMINIPEKKA);
            enemies.add(rMiniPekka);
            //creatures.add(rMiniPekka);
        }
        Rage rRage = new Rage("red",new Point2D(3,8),CellValue.rRAGE);
        enemies.add(rRage);
        //creatures.add(rRage);
        Valkyarie rValkyarie = new Valkyarie("red",new Point2D(3,8),-1,CellValue.rVALKYARIE);
        count = rValkyarie.getCount();
        for (int i = 0; i < count; i++)
        {
            rValkyarie = new Valkyarie("red",new Point2D(3 + i,8),i,CellValue.rVALKYARIE);
            enemies.add(rValkyarie);
            //creatures.add(rValkyarie);
        }
        Wizard rWizard = new Wizard("red",new Point2D(3,8),-1,CellValue.rWIZARD);
        count = rWizard.getCount();
        for (int i = 0; i < count; i++)
        {
            rWizard = new Wizard("red",new Point2D(3 + i,8),i,CellValue.rWIZARD);
            enemies.add(rWizard);
            //creatures.add(rWizard);
        }
    }

    public void aliveCreatures()
    {
        ArrayList<Creature> deadCreatures = new ArrayList<>();
        Iterator<Creature> it = creatures.iterator();
        while (it.hasNext())
        {
            Creature creature = it.next();
            if((creature instanceof Troop) )
            {
                if(((Troop) creature).getHP() == 0)
                {
                    deadCreatures.add(creature);
                    creatures.remove(creature);
                    if (creature.getColor().equals("red"))
                    {
                        enemies.remove(creature);
                        aliveEnemies.remove(creature);
                    }
                    if (creature.getColor().equals("blue"))
                    {
                        friends.remove(creature);
                    }
                    setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.ROAD);
                }
            }
            if((creature instanceof Building) )
            {
                if(((Building) creature).isAlive())
                {
                    if(((Troop) creature).getHP() == 0)
                    {
                        deadCreatures.add(creature);
                        creatures.remove(creature);
                        if (creature.getColor().equals("red"))
                        {
                            enemies.remove(creature);
                            aliveEnemies.remove(creature);
                        }
                        if (creature.getColor().equals("blue"))
                        {
                            friends.remove(creature);
                        }
                    }
                }
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.GRASS);
            }
        }
    }

    /**
     * This method selects four most powerful creatures for the enemy group
     */
    public void initializeEnemies()
    {
        Iterator<Creature> it = creatures.iterator();
        while (it.hasNext())
        {
            Creature creature = it.next();
            if (creature.getName().equals("babyDragon"))
            {
                aliveEnemies.add(creature);
                enemies.remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rBABYDRAGON);
            }
            if (creature.getName().equals("wizard"))
            {
                aliveEnemies.add(creature);
                enemies.remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rWIZARD);
            }
            if (creature.getName().equals("miniPekka"))
            {
                aliveEnemies.add(creature);
                enemies.remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rMINIPEKKA);
            }
            if (creature.getName().equals("giant"))
            {
                aliveEnemies.add(creature);
                enemies.remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rGIANT);
            }
        }
    }

    /**
     * This method is used to check if we need to insert enemies to the game or not.
     */
    public void aliveEnemies()
    {
        Creature creature;
        if (aliveEnemies.size() < 4)
        {
            creature = enemies.get(0);
            aliveEnemies.add(creature);
            enemies.remove(creature);
            setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),creature.getCellValue());
        }
    }

    public void setXPDamage(int level,Creature creature)
    {
        if (level == 1)
        {
            if(creature instanceof Troop)
            {
                if (creature.getName().equals("barbarian"))
                {
                    ((Troop) creature).setDamage(75);
                    ((Troop) creature).setHP(300);
                }
                if (creature.getName().equals("archer"))
                {
                    ((Troop) creature).setDamage(125);
                    ((Troop) creature).setHP(33);
                }
            }
        }

        if (level == 2)
        {

        }

        if (level == 3)
        {

        }

        if (level == 4)
        {

        }

        if (level == 5)
        {

        }

    }

    public void setCellValue(int row , int column, CellValue cellValue){
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        this.grid[row][column] = cellValue;
        //System.out.println(grid[row][column].toString());
    }

    public void move(int desRow,int desColumn, Creature creature){
        int curColumn =(int)creature.getLocation().getX();
        int curRow =(int)creature.getLocation().getY();
        if(curColumn !=desRow || curRow != desColumn) {
            if (desRow == 3 && desColumn == 9) {
                if (curRow != 3) {
                    if (curColumn <= 9 && curRow > 4) {
                        if (curColumn > 4) {
                            curColumn--;
                        } else if (curColumn < 4) {
                            curColumn++;
                        }
                    }
                    if (curColumn > 9 && curRow > 4) {
                        if (curColumn < 14) {
                            curColumn++;
                        } else if (curColumn > 14) {
                            curColumn--;
                        }
                    }
                    if ((curColumn == 4 || curColumn == 14)) {
                        curRow--;
                    }

                } else {
                    if (curColumn < 9) {
                        curColumn++;
                    } else
                        curColumn--;
                }

            } else {
                if (curColumn <= 9 && curRow > 4) {
                    if (curColumn > 4) {
                        curColumn--;
                    } else if (curColumn < 4) {
                        curColumn++;
                    }
                }
                if (curColumn > 9 && curRow > 4) {
                    if (curColumn < 14) {
                        curColumn++;
                    } else if (curColumn > 14) {
                        curColumn--;
                    }
                }
                if ((curColumn == 4 || curColumn == 14)) {
                    curRow--;
                }
            }
            creature.getSaveCell().put(new Point2D(curColumn, curRow), getCellValue(curColumn, curRow));
        }
    }
    public void setPosition(Creature creature){
            newMove(4,4,creature);
            //whichWay(creature);
            this.setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),creature.getCellValue());
            if(creature instanceof Troop){
                ((Troop)creature).setTimeForClear();
            }
            //clearSave(creature);
    }

    public void clearSave(Creature creature){
        if(creature instanceof  Troop) {
            if(((Troop) creature).getTimeForClear()==2) {
                HashMap<Point2D,CellValue> temp = creature.getSaveCell();
                for (Point2D point2D : temp.keySet()) {
                    this.setCellValue((int) point2D.getX(),(int)point2D.getY(),temp.get(point2D));
                }
                ((Troop)creature).newTimeForClear();
            }
        }
    }

    public void whichWay(Creature creature){
        if(creature.getColor().equals("blue")){
            if(creature.getLocation().getX()<=9) {
                if(getCellValue(4,4)==CellValue.REDQUEEN) {
                    move(4, 4, creature);
                }else{
                    move(3,9,creature);
                }
            }else {
                if(getCellValue(4,14)==CellValue.REDQUEEN) {
                    move(4, 14, creature);
                }else {
                    move(3,9,creature);
                }
            }
        }
    }

    public void moveCreatures(){
        for (Creature creature:creatures){
            setPosition(creature);
        }
    }

    public void step(){
        moveCreatures();
    }
    public void newMove(int desRow ,int desColumn, Creature creature) {
        int curColumn =(int) creature.getLocation().getX();
        int curRow = (int) creature.getLocation().getY();
        if (curColumn != desColumn || curRow != desRow) {
            if (curColumn <= 9 && curRow > 4) {
                if (curColumn > 4) {
                    curColumn--;
                } else if (curColumn < 4) {
                    curColumn++;
                }
            }
            if (curColumn > 9 && curRow > 4) {
                if (curColumn < 14) {
                    curColumn++;
                } else if (curColumn > 14) {
                    curColumn--;
                }
            }
            if ((curColumn == 4 || curColumn == 14)) {
                curRow--;
            }

        }
        creature.getSaveCell().put(new Point2D(curColumn, curRow), getCellValue(curColumn, curRow));
        creature.setLocation(new Point2D(curColumn,curRow));

    }

}
