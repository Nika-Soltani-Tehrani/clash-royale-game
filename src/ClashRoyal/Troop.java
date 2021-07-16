package ClashRoyal;

public abstract class Troop extends Creature{

    protected int HP;
    protected int damage;
    protected double hitSpeed;
    protected Speed speed;
    protected int count;


    public enum Speed {
        SLOW, MEDIUM, FAST
    }

    public Troop() {
    }

    public abstract void action();

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
