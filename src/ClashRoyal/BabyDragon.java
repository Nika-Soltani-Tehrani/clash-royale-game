package ClashRoyal;

public class BabyDragon extends Troop{

    public BabyDragon() {
        this.cost = 4;
        this.count = 1;
        this.range = 3; //only can damage the person in front of him
        this.speed = Speed.FAST;
        this.hitSpeed = 1.8;
    }

    @Override
    public void action() {

    }

}
