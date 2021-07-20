package ClashRoyal;

import javafx.geometry.Point2D;

public class BabyDragon extends Troop{

    public BabyDragon(String color, Point2D location,int id,BoardManager.CellValue cellValue) {
        super(color,location,id,cellValue);
        this.cost = 4;
        this.count = 1;
        this.range = 3; //only can damage the person in front of him
        this.speed = Speed.FAST;
        this.hitSpeed = 1.8;
        this.name = "babyDragon";
    }

    @Override
    public void action(BoardManager boardManager)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();
        Creature enemy = null;

        for( int i = x; i < x + range; i++)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.BRIDGE)
            {
                enemy = boardManager.enemyFinder(boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)],color);
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    /*if (enemy instanceof Spell)
                    {
                        this.loseHP(((Spell) enemy).getAreaDamage());
                    }*/
                }
            }
        }
        for( int i = x; i > x - range; i--)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)] != BoardManager.CellValue.BRIDGE)
            {
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    /*if (enemy instanceof Spell)
                    {
                        this.loseHP(((Spell) enemy).getAreaDamage());
                    }*/
                }
            }
        }
        for( int i = y; i < y + range; i++)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.BRIDGE)
            {
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    /*if (enemy instanceof Spell)
                    {
                        this.loseHP(((Spell) enemy).getAreaDamage());
                    }*/
                }
            }
        }
        for( int i = y; i > y - range; i--)
        {
            if ((boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.GRASS)
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.ROAD
                    ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.BRIDGE)
            {
                if (enemy != null) {
                    if (enemy instanceof Troop)
                    {
                        ((Troop) enemy).loseHP(this.damage);
                    }
                    if (enemy instanceof Building)
                    {
                        ((Building) enemy).loseHP(this.damage);
                    }
                    /*if (enemy instanceof Spell)
                    {
                        this.loseHP(((Spell) enemy).getAreaDamage());
                    }*/
                }
            }
        }
    }

}
