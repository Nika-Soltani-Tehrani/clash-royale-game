package menu;

import javafx.geometry.Point2D;

/**
 * The type Spell.
 */
public abstract class Spell extends Creature{

    /**
     * The Area damage.
     */
    protected int areaDamage;

    /**
     * Instantiates a new Spell.
     *
     * @param color     the color
     * @param location  the location
     * @param cellValue the cell value
     */
    public Spell(String color, Point2D location, BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
    }

    public abstract void action(BoardManager boardManager);

    /**
     * Gets area damage.
     *
     * @return the area damage
     */
    public int getAreaDamage() {
        return areaDamage;
    }

    /**
     * Sets area damage.
     *
     * @param areaDamage the area damage
     */
    public void setAreaDamage(int areaDamage) {
        this.areaDamage = areaDamage;
    }
}
