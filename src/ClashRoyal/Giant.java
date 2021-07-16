package ClashRoyal;

public class Giant extends Troop{

    public Giant() {
        this.cost = 5;
        this.count = 1;
        this.range = 1; //only can damage the person in front of him
        this.speed = Speed.SLOW;
        this.hitSpeed = 1.5;
    }

    @Override
    public void action() {

    }


}
