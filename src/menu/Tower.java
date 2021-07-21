package menu;

import javafx.geometry.Point2D;

public abstract class Tower {

    protected String color;
    protected int id;
    protected int HP;
    protected int damage;
    protected double range;
    protected double hitSpeed;
    protected Point2D location;
    protected boolean isAlive = true;
    protected BoardManager.CellValue cellValue;

    public Tower(String color, Point2D location, int id, BoardManager.CellValue cellValue) {
        this.id = id;
        this.color = color;
        this.location = location;
        this.cellValue = cellValue;
    }

    public abstract void action(BoardManager boardManager);

    public void loseHP(int damage)
    {
        this.HP = this.HP - damage;
        if(this.HP == 0)
            isAlive = false;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
