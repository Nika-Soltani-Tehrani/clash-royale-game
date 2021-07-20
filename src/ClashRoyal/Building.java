package ClashRoyal;

import javafx.geometry.Point2D;

public abstract class Building extends Creature{

    protected int HP;
    protected int damage;
    protected double hitSpeed;
    protected int lifeTime;
    protected double range;
    protected boolean isAlive = true;

    public Building(String color, Point2D location,BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
    }


    public abstract void action(BoardManager boardManager);

    public void loseHP(int damage)
    {
        this.HP = this.HP - damage;
        if(this.HP == 0)
            isAlive = false;
    }

    public void gainPower(int gain)
    {
        this.damage = this.damage + gain;
    }

    public void gainHitSpeed(int gain)
    {
        this.hitSpeed = this.hitSpeed + gain;
    }


    public boolean isAlive() {
        return (isAlive) || lifeTime > 0;
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

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public void setRange(double range) {
        this.range = range;
    }

}
