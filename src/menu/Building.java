package menu;

import javafx.geometry.Point2D;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

/**
 * The type Building.
 */
public abstract class Building extends Creature{

    /**
     * The Hp.
     */
    protected int HP;
    /**
     * The Damage.
     */
    protected int damage;
    /**
     * The Hit speed.
     */
    protected double hitSpeed;
    /**
     * The Life time.
     */
    protected int lifeTime;
    /**
     * The Range.
     */
    protected double range;
    /**
     * The Is alive.
     */
    protected boolean isAlive = true;


    /**
     * Instantiates a new Building.
     *
     * @param color     the color
     * @param location  the location
     * @param cellValue the cell value
     */
    public Building(String color, Point2D location, BoardManager.CellValue cellValue) {
        super(color, location, cellValue);
    }

    public abstract void action(BoardManager boardManager);

    /**
     * Lose hp.
     *
     * @param damage the damage
     */
    public void loseHP(int damage)
    {
        this.HP = this.HP - damage;
        if(this.HP <= 0)
            isAlive = false;
    }

    /**
     * Gain power.
     *
     * @param gain the gain
     */
    public void gainPower(int gain)
    {
        this.damage = this.damage + gain;
    }

    /**
     * Gain hit speed.
     *
     * @param gain the gain
     */
    public void gainHitSpeed(int gain)
    {
        this.hitSpeed = this.hitSpeed + gain;
    }


    /**
     * Is alive boolean.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return (isAlive);
    }

    /**
     * Sets alive.
     *
     * @param alive the alive
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHP() {
        return HP;
    }

    /**
     * Sets hp.
     *
     * @param HP the hp
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets hit speed.
     *
     * @return the hit speed
     */
    public double getHitSpeed() {
        return hitSpeed;
    }

    /**
     * Sets hit speed.
     *
     * @param hitSpeed the hit speed
     */
    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    /**
     * Gets life time.
     *
     * @return the life time
     */
    public int getLifeTime() {
        return lifeTime;
    }

    /**
     * Sets life time.
     *
     * @param lifeTime the life time
     */
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