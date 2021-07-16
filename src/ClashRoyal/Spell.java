package ClashRoyal;

public abstract class Spell extends Creature{

    protected int areaDamage;

    public Spell() {

    }

    public abstract void action();

    public int getAreaDamage() {
        return areaDamage;
    }

    public void setAreaDamage(int areaDamage) {
        this.areaDamage = areaDamage;
    }
}
