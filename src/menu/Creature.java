package menu;

import javafx.geometry.Point2D;
import java.util.HashMap;

/**
 * The type Creature.
 */
public abstract class Creature {

    /**
     * The Cell value.
     */
    protected BoardManager.CellValue cellValue;
    /**
     * The Color.
     */
    protected String color;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Cost.
     */
    protected int cost;
    /**
     * The Range.
     */
    protected double range;
    /**
     * The Location.
     */
    protected Point2D location;
    /**
     * The Save cell.
     */
    protected HashMap<Point2D, BoardManager.CellValue> saveCell;

    /**
     * Instantiates a new Creature.
     *
     * @param color     the color
     * @param location  the location
     * @param cellValue the cell value
     */
    public Creature(String color, Point2D location, BoardManager.CellValue cellValue) {
        this.color = color;
        this.cellValue = cellValue;
        this.location = location;
        saveCell = new HashMap<>();
    }

    /**
     * Action.
     *
     * @param boardManager the board manager
     */
    public abstract void action(BoardManager boardManager);

    /**
     * Gets cell value.
     *
     * @return the cell value
     */
    public BoardManager.CellValue getCellValue() {
        return cellValue;
    }

    /**
     * Sets cell value.
     *
     * @param cellValue the cell value
     */
    public void setCellValue(BoardManager.CellValue cellValue) {
        this.cellValue = cellValue;
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
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(int cost) {
        this.cost = cost;
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets save cell.
     *
     * @return the save cell
     */
    public HashMap<Point2D, BoardManager.CellValue> getSaveCell() {
        return saveCell;
    }
}

