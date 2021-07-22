package menu;

import javafx.geometry.Point2D;
import java.io.*;
import java.util.*;
import java.util.Iterator;

/**
 * The type Board manager.
 */
public class BoardManager {

    /**
     * The enum Cell value.
     */
//ArrayList(object of creatures)
    public enum CellValue {
        /**
         * Grass cell value.
         */
        GRASS,
        /**
         * Water cell value.
         */
        WATER,
        /**
         * Road cell value.
         */
        ROAD,
        /**
         * Bluequeen cell value.
         */
        BLUEQUEEN,
        /**
         * Blueking cell value.
         */
        BLUEKING,
        /**
         * Redqueen cell value.
         */
        REDQUEEN,
        /**
         * Redking cell value.
         */
        REDKING,
        /**
         * Bridge cell value.
         */
        BRIDGE,
        /**
         * Tree cell value.
         */
        TREE,
        /**
         * Edge cell value.
         */
        EDGE,
        /**
         * Destroy cell value.
         */
        DESTROY,
        /**
         * The R archer.
         */
//red creatures
        rARCHER,
        /**
         * R arrow cell value.
         */
        rARROW,
        /**
         * R babydragon cell value.
         */
        rBABYDRAGON,
        /**
         * R barbarian cell value.
         */
        rBARBARIAN,
        /**
         * R cannon cell value.
         */
        rCANNON,
        /**
         * R fireball cell value.
         */
        rFIREBALL,
        /**
         * R giant cell value.
         */
        rGIANT,
        /**
         * R infernotower cell value.
         */
        rINFERNOTOWER,
        /**
         * R minipekka cell value.
         */
        rMINIPEKKA,
        /**
         * R rage cell value.
         */
        rRAGE,
        /**
         * R valkyarie cell value.
         */
        rVALKYARIE,
        /**
         * R wizard cell value.
         */
        rWIZARD,
        /**
         * The B archer.
         */
//blue creatures
        bARCHER,
        /**
         * B arrow cell value.
         */
        bARROW,
        /**
         * B babydragon cell value.
         */
        bBABYDRAGON,
        /**
         * B barbarian cell value.
         */
        bBARBARIAN,
        /**
         * B cannon cell value.
         */
        bCANNON,
        /**
         * B fireball cell value.
         */
        bFIREBALL,
        /**
         * B giant cell value.
         */
        bGIANT,
        /**
         * B infernotower cell value.
         */
        bINFERNOTOWER,
        /**
         * B minipekka cell value.
         */
        bMINIPEKKA,
        /**
         * B rage cell value.
         */
        bRAGE,
        /**
         * B valkyarie cell value.
         */
        bVALKYARIE,
        /**
         * B wizard cell value.
         */
        bWIZARD
    }

    /**
     * The enum Direction.
     */
    public enum Direction {
        /**
         * Up direction.
         */
        UP,
        /**
         * Down direction.
         */
        DOWN,
        /**
         * Left direction.
         */
        LEFT,
        /**
         * Right direction.
         */
        RIGHT,
        /**
         * None direction.
         */
        NONE
    }

    private Robot robot;
    private int rowCount;
    private int columnCount;
    private CellValue[][] grid;
    private int XP;
    private int robotXP;
    private int crown;
    private int robotCrown;
    private boolean endGame = false;
    private int level;
    /**
     * The R princess.
     */
    public int rPrincess = 2;
    /**
     * The B princess.
     */
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
    //private ArrayList<Creature> enemies = new ArrayList<>();
    //private ArrayList<Creature> aliveEnemies = new ArrayList<>();
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

    /**
     * Initialize values of instance variables and initialize level map
     */
    public void startNewGame() {
        gameOver = false;
        youWon = false;
        rowCount = 0;
        columnCount = 0;
        crown = 0;
        robotCrown = 0;
        XP = 300;//bayad az file khande shavad
        robotXP = 0;
        level = 1;//bayad az file khande shavad
        if (availableLevel(XP,level)){
            if (!MenuController.dumb) {
                robot = new SimpleRobot(this);
                initializeEnemies();
                setTowerXPDamage(level);
                initializeLevel(BoardController.getLevelFile());
            }
            if (MenuController.dumb) {
                robot = new GeniusRobot(this);
                initializeEnemies();
                setTowerXPDamage(level);
                initializeLevel(BoardController.getLevelFile());
            }
        }
    }

    /**
     * Available level boolean.
     *
     * @param XP    the xp
     * @param level the level
     * @return the boolean
     */
    public boolean availableLevel(int XP,int level)
    {
        if (level == 1)
        {
            if (XP >=300)
                return true;
            else
            {
                System.out.println("Level is not available for you!");
                return false;
            }
        }
        if (level == 2)
        {
            if (XP >=500)
                return true;
            else
            {
                System.out.println("Level is not available for you!");
                return false;
            }
        }
        if (level == 3)
        {
            if (XP >=900)
                return true;
            else
            {
                System.out.println("Level is not available for you!");
                return false;
            }
        }
        if (level == 4)
        {
            if (XP >=1700)
                return true;
            else
            {
                System.out.println("Level is not available for you!");
                return false;
            }
        }
        if (level == 5)
        {
            if (XP >=2500)
                return true;
            else
            {
                System.out.println("Level is not available for you!");
                return false;
            }
        }
        return false;
    }

    /**
     * Sets winner xp.
     */
    public void setWinnerXP()
    {
        if (crown > robotCrown)
        {
            XP += 200;
            robotXP += 70;
        }
        if (crown < robotCrown)
        {
            XP += 70;
            robotXP += 200;
        }
        if (crown == robotCrown)
        {
            int friendHP = blueKingTower.getHP() + blue1PrincessTower.getHP() + blue2PrincessTower.getHP();
            int robotHP = redKingTower.getHP() + red1PrincessTower.getHP() + red2PrincessTower.getHP();
            if (friendHP > robotHP)
            {
                XP += 200;
                robotXP += 70;
            }
            if (friendHP < robotHP)
            {
                XP += 70;
                robotXP += 200;
            }
        }
    }

    /**
     * Gets cell value.
     *
     * @param row    of the cell
     * @param column of the cell
     * @return the Cell Value of cell (row, column)
     */
    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }


    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return XP;
    }

    /**
     * Enemy finder creature.
     *
     * @param cellValue the cell value
     * @param color     the color
     * @return the creature
     */
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


    /**
     * Friend finder creature.
     *
     * @param cellValue the cell value
     * @param color     the color
     * @return the creature
     */
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

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }


    /**
     * Get grid cell value [ ] [ ].
     *
     * @return the cell value [ ] [ ]
     */
    public CellValue[][] getGrid() {
        return grid;
    }

    /**
     * Make friends.
     *
     * @param name     the name
     * @param location the location
     */
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
                bArcher.getSaveCell().put(new Point2D(bArcher.getLocation().getX(),bArcher.getLocation().getY()),
                        getCellValue((int)bArcher.getLocation().getX(),(int)bArcher.getLocation().getY()));
                setCellValue(x+i,y,CellValue.bARCHER);
                setXPDamage(level,bArcher);
            }

        }
        if (name.equals("arrow"))
        {
            Arrow bArrow = new Arrow("blue",new Point2D(x,y),CellValue.bARROW);
            friends.add(bArrow);
            creatures.add(bArrow);
            bArrow.getSaveCell().put(new Point2D(bArrow.getLocation().getX(),bArrow.getLocation().getY()),
                    getCellValue((int)bArrow.getLocation().getX(),(int)bArrow.getLocation().getY()));
            setCellValue(x,y,CellValue.bARROW);
            setXPDamage(level,bArrow);
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
                bBabyDragon.getSaveCell().put(new Point2D(bBabyDragon.getLocation().getX(),bBabyDragon.getLocation().getY()),
                        getCellValue((int)bBabyDragon.getLocation().getX(),(int)bBabyDragon.getLocation().getY()));
                setCellValue(x+i,y,CellValue.bBABYDRAGON);
                setXPDamage(level,bBabyDragon);
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
                bBarbarian.getSaveCell().put(new Point2D(bBarbarian.getLocation().getX(),bBarbarian.getLocation().getY()),
                        getCellValue((int)bBarbarian.getLocation().getX(),(int)bBarbarian.getLocation().getY()));
                setCellValue(x+i,y,CellValue.bBARBARIAN);
                setXPDamage(level,bBarbarian);
            }

        }
        if (name.equals("cannon"))
        {
            Cannon bCannon = new Cannon("blue",new Point2D(x,y),CellValue.bCANNON);
            friends.add(bCannon);
            creatures.add(bCannon);
            bCannon.getSaveCell().put(new Point2D(bCannon.getLocation().getX(),bCannon.getLocation().getY()),
                    getCellValue((int)bCannon.getLocation().getX(),(int)bCannon.getLocation().getY()));
            setCellValue(x,y,CellValue.bCANNON);
            setXPDamage(level,bCannon);
        }
        if (name.equals("fireball"))
        {
            Fireball bFireball = new Fireball("blue",new Point2D(x,y),CellValue.bFIREBALL);
            friends.add(bFireball);
            creatures.add(bFireball);
            bFireball.getSaveCell().put(new Point2D(bFireball.getLocation().getX(),bFireball.getLocation().getY()),
                    getCellValue((int)bFireball.getLocation().getX(),(int)bFireball.getLocation().getY()));
            setCellValue(x,y,CellValue.bFIREBALL);
            setXPDamage(level,bFireball);
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
                bGiant.getSaveCell().put(new Point2D(bGiant.getLocation().getX(),bGiant.getLocation().getY()),
                        getCellValue((int)bGiant.getLocation().getX(),(int)bGiant.getLocation().getY()));
                setCellValue(x+i,y,CellValue.bGIANT);
                setXPDamage(level,bGiant);
            }

        }
        if (name.equals("infernoTower"))
        {
            InfernoTower bInfernoTower = new InfernoTower("blue",new Point2D(x,y),CellValue.bINFERNOTOWER);
            friends.add(bInfernoTower);
            creatures.add(bInfernoTower);
            bInfernoTower.getSaveCell().put(new Point2D(bInfernoTower.getLocation().getX(),bInfernoTower.getLocation().getY()),
                    getCellValue((int)bInfernoTower.getLocation().getX(),(int)bInfernoTower.getLocation().getY()));
            setCellValue(x,y,CellValue.bINFERNOTOWER);
            setXPDamage(level,bInfernoTower);
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
                bMiniPekka.getSaveCell().put(new Point2D(bMiniPekka.getLocation().getX(),bMiniPekka.getLocation().getY()),
                        getCellValue((int)bMiniPekka.getLocation().getX(),(int)bMiniPekka.getLocation().getY()));
                setCellValue(x+i,y,CellValue.bMINIPEKKA);
                setXPDamage(level,bMiniPekka);
            }

        }
        if (name.equals("rage"))
        {
            Rage bRage = new Rage("blue",new Point2D(x,y),CellValue.bRAGE);
            friends.add(bRage);
            creatures.add(bRage);
            bRage.getSaveCell().put(new Point2D(bRage.getLocation().getX(),bRage.getLocation().getY()),
                    getCellValue((int)bRage.getLocation().getX(),(int)bRage.getLocation().getY()));
            setCellValue(x,y,CellValue.bRAGE);
            setXPDamage(level,bRage);
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
                bValkyarie.getSaveCell().put(new Point2D(bValkyarie.getLocation().getX(),bValkyarie.getLocation().getY()),
                        getCellValue((int)bValkyarie.getLocation().getX(),(int)bValkyarie.getLocation().getY()));
                setCellValue(x+i,y,CellValue.bVALKYARIE);
                setXPDamage(level,bValkyarie);
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
                bWizard.getSaveCell().put(new Point2D(bWizard.getLocation().getX(),bWizard.getLocation().getY()),
                        getCellValue((int)bWizard.getLocation().getX(),(int)bWizard.getLocation().getY()));
                setCellValue(x+i,y,CellValue.bWIZARD);
                setXPDamage(level,bWizard);
            }

        }
    }

    /*public void makeEnemies()
    {
        int count;

        Archer rArcher = new Archer("red",new Point2D(3,8),-1,CellValue.rARCHER);
        count = rArcher.getCount();
        for (int i = 0; i < count; i++)
        {
            rArcher = new Archer("red",new Point2D(3 + i,8),i,CellValue.rARCHER);
            enemies.add(rArcher);
            setXPDamage(level,rArcher);
            //creatures.add(rArcher);
        }
        Arrow rArrow = new Arrow("red",new Point2D(3,8),CellValue.rARROW);
        enemies.add(rArrow);
        setXPDamage(level,rArrow);
        //creatures.add(rArrow);
        BabyDragon rBabyDragon = new BabyDragon("red",new Point2D(3,8),-1,CellValue.rBABYDRAGON);
        count = rBabyDragon.getCount();
        for (int i = 0; i < count; i++)
        {
            rBabyDragon = new BabyDragon("red",new Point2D(3 + i,8),i,CellValue.rBABYDRAGON);
            enemies.add(rBabyDragon);
            setXPDamage(level,rBabyDragon);
            //creatures.add(rBabyDragon);
        }
        Barbarian rBarbarian = new Barbarian("red",new Point2D(3,8),-1,CellValue.rBARBARIAN);
        count = rBarbarian.getCount();
        for (int i = 0; i < count; i++)
        {
            rBarbarian = new Barbarian("red",new Point2D(3 + i,8),i,CellValue.rBARBARIAN);
            enemies.add(rBabyDragon);
            setXPDamage(level,rBabyDragon);
            //creatures.add(rBarbarian);
        }
        Cannon rCannon = new Cannon("red",new Point2D(3,8),CellValue.rCANNON);
        enemies.add(rCannon);
        setXPDamage(level,rCannon);
        //creatures.add(rCannon);

        Fireball rFireball = new Fireball("red",new Point2D(3,8),CellValue.rFIREBALL);
        enemies.add(rFireball);
        setXPDamage(level,rFireball);
        //creatures.add(rFireball);
        Giant rGiant = new Giant("red",new Point2D(3,8),-1,CellValue.rGIANT);
        count = rGiant.getCount();
        for (int i = 0; i < count; i++)
        {
            rGiant = new Giant("red",new Point2D(3 + i,8),i,CellValue.rGIANT);
            enemies.add(rGiant);
            setXPDamage(level,rGiant);
            //creatures.add(rGiant);
        }
        InfernoTower rInfernoTower = new InfernoTower("red",new Point2D(3,8),CellValue.rINFERNOTOWER);
        enemies.add(rInfernoTower);
        setXPDamage(level,rInfernoTower);
        //creatures.add(rInfernoTower);
        MiniPekka rMiniPekka = new MiniPekka("red",new Point2D(3,8),-1,CellValue.rMINIPEKKA);
        count = rMiniPekka.getCount();
        for (int i = 0; i < count; i++)
        {
            rMiniPekka = new MiniPekka("red",new Point2D(3 + i,8),i,CellValue.rMINIPEKKA);
            enemies.add(rMiniPekka);
            setXPDamage(level,rMiniPekka);
            //creatures.add(rMiniPekka);
        }
        Rage rRage = new Rage("red",new Point2D(3,8),CellValue.rRAGE);
        enemies.add(rRage);
        setXPDamage(level,rRage);
        //creatures.add(rRage);
        Valkyarie rValkyarie = new Valkyarie("red",new Point2D(3,8),-1,CellValue.rVALKYARIE);
        count = rValkyarie.getCount();
        for (int i = 0; i < count; i++)
        {
            rValkyarie = new Valkyarie("red",new Point2D(3 + i,8),i,CellValue.rVALKYARIE);
            enemies.add(rValkyarie);
            setXPDamage(level,rValkyarie);
            //creatures.add(rValkyarie);
        }
        Wizard rWizard = new Wizard("red",new Point2D(3,8),-1,CellValue.rWIZARD);
        count = rWizard.getCount();
        for (int i = 0; i < count; i++)
        {
            rWizard = new Wizard("red",new Point2D(3 + i,8),i,CellValue.rWIZARD);
            enemies.add(rWizard);
            setXPDamage(level,rWizard);
            //creatures.add(rWizard);
        }
    }*/

    /**
     * Alive creatures.
     */
    public void aliveCreatures()
    {
        ArrayList<Creature> deadCreatures = new ArrayList<>();
        Iterator<Creature> it = creatures.iterator();
        while (it.hasNext())
        {
            Creature creature = it.next();
            if((creature instanceof Troop) )
            {
                if(!((Troop) creature).isAlive())
                {
                    deadCreatures.add(creature);
                    creatures.remove(creature);
                    if (creature.getColor().equals("red"))
                    {
                        robot.getEnemies().remove(creature);
                        robot.getAliveEnemies().remove(creature);
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
                    deadCreatures.add(creature);
                    creatures.remove(creature);
                    if (creature.getColor().equals("red"))
                    {
                        robot.getEnemies().remove(creature);
                        robot.getAliveEnemies().remove(creature);
                    }
                    if (creature.getColor().equals("blue"))
                    {
                        friends.remove(creature);
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
                robot.getAliveEnemies().add(creature);
                robot.getEnemies().remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rBABYDRAGON);
            }
            if (creature.getName().equals("wizard"))
            {
                robot.getAliveEnemies().add(creature);
                robot.getEnemies().remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rWIZARD);
            }
            if (creature.getName().equals("miniPekka"))
            {
                robot.getAliveEnemies().add(creature);
                robot.getEnemies().remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rMINIPEKKA);
            }
            if (creature.getName().equals("giant"))
            {
                robot.getAliveEnemies().add(creature);
                robot.getEnemies().remove(creature);
                creatures.add(creature);
                setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),CellValue.rGIANT);
            }
        }
    }

    /*public void aliveEnemies()
    {
        Creature creature;
        if (robot.getAliveEnemies().size() < 4)
        {
            creature = robot.getEnemies().get(0);
            robot.getAliveEnemies().add(creature);
            robot.getEnemies().remove(creature);
            setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),creature.getCellValue());
        }
    }*/

    /**
     * This method is used to check if we need to insert enemies to the game or not.
     */
    public void aliveTowers()
    {
        if (!blueKingTower.isAlive()) {
            robotCrown += 3;
            endGame = true;
            setCellValue((int)blueKingTower.getLocation().getX(),(int)blueKingTower.getLocation().getY(),CellValue.GRASS);
        }
        if (!blue1PrincessTower.isAlive()) {
            crown += 1;
            setCellValue((int)blue1PrincessTower.getLocation().getX(),(int)blue1PrincessTower.getLocation().getY(),CellValue.GRASS);
        }
        if (!blue2PrincessTower.isAlive()) {
            crown += 1;
            setCellValue((int)blue2PrincessTower.getLocation().getX(),(int)blue2PrincessTower.getLocation().getY(),CellValue.GRASS);
        }
        if (!redKingTower.isAlive()) {
            setCellValue((int)redKingTower.getLocation().getX(),(int)redKingTower.getLocation().getY(),CellValue.GRASS);
            crown += 3;
            endGame = true;
        }
        if (!red1PrincessTower.isAlive()) {
            setCellValue((int)red1PrincessTower.getLocation().getX(),(int)red1PrincessTower.getLocation().getY(),CellValue.GRASS);
            crown += 1;
        }
        if (!red2PrincessTower.isAlive()) {
            setCellValue((int)red2PrincessTower.getLocation().getX(),(int)red2PrincessTower.getLocation().getY(),CellValue.GRASS);
            crown += 1;
        }
    }

    /**
     * Sets tower xp damage.
     *
     * @param level the level
     */
    public void setTowerXPDamage(int level)
    {
        if (level == 1)
        {
            blueKingTower.setDamage(50);
            blueKingTower.setHP(2400);
            blue1PrincessTower.setDamage(50);
            blue1PrincessTower.setHP(1400);
            blue2PrincessTower.setDamage(50);
            blue2PrincessTower.setHP(1400);

            redKingTower.setDamage(50);
            redKingTower.setHP(2400);
            red1PrincessTower.setDamage(50);
            red1PrincessTower.setHP(1400);
            red2PrincessTower.setDamage(50);
            red2PrincessTower.setHP(1400);
        }
        if (level == 2)
        {
            blueKingTower.setDamage(53);
            blueKingTower.setHP(2568);
            blue1PrincessTower.setDamage(54);
            blue1PrincessTower.setHP(1512);
            blue2PrincessTower.setDamage(54);
            blue2PrincessTower.setHP(1512);

            redKingTower.setDamage(53);
            redKingTower.setHP(2568);
            red1PrincessTower.setDamage(54);
            red1PrincessTower.setHP(1512);
            red2PrincessTower.setDamage(54);
            red2PrincessTower.setHP(1512);
        }
        if (level == 3)
        {
            blueKingTower.setDamage(57);
            blueKingTower.setHP(2736);
            blue1PrincessTower.setDamage(58);
            blue1PrincessTower.setHP(1624);
            blue2PrincessTower.setDamage(58);
            blue2PrincessTower.setHP(1624);

            redKingTower.setDamage(57);
            redKingTower.setHP(2736);
            red1PrincessTower.setDamage(58);
            red1PrincessTower.setHP(1624);
            red2PrincessTower.setDamage(58);
            red2PrincessTower.setHP(1624);
        }
        if (level == 4)
        {
            blueKingTower.setDamage(60);
            blueKingTower.setHP(2904);
            blue1PrincessTower.setDamage(62);
            blue1PrincessTower.setHP(1750);
            blue2PrincessTower.setDamage(62);
            blue2PrincessTower.setHP(1750);

            redKingTower.setDamage(60);
            redKingTower.setHP(2904);
            red1PrincessTower.setDamage(62);
            red1PrincessTower.setHP(1750);
            red2PrincessTower.setDamage(62);
            red2PrincessTower.setHP(1750);
        }
        if (level == 5)
        {
            blueKingTower.setDamage(64);
            blueKingTower.setHP(3096);
            blue1PrincessTower.setDamage(69);
            blue1PrincessTower.setHP(1890);
            blue2PrincessTower.setDamage(69);
            blue2PrincessTower.setHP(1890);

            redKingTower.setDamage(64);
            redKingTower.setHP(3096);
            red1PrincessTower.setDamage(69);
            red1PrincessTower.setHP(1890);
            red2PrincessTower.setDamage(69);
            red2PrincessTower.setHP(1890);
        }
    }

    /**
     * Sets xp damage.
     *
     * @param level    the level
     * @param creature the creature
     */
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
                    ((Troop) creature).setDamage(33);
                    ((Troop) creature).setHP(125);
                }
                if (creature.getName().equals("babyDragon"))
                {
                    ((Troop) creature).setDamage(100);
                    ((Troop) creature).setHP(800);
                }
                if (creature.getName().equals("wizard"))
                {
                    ((Troop) creature).setDamage(130);
                    ((Troop) creature).setHP(340);
                }
                if (creature.getName().equals("miniPekka"))
                {
                    ((Troop) creature).setDamage(325);
                    ((Troop) creature).setHP(600);
                }
                if (creature.getName().equals("giant"))
                {
                    ((Troop) creature).setDamage(126);
                    ((Troop) creature).setHP(2000);
                }
                if (creature.getName().equals("valkyarie"))
                {
                    ((Troop) creature).setDamage(120);
                    ((Troop) creature).setHP(880);
                }
            }
            if (creature instanceof Building)
            {
                if (creature.getName().equals("cannon"))
                {
                    ((Building) creature).setDamage(60);
                    ((Building) creature).setHP(380);
                }
                if (creature instanceof InfernoTower)
                {
                    ((InfernoTower) creature).setHP(800);
                    ((InfernoTower) creature).setDamage(20);
                    ((InfernoTower) creature).setDamageGainer(9);
                }
            }
            if (creature instanceof Rage)
            {
                ((Rage) creature).setTime(6);
            }
        }

        if (level == 2)
        {
            if(creature instanceof Troop)
            {
                if (creature.getName().equals("barbarian"))
                {
                    ((Troop) creature).setDamage(82);
                    ((Troop) creature).setHP(330);
                }
                if (creature.getName().equals("archer"))
                {
                    ((Troop) creature).setDamage(44);
                    ((Troop) creature).setHP(127);
                }
                if (creature.getName().equals("babyDragon"))
                {
                    ((Troop) creature).setDamage(110);
                    ((Troop) creature).setHP(880);
                }
                if (creature.getName().equals("wizard"))
                {
                    ((Troop) creature).setDamage(143);
                    ((Troop) creature).setHP(374);
                }
                if (creature.getName().equals("miniPekka"))
                {
                    ((Troop) creature).setDamage(357);
                    ((Troop) creature).setHP(660);
                }
                if (creature.getName().equals("giant"))
                {
                    ((Troop) creature).setDamage(138);
                    ((Troop) creature).setHP(2200);
                }
                if (creature.getName().equals("valkyarie"))
                {
                    ((Troop) creature).setDamage(132);
                    ((Troop) creature).setHP(968);
                }
            }
            if (creature instanceof Building)
            {
                if (creature.getName().equals("cannon"))
                {
                    ((Building) creature).setDamage(60);
                    ((Building) creature).setHP(380);
                }
                if (creature instanceof InfernoTower)
                {
                    ((InfernoTower) creature).setHP(880);
                    ((InfernoTower) creature).setDamage(22);
                    ((InfernoTower) creature).setDamageGainer(10);
                }
            }
            if (creature instanceof Rage)
            {
                ((Rage) creature).setTime(6);
            }
        }

        if (level == 3)
        {
            if(creature instanceof Troop)
            {
                if (creature.getName().equals("barbarian"))
                {
                    ((Troop) creature).setDamage(90);
                    ((Troop) creature).setHP(363);
                }
                if (creature.getName().equals("archer"))
                {
                    ((Troop) creature).setDamage(48);
                    ((Troop) creature).setHP(151);
                }
                if (creature.getName().equals("babyDragon"))
                {
                    ((Troop) creature).setDamage(121);
                    ((Troop) creature).setHP(968);
                }
                if (creature.getName().equals("wizard"))
                {
                    ((Troop) creature).setDamage(157);
                    ((Troop) creature).setHP(411);
                }
                if (creature.getName().equals("miniPekka"))
                {
                    ((Troop) creature).setDamage(393);
                    ((Troop) creature).setHP(726);
                }
                if (creature.getName().equals("giant"))
                {
                    ((Troop) creature).setDamage(152);
                    ((Troop) creature).setHP(2420);
                }
                if (creature.getName().equals("valkyarie"))
                {
                    ((Troop) creature).setDamage(145);
                    ((Troop) creature).setHP(1064);
                }
            }
            if (creature instanceof Building)
            {
                if (creature.getName().equals("cannon"))
                {
                    ((Building) creature).setDamage(72);
                    ((Building) creature).setHP(459);
                }
                if (creature instanceof InfernoTower)
                {
                    ((InfernoTower) creature).setHP(968);
                    ((InfernoTower) creature).setDamage(24);
                    ((InfernoTower) creature).setDamageGainer(11);
                }
            }
            if (creature instanceof Rage)
            {
                ((Rage) creature).setTime(7);
            }
        }

        if (level == 4)
        {
            if(creature instanceof Troop)
            {
                if (creature.getName().equals("barbarian"))
                {
                    ((Troop) creature).setDamage(99);
                    ((Troop) creature).setHP(438);
                }
                if (creature.getName().equals("archer"))
                {
                    ((Troop) creature).setDamage(53);
                    ((Troop) creature).setHP(166);
                }
                if (creature.getName().equals("babyDragon"))
                {
                    ((Troop) creature).setDamage(133);
                    ((Troop) creature).setHP(1064);
                }
                if (creature.getName().equals("wizard"))
                {
                    ((Troop) creature).setDamage(172);
                    ((Troop) creature).setHP(452);
                }
                if (creature.getName().equals("miniPekka"))
                {
                    ((Troop) creature).setDamage(432);
                    ((Troop) creature).setHP(798);
                }
                if (creature.getName().equals("giant"))
                {
                    ((Troop) creature).setDamage(167);
                    ((Troop) creature).setHP(2660);
                }
                if (creature.getName().equals("valkyarie"))
                {
                    ((Troop) creature).setDamage(159);
                    ((Troop) creature).setHP(1170);
                }
            }
            if (creature instanceof Building)
            {
                if (creature.getName().equals("cannon"))
                {
                    ((Building) creature).setDamage(79);
                    ((Building) creature).setHP(505);
                }
                if (creature instanceof InfernoTower)
                {
                    ((InfernoTower) creature).setHP(1064);
                    ((InfernoTower) creature).setDamage(26);
                    ((InfernoTower) creature).setDamageGainer(12);
                }
            }
            if (creature instanceof Rage)
            {
                ((Rage) creature).setTime(7);
            }
        }

        if (level == 5)
        {
            if(creature instanceof Troop)
            {
                if (creature.getName().equals("barbarian"))
                {
                    ((Troop) creature).setDamage(109);
                    ((Troop) creature).setHP(480);
                }
                if (creature.getName().equals("archer"))
                {
                    ((Troop) creature).setDamage(58);
                    ((Troop) creature).setHP(182);
                }
                if (creature.getName().equals("babyDragon"))
                {
                    ((Troop) creature).setDamage(146);
                    ((Troop) creature).setHP(1168);
                }
                if (creature.getName().equals("wizard"))
                {
                    ((Troop) creature).setDamage(189);
                    ((Troop) creature).setHP(496);
                }
                if (creature.getName().equals("miniPekka"))
                {
                    ((Troop) creature).setDamage(474);
                    ((Troop) creature).setHP(876);
                }
                if (creature.getName().equals("giant"))
                {
                    ((Troop) creature).setDamage(183);
                    ((Troop) creature).setHP(2920);
                }
                if (creature.getName().equals("valkyarie"))
                {
                    ((Troop) creature).setDamage(175);
                    ((Troop) creature).setHP(1284);
                }
            }
            if (creature instanceof Building)
            {
                if (creature.getName().equals("cannon"))
                {
                    ((Building) creature).setDamage(87);
                    ((Building) creature).setHP(554);
                }
                if (creature instanceof InfernoTower)
                {
                    ((InfernoTower) creature).setHP(1168);
                    ((InfernoTower) creature).setDamage(29);
                    ((InfernoTower) creature).setDamageGainer(13);
                }
            }
            if (creature instanceof Rage)
            {
                ((Rage) creature).setTime(8);
            }
        }

    }

    /**
     * Set cell value.
     *
     * @param row       the row
     * @param column    the column
     * @param cellValue the cell value
     */
    public void setCellValue(int row , int column, CellValue cellValue){
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        this.grid[row][column] = cellValue;
    }

    /**
     * Is end game boolean.
     *
     * @return the boolean
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * Sets end game.
     *
     * @param endGame the end game
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    /**
     * Set position.
     *
     * @param creature the creature
     */
    public void setPosition(Creature creature){

        if(creature instanceof Troop){
                whichWay(creature);
                ((Troop)creature).setTimeForClear();
            }
            clearSave(creature);
    }

    /**
     * Clear save.
     *
     * @param creature the creature
     */
    public void clearSave(Creature creature){
        if(creature instanceof  Troop) {
            if(((Troop) creature).getTimeForClear()==1) {
                HashMap<Point2D,CellValue> temp = creature.getSaveCell();
                for (Point2D point2D : temp.keySet()) {
                    if(!creature.getLocation().equals(point2D)) {
                        this.setCellValue((int) point2D.getX(), (int) point2D.getY(), temp.get(point2D));
                        //System.out.println("creature loc is:"+creature.getLocation());
                        //System.out.println("is cleaning"+point2D);
                    }

                }
                ((Troop)creature).newTimeForClear();
            }
        }
    }

    /**
     * Which way.
     *
     * @param creature the creature
     */
    public void whichWay(Creature creature){
        if(creature.getColor().equals("blue")){
            if(creature.getLocation().getY()<=9) {
                if(getCellValue(4,4)==CellValue.REDQUEEN) {
                    newMove(4, 4, creature);
                }else{
                    newMove(3,9,creature);
                }
            }else {
                if(getCellValue(4,14)==CellValue.REDQUEEN) {
                    newMove(4, 14, creature);
                }else {
                    newMove(3,9,creature);
                }
            }
        }else {
            if(creature.getLocation().getY()<=9) {
                if(getCellValue(27,4)==CellValue.REDQUEEN) {
                    botMove(27, 4, creature);
                }else{
                    botMove(28,9,creature);
                }
            }else {
                if(getCellValue(27,14)==CellValue.REDQUEEN) {
                    botMove(27, 14, creature);
                }else {
                    botMove(28,9,creature);
                }
            }
        }
    }

    /**
     * Move creatures.
     */
    public void moveCreatures(){
        for (Creature creature:friends){
            setPosition(creature);
        }

        for(Creature creature: robot.getAliveEnemies()){
            setPosition(creature);
        }
    }

    /**
     * Action creatures.
     */
    public void actionCreatures(){
        for (Creature creature:friends){
            creature.action(this);
        }

        for(Creature creature: robot.getAliveEnemies()){
            creature.action(this);
        }
    }

    /**
     * Tower action.
     */
    public void towerAction(){
        blueKingTower.action(this);
        redKingTower.action(this);
        blue1PrincessTower.action(this);
        red1PrincessTower.action(this);
        blue2PrincessTower.action(this);
        red2PrincessTower.action(this);
    }

    /**
     * Step.
     */
    public void step(){
        robotMove();
        aliveCreatures();
        aliveTowers();
        moveCreatures();
        actionCreatures();
        //towerAction();

    }

    /**
     * Robot move.
     */
    public void robotMove(){
        if(robot instanceof SimpleRobot){
            ((SimpleRobot) robot).addEnemy();
        }else{

        }
    }

    /**
     * New move.
     *
     * @param desRow    the des row
     * @param desColumn the des column
     * @param creature  the creature
     */
    public void newMove(int desRow ,int desColumn, Creature creature) {
        int curRow =(int) creature.getLocation().getX();
        int curColumn = (int) creature.getLocation().getY();
        if (curColumn != desColumn || curRow != desRow) {
            if(desColumn==9&&curRow>5){
                desColumn=4;
            }
            if(curColumn>desColumn){
                curColumn--;
            }if(curColumn<desColumn){
                curColumn++;
            }if(curColumn==desColumn){
                curRow--;
            }
            if(getCellValue(curRow,curColumn) == CellValue.GRASS || getCellValue(curRow,curColumn)==CellValue.ROAD
                || getCellValue(curRow,curColumn) == CellValue.BRIDGE ||getCellValue(curRow,curColumn) == CellValue.DESTROY) {
                creature.getSaveCell().put(new Point2D(curRow, curColumn), getCellValue(curRow, curColumn));
                creature.setLocation(new Point2D(curRow, curColumn));
            }
        }
        this.setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),creature.getCellValue());

    }

    /**
     * Bot move.
     *
     * @param desRow    the des row
     * @param desColumn the des column
     * @param creature  the creature
     */
    public void botMove(int desRow,int desColumn,Creature creature){
        int curRow =(int) creature.getLocation().getX();
        int curColumn = (int) creature.getLocation().getY();
        if (curColumn != desColumn || curRow != desRow) {
            if(desColumn==9&&curRow<27){
                desColumn=4;
            }
            if(curColumn>desColumn){
                curColumn--;
            }if(curColumn<desColumn){
                curColumn++;
            }if(curColumn==desColumn){
                curRow++;
            }
            if(getCellValue(curRow,curColumn) == CellValue.GRASS || getCellValue(curRow,curColumn)==CellValue.ROAD
                    || getCellValue(curRow,curColumn) == CellValue.BRIDGE||getCellValue(curRow,curColumn) == CellValue.DESTROY) {
                creature.getSaveCell().put(new Point2D(curRow, curColumn), getCellValue(curRow, curColumn));
                creature.setLocation(new Point2D(curRow, curColumn));
            }
        }
        this.setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),creature.getCellValue());
    }

    /**
     * Gets blue king tower.
     *
     * @return the blue king tower
     */
    public KingTower getBlueKingTower() {
        return blueKingTower;
    }

    /**
     * Sets blue king tower.
     *
     * @param blueKingTower the blue king tower
     */
    public void setBlueKingTower(KingTower blueKingTower) {
        this.blueKingTower = blueKingTower;
    }

    /**
     * Gets blue 1 princess tower.
     *
     * @return the blue 1 princess tower
     */
    public PrincessTower getBlue1PrincessTower() {
        return blue1PrincessTower;
    }

    /**
     * Sets blue 1 princess tower.
     *
     * @param blue1PrincessTower the blue 1 princess tower
     */
    public void setBlue1PrincessTower(PrincessTower blue1PrincessTower) {
        this.blue1PrincessTower = blue1PrincessTower;
    }

    /**
     * Gets blue 2 princess tower.
     *
     * @return the blue 2 princess tower
     */
    public PrincessTower getBlue2PrincessTower() {
        return blue2PrincessTower;
    }

    /**
     * Sets blue 2 princess tower.
     *
     * @param blue2PrincessTower the blue 2 princess tower
     */
    public void setBlue2PrincessTower(PrincessTower blue2PrincessTower) {
        this.blue2PrincessTower = blue2PrincessTower;
    }

    /**
     * Gets red king tower.
     *
     * @return the red king tower
     */
    public KingTower getRedKingTower() {
        return redKingTower;
    }

    /**
     * Sets red king tower.
     *
     * @param redKingTower the red king tower
     */
    public void setRedKingTower(KingTower redKingTower) {
        this.redKingTower = redKingTower;
    }

    /**
     * Gets red 1 princess tower.
     *
     * @return the red 1 princess tower
     */
    public PrincessTower getRed1PrincessTower() {
        return red1PrincessTower;
    }

    /**
     * Sets red 1 princess tower.
     *
     * @param red1PrincessTower the red 1 princess tower
     */
    public void setRed1PrincessTower(PrincessTower red1PrincessTower) {
        this.red1PrincessTower = red1PrincessTower;
    }

    /**
     * Gets red 2 princess tower.
     *
     * @return the red 2 princess tower
     */
    public PrincessTower getRed2PrincessTower() {
        return red2PrincessTower;
    }

    /**
     * Sets red 2 princess tower.
     *
     * @param red2PrincessTower the red 2 princess tower
     */
    public void setRed2PrincessTower(PrincessTower red2PrincessTower) {
        this.red2PrincessTower = red2PrincessTower;
    }

    /**
     * Gets creatures.
     *
     * @return the creatures
     */
    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    /**
     * Sets creatures.
     *
     * @param creatures the creatures
     */
    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    /**
     * Gets friends.
     *
     * @return the friends
     */
    public ArrayList<Creature> getFriends() {
        return friends;
    }

    /**
     * Sets friends.
     *
     * @param friends the friends
     */
    public void setFriends(ArrayList<Creature> friends) {
        this.friends = friends;
    }

    /**
     * Gets xp.
     *
     * @return the xp
     */
    public int getXP() {
        return XP;
    }

    /**
     * Sets xp.
     *
     * @param XP the xp
     */
    public void setXP(int XP) {
        this.XP = XP;
    }

    /**
     * Gets robot xp.
     *
     * @return the robot xp
     */
    public int getRobotXP() {
        return robotXP;
    }

    /**
     * Sets robot xp.
     *
     * @param robotXP the robot xp
     */
    public void setRobotXP(int robotXP) {
        this.robotXP = robotXP;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets robot.
     *
     * @return the robot
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * Sets robot.
     *
     * @param robot the robot
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
