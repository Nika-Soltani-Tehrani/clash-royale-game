package menu;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * The type Cannon.
 */
public class Cannon extends Building{
    private static final int STARTTIME = 0;
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    /**
     * Instantiates a new Cannon.
     *
     * @param color     the color
     * @param location  the location
     * @param cellValue the cell value
     */
    public Cannon(String color, Point2D location, BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
        this.cost = 6;
        this.lifeTime = 30;
        this.range = 5.5;
        this.hitSpeed = 0.8;
        this.name = "cannon";
        // TODO
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
        timeSeconds.set(STARTTIME);
        timeline.play();

    }
    private void updateTimer() {
        // increment seconds
        int lifeTimeTimer = timeSeconds.get();
        timeSeconds.set(lifeTimeTimer + 1);
    }
    @Override
    public void action(BoardManager boardManager)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();
        damage = (int)(damage/hitSpeed);
        Creature enemy = null;

        for( int i = x; i < x + range; i++)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.BRIDGE)
            {
                enemy = boardManager.enemyFinder(boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)],color);
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    /*if (enemy instanceof Spell)
                    {
                        this.loseHP(((Spell) enemy).getAreaDamage());
                    }*/
                    if (this.color.equals("red")){
                        if (boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.BLUEKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.BLUEQUEEN)) {
                            if (y < 9) {
                                boardManager.getBlue1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getBlue2PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                    if (this.color.equals("blue")){
                        if (boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.REDKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.REDQUEEN)) {
                            if (y < 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                }
            }
        }
        for( int i = x; i > x - range; i--)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.BRIDGE)
            {
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    /*if (enemy instanceof Spell)
                    {
                        this.loseHP(((Spell) enemy).getAreaDamage());
                    }*/
                    if (this.color.equals("red")){
                        if (boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.BLUEKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.BLUEQUEEN)) {
                            if (y < 9) {
                                boardManager.getBlue1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getBlue2PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                    if (this.color.equals("blue")){
                        if (boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.REDKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] == BoardManager.CellValue.REDQUEEN)) {
                            if (y < 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                }
            }
        }
        for( int i = y; i < y + range; i++)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.BRIDGE)
            {
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    /*if (enemy instanceof Spell)
                    {
                        this.loseHP(((Spell) enemy).getAreaDamage());
                    }*/
                    if (this.color.equals("red")){
                        if (boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.BLUEKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.BLUEQUEEN)) {
                            if (y < 9) {
                                boardManager.getBlue1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getBlue2PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                    if (this.color.equals("blue")){
                        if (boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.REDKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.REDQUEEN)) {
                            if (y < 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                }
            }
        }
        for( int i = y; i > y - range; i--)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.BRIDGE)
            {
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    if (this.color.equals("red")){
                        if (boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.BLUEKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.BLUEQUEEN)) {
                            if (y < 9) {
                                boardManager.getBlue1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getBlue2PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                    if (this.color.equals("blue")){
                        if (boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.REDKING) {
                            boardManager.getBlueKingTower().loseHP(this.damage);
                        }
                        if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] == BoardManager.CellValue.REDQUEEN)) {
                            if (y < 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                            if (y > 9) {
                                boardManager.getRed1PrincessTower().loseHP(this.damage);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(toString());
    }
    @Override
    public String toString() {
        return "cannon{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", HP=" + HP +
                '}';
    }
}