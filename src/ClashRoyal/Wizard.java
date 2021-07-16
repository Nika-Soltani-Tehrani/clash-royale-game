package ClashRoyal;

public class Wizard extends Troop{

    public Wizard() {
        this.cost = 5;
        this.count = 1;
        this.range = 5; //only can damage the person in front of him
        this.speed = Speed.MEDIUM;
        this.hitSpeed = 1.7;
    }

    @Override
    public void action() {

    }

}
