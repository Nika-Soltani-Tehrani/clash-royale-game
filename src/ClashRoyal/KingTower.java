package ClashRoyal;

import javafx.geometry.Point2D;

public class KingTower extends Tower{


    public KingTower(Point2D location) {
        this.range = 7;
        this.hitSpeed = 1;
        this.location = location;
    }

    @Override
    public void action() {

    }

    public boolean isEnabled()
    {
        // needed to be modified
        return true;
    }
}
