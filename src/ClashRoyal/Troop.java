package ClashRoyal;

import javafx.geometry.Point2D;

public abstract class Troop extends Creature{

    protected int HP;
    protected int id;
    protected int damage;
    protected double hitSpeed;
    protected Speed speed;
    protected int count;
    protected boolean isAlive = true;


    public enum Speed {
        SLOW, MEDIUM, FAST
    }

    public Troop(String color, Point2D location,int id,BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
        this.id = id;
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

    public void gainSpeed()
    {
        if (this.speed == Speed.SLOW)
            this.speed = Speed.MEDIUM;
        if (this.speed == Speed.MEDIUM)
            this.speed = Speed.FAST;
    }

    public void gainHitSpeed(int gain)
    {
        this.hitSpeed = this.hitSpeed + gain;
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

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
