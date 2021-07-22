package menu;

import javafx.geometry.Point2D;

/**
 * The type Tower.
 */
public abstract class Tower {

    /**
     * The Color.
     */
    protected String color;
    /**
     * The Id.
     */
    protected int id;
    /**
     * The Hp.
     */
    protected int HP;
    /**
     * The Damage.
     */
    protected int damage;
    /**
     * The Range.
     */
    protected double range;
    /**
     * The Hit speed.
     */
    protected double hitSpeed;
    /**
     * The Location.
     */
    protected Point2D location;
    /**
     * The Is alive.
     */
    protected boolean isAlive = true;
    /**
     * The Cell value.
     */
    protected BoardManager.CellValue cellValue;

    /**
     * Instantiates a new Tower.
     *
     * @param color     the color
     * @param location  the location
     * @param id        the id
     * @param cellValue the cell value
     */
    public Tower(String color, Point2D location, int id, BoardManager.CellValue cellValue) {
        this.id = id;
        this.color = color;
        this.location = location;
        this.cellValue = cellValue;
    }

    /**
     * Action.
     *
     * @param boardManager the board manager
     */
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
     * Gain hit speed.
     *
     * @param gain the gain
     */
    public void gainHitSpeed(int gain)
    {
        this.hitSpeed = this.hitSpeed + gain;
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
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
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
     * Gets range.
     *
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(double range) {
        this.range = range;
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
     * Gets location.
     *
     * @return the location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }
}