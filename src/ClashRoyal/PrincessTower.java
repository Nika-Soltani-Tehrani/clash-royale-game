package ClashRoyal;

import javafx.geometry.Point2D;

public class PrincessTower extends Tower{

    public PrincessTower(Point2D location) {

        this.range = 7.5;
        this.hitSpeed = 0.8;
        this.location = location;
    }

    @Override
    public void action() {

    }
}
