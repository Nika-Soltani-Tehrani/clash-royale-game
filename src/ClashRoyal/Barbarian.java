package ClashRoyal;

public class Barbarian extends Troop{

    public Barbarian() {
        this.cost = 5;
        this.count = 4;
        this.range = 1; //only can damage the person in front of him
        this.speed = Speed.MEDIUM;
        this.hitSpeed = 1.5;
    }

    @Override
    public void action() {

    }
}