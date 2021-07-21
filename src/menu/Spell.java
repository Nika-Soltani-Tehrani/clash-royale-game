package menu;

import javafx.geometry.Point2D;

public abstract class Spell extends Creature{

    protected int areaDamage;

    public Spell(String color, Point2D location, BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
    }

    public abstract void action(BoardManager boardManager);

    public int getAreaDamage() {
        return areaDamage;
    }

    public void setAreaDamage(int areaDamage) {
        this.areaDamage = areaDamage;
    }
}
