package menu;

import javafx.geometry.Point2D;

public class Arrow extends Spell{

    public Arrow(String color, Point2D location, BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
        this.cost = 3;
        this.range = 4;
        this.name = "arrow";
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
