package ClashRoyal;

public class MiniPekka extends Troop{


    public MiniPekka() {
        this.cost = 4;
        this.count = 1;
        this.range = 1; //only can damage the person in front of him
        this.speed = Speed.FAST;
        this.hitSpeed = 1.8;
    }

    @Override
    public void action() {

    }

}
