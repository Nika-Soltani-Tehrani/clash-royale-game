package menu;

import javafx.geometry.Point2D;

/**
 * The type Barbarian.
 */
public class Barbarian extends Troop{

    /**
     * Instantiates a new Barbarian.
     *
     * @param color     the color
     * @param location  the location
     * @param id        the id
     * @param cellValue the cell value
     */
    public Barbarian(String color, Point2D location, int id, BoardManager.CellValue cellValue) {
        super(color,location,id,cellValue);
        this.cost = 5;
        this.count = 4;
        this.range = 1; //only can damage the person in front of him
        this.speed = Speed.MEDIUM;
        this.hitSpeed = 1.5;
        this.name = "barbarian";
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
        return "barbarian{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", HP=" + HP +
                '}';
    }
}