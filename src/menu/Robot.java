package menu;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Robot.
 */
public abstract class Robot {

    /**
     * The Board manager.
     */
    protected BoardManager boardManager;
    /**
     * The Enemies.
     */
    protected ArrayList<Creature> enemies = new ArrayList<>();
    /**
     * The Alive enemies.
     */
    protected ArrayList<Creature> aliveEnemies = new ArrayList<>();

    /**
     * Instantiates a new Robot.
     *
     * @param boardManager the board manager
     */
    public Robot(BoardManager boardManager)
    {
        this.boardManager = boardManager;
        makeEnemies();
    }

    /**
     * Make enemies.
     */
    public void makeEnemies()
    {
        int count;
        Random r = new Random();
        int xLow = 5;
        int xHigh = 13;
        int x = r.nextInt(xHigh - xLow) + xLow;

        int yLow = 5;
        int yHigh = 9;
        int y = r.nextInt(yHigh - yLow) + yLow;

        Archer rArcher = new Archer("red",new Point2D(x, y),-1, BoardManager.CellValue.rARCHER);
        count = rArcher.getCount();
        for (int i = 0; i < count; i++)
        {
            rArcher = new Archer("red",new Point2D(x + i, y),i, BoardManager.CellValue.rARCHER);
            enemies.add(rArcher);
            boardManager.setXPDamage(boardManager.getLevel(),rArcher);
        }
        Arrow rArrow = new Arrow("red",new Point2D(x, y), BoardManager.CellValue.rARROW);
        enemies.add(rArrow);
        boardManager.setXPDamage(boardManager.getLevel(),rArrow);

        BabyDragon rBabyDragon = new BabyDragon("red",new Point2D(x, y),-1, BoardManager.CellValue.rBABYDRAGON);
        count = rBabyDragon.getCount();
        for (int i = 0; i < count; i++)
        {
            rBabyDragon = new BabyDragon("red",new Point2D(x + i, y),i, BoardManager.CellValue.rBABYDRAGON);
            enemies.add(rBabyDragon);
            boardManager.setXPDamage(boardManager.getLevel(),rBabyDragon);
        }
        Barbarian rBarbarian = new Barbarian("red",new Point2D(x, y),-1, BoardManager.CellValue.rBARBARIAN);
        count = rBarbarian.getCount();
        for (int i = 0; i < count; i++)
        {
            rBarbarian = new Barbarian("red",new Point2D(x + i, y),i, BoardManager.CellValue.rBARBARIAN);
            enemies.add(rBarbarian);
            boardManager.setXPDamage(boardManager.getLevel(),rBarbarian);
        }
        Cannon rCannon = new Cannon("red",new Point2D(x, y), BoardManager.CellValue.rCANNON);
        enemies.add(rCannon);
        boardManager.setXPDamage(boardManager.getLevel(),rCannon);

        Fireball rFireball = new Fireball("red",new Point2D(x, y), BoardManager.CellValue.rFIREBALL);
        enemies.add(rFireball);
        boardManager.setXPDamage(boardManager.getLevel(),rFireball);

        Giant rGiant = new Giant("red",new Point2D(x, y),-1, BoardManager.CellValue.rGIANT);
        count = rGiant.getCount();
        for (int i = 0; i < count; i++)
        {
            rGiant = new Giant("red",new Point2D(x + i, y),i, BoardManager.CellValue.rGIANT);
            enemies.add(rGiant);
            boardManager.setXPDamage(boardManager.getLevel(),rGiant);
        }
        InfernoTower rInfernoTower = new InfernoTower("red",new Point2D(x, y), BoardManager.CellValue.rINFERNOTOWER);
        enemies.add(rInfernoTower);
        boardManager.setXPDamage(boardManager.getLevel(),rInfernoTower);

        MiniPekka rMiniPekka = new MiniPekka("red",new Point2D(x, y),-1, BoardManager.CellValue.rMINIPEKKA);
        count = rMiniPekka.getCount();
        for (int i = 0; i < count; i++)
        {
            rMiniPekka = new MiniPekka("red",new Point2D(x + i, y),i, BoardManager.CellValue.rMINIPEKKA);
            enemies.add(rMiniPekka);
            boardManager.setXPDamage(boardManager.getLevel(),rMiniPekka);
        }
        Rage rRage = new Rage("red",new Point2D(x, y), BoardManager.CellValue.rRAGE);
        enemies.add(rRage);
        boardManager.setXPDamage(boardManager.getLevel(),rRage);

        Valkyarie rValkyarie = new Valkyarie("red",new Point2D(x, y),-1, BoardManager.CellValue.rVALKYARIE);
        count = rValkyarie.getCount();
        for (int i = 0; i < count; i++)
        {
            rValkyarie = new Valkyarie("red",new Point2D(x + i, y),i, BoardManager.CellValue.rVALKYARIE);
            enemies.add(rValkyarie);
            boardManager.setXPDamage(boardManager.getLevel(),rValkyarie);
        }
        Wizard rWizard = new Wizard("red",new Point2D(x, y),-1, BoardManager.CellValue.rWIZARD);
        count = rWizard.getCount();
        for (int i = 0; i < count; i++)
        {
            rWizard = new Wizard("red",new Point2D(x + i, y),i, BoardManager.CellValue.rWIZARD);
            getEnemies().add(rWizard);
            boardManager.setXPDamage(boardManager.getLevel(),rWizard);
        }
    }

    /**
     * Gets enemies.
     *
     * @return the enemies
     */
    public ArrayList<Creature> getEnemies() {
        return enemies;
    }

    /**
     * Sets enemies.
     *
     * @param enemies the enemies
     */
    public void setEnemies(ArrayList<Creature> enemies) {
        this.enemies = enemies;
    }

    /**
     * Gets alive enemies.
     *
     * @return the alive enemies
     */
    public ArrayList<Creature> getAliveEnemies() {
        return aliveEnemies;
    }

    /**
     * Sets alive enemies.
     *
     * @param aliveEnemies the alive enemies
     */
    public void setAliveEnemies(ArrayList<Creature> aliveEnemies) {
        this.aliveEnemies = aliveEnemies;
    }


}