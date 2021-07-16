package ClashRoyal;

public class Archer extends Troop{

    public Archer() {
        this.cost = 3;
        this.count = 2;
        this.range = 5; //only can damage the person in front of him
        this.speed = Speed.MEDIUM;
        this.hitSpeed = 1.2;
    }

    @Override
    public void action() {

    }
}
