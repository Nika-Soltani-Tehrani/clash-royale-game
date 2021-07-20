package ClashRoyal;

import javafx.geometry.Point2D;

public class Fireball extends Spell{

    public Fireball(String color, Point2D location) {
        super(color,location);
        this.cost = 4;
        this.range = 2.5;
        this.name = "fireball";
    }

    @Override
    public void action(BoardManager boardManager)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();
        Creature enemy = null;

        for( int i = x - (int)range; i < y + range; i++)
        {
            for( int j = y - (int)range; i < x + range; j++){
                if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.GRASS)
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.ROAD
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.BRIDGE) {
                    enemy = boardManager.enemyFinder(boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)], color);
                    if (enemy != null) {
                        if (enemy instanceof Troop) {
                            ((Troop) enemy).loseHP(((Troop) enemy).getHP());
                        }
                        if (enemy instanceof Building) {
                            ((Building) enemy).loseHP(((Building) enemy).getHP());
                        }

                    }
                }
            }
        }
    }
}
