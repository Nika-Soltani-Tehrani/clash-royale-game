package ClashRoyal;

import javafx.geometry.Point2D;

public abstract class Creature {

    protected BoardManager.CellValue cellValue;
    protected String color;
    protected String name;
    protected int cost;
    protected double range;
    protected Point2D location;
    protected Point2D velocity;

    public Creature(String color, Point2D location, BoardManager.CellValue cellValue) {
        this.color = color;
        this.cellValue = cellValue;
        this.location = location;
    }

    public BoardManager.CellValue getCellValue() {
        return cellValue;
    }

    public void setCellValue(BoardManager.CellValue cellValue) {
        this.cellValue = cellValue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

