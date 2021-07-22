package menu;

import javafx.geometry.Point2D;

/**
 * The type Troop.
 */
public abstract class Troop extends Creature{

    /**
     * The Hp.
     */
    protected int HP;
    /**
     * The Id.
     */
    protected int id;
    /**
     * The Damage.
     */
    protected int damage;
    /**
     * The Hit speed.
     */
    protected double hitSpeed;
    /**
     * The Speed.
     */
    protected Speed speed;
    /**
     * The Count.
     */
    protected int count;
    /**
     * The Is alive.
     */
    protected boolean isAlive = true;
    /**
     * The Time for clear.
     */
    protected int timeForClear;


    /**
     * The enum Speed.
     */
    public enum Speed {
        /**
         * Slow speed.
         */
        SLOW,
        /**
         * Medium speed.
         */
        MEDIUM,
        /**
         * Fast speed.
         */
        FAST
    }

    /**
     * Instantiates a new Troop.
     *
     * @param color     the color
     * @param location  the location
     * @param id        the id
     * @param cellValue the cell value
     */
    public Troop(String color, Point2D location,int id,BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
        this.id = id;
        timeForClear = 0;
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
     * Gain speed.
     */
    public void gainSpeed()
    {
        if (this.speed == Speed.SLOW)
            this.speed = Speed.MEDIUM;
        if (this.speed == Speed.MEDIUM)
            this.speed = Speed.FAST;
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
        return isAlive;
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
     * Gets speed.
     *
     * @return the speed
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets time for clear.
     *
     * @return the time for clear
     */
    public int getTimeForClear() {
        return timeForClear;
    }

    /**
     * Set time for clear.
     */
    public void setTimeForClear(){
        timeForClear++;
    }

    /**
     * New time for clear.
     */
    public void newTimeForClear(){
        this.timeForClear = 0 ;
    }
}