package ClashRoyal;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class Rage extends Spell{

    private int time;
    private static final int STARTTIME = 0;
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    public Rage(String color, Point2D location,BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
        this.cost = 3;
        this.range = 5;
        this.name = "rage";
        // TODO
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
        timeSeconds.set(STARTTIME);
        timeline.play();
    }

    @Override
    public void action(BoardManager boardManager)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();
        int addedHitSpeed;
        int addedHP;
        Troop.Speed lastSpeed;
        Creature friend = null;

        for( int i = x - (int)range; i < y + range; i++)
        {
            for( int j = y - (int)range; i < x + range; j++){
                if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.GRASS)
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.ROAD
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.BRIDGE) {
                    friend = boardManager.friendFinder(boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)], color);
                    if (friend != null) {
                        if (friend instanceof Troop) {
                            addedHitSpeed = (int)(0.4 * ((Troop) friend).getHitSpeed());
                            addedHP = (int)(1.4 * ((Building) friend).getHP());
                            lastSpeed = ((Troop) friend).getSpeed();
                            ((Troop) friend).gainHitSpeed((int)(1.4 * ((Troop) friend).getHitSpeed()));
                            ((Troop) friend).gainPower((int)(1.4 * ((Troop) friend).getHP()));
                            ((Troop) friend).gainSpeed();
                        }
                        if (friend instanceof Building) {
                            addedHitSpeed = (int)(0.4 * ((Troop) friend).getHitSpeed());
                            addedHP = (int)(1.4 * ((Building) friend).getHP());
                            ((Building) friend).gainHitSpeed((int)(1.4 * ((Building) friend).getHitSpeed()));
                            ((Building) friend).gainPower((int)(1.4 * ((Building) friend).getHP()));

                        }
                    }
                }
            }
        }
        if (timeSeconds.intValue() == time)
        {
            if (friend instanceof Troop) {
                addedHitSpeed = (int)(0.4 * ((Troop) friend).getHitSpeed());
                addedHP = (int)(1.4 * ((Building) friend).getHP());
                lastSpeed = ((Troop) friend).getSpeed();
                ((Troop) friend).setHitSpeed(((Troop) friend).getHitSpeed() - addedHitSpeed);
                ((Troop) friend).setHP(((Troop) friend).getHP() - addedHP);
                ((Troop) friend).setSpeed(lastSpeed);
            }
            if (friend instanceof Building) {
                addedHitSpeed = (int)(0.4 * ((Troop) friend).getHitSpeed());
                addedHP = (int)(1.4 * ((Building) friend).getHP());
                ((Building) friend).setHitSpeed(((Building) friend).getHitSpeed() - addedHitSpeed);
                ((Building) friend).setHP(((Building) friend).getHP() - addedHP);

            }
        }
    }

    private void updateTimer() {
        // increment seconds
        int lifeTimeTimer = timeSeconds.get();
        timeSeconds.set(lifeTimeTimer + 1);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
