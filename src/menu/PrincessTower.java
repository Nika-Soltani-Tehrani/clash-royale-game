package menu;

import javafx.geometry.Point2D;

/**
 * The type Princess tower.
 */
public class PrincessTower extends Tower{

    /**
     * Instantiates a new Princess tower.
     *
     * @param color     the color
     * @param location  the location
     * @param id        the id
     * @param cellValue the cell value
     */
    public PrincessTower(String color, Point2D location, int id, BoardManager.CellValue cellValue) {
        super(color,location,id,cellValue);
        this.range = 7.5;
        this.hitSpeed = 0.8;
        this.location = location;
    }

    @Override
    public void action(BoardManager boardManager)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();
        damage = (int)(damage/hitSpeed);
        Creature enemy = null;

        for( int i = x - (int)range; i < y + range; i++)
        {
            for( int j = y - (int)range; i < x + range; j++){
                if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.GRASS)
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.ROAD
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.BRIDGE) {
                    enemy = boardManager.enemyFinder(boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)], color);
                    if (enemy != null) {
                        if (enemy instanceof Troop) {
                            ((Troop) enemy).loseHP(this.damage);
                        }
                        if (enemy instanceof Building) {
                            ((Building) enemy).loseHP(this.damage);
                        }

                    }
                }
            }
        }
        System.out.println(toString());
    }
    @Override
    public String toString() {
        return "princess tower{" +
                "color='" + color + '\'' +
                ", HP=" + HP +
                '}';
    }
}