package ClashRoyal;

public class Valkyarie extends Troop{

    public Valkyarie() {
        this.cost = 4;
        this.count = 1;
        this.range = 1; //only can damage the person in front of him
        this.speed = Speed.MEDIUM;
        this.hitSpeed = 1.5;
    }

    @Override
    public void action() {

    }
}
