package ClashRoyal;


import javafx.geometry.Point2D;

public class Rage extends Spell{

    public Rage(String color, Point2D location) {
        super(color,location);
        this.cost = 3;
        this.range = 5;
        this.name = "rage";
    }

    @Override
    public void action(BoardManager boardManager)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();
        Creature friend = null;

        for( int i = x - (int)range; i < y + range; i++)
        {
            for( int j = y - (int)range; i < x + range; j++){
                if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.GRASS)
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.ROAD
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                        ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                        || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.BRIDGE) {
                    friend = boardManager.friendFinder(boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)], color);
                    if (friend != null) {
                        if (friend instanceof Troop) {
                            ((Troop) friend).gainHitSpeed((int)(1.4 * ((Troop) friend).getHitSpeed()));
                            ((Troop) friend).gainPower((int)(1.4 * ((Troop) friend).getHitSpeed()));
                            ((Troop) friend).gainSpeed();
                        }
                        if (friend instanceof Building) {
                            ((Building) friend).gainHitSpeed((int)(1.4 * ((Building) friend).getHitSpeed()));
                            ((Building) friend).gainPower((int)(1.4 * ((Building) friend).getHitSpeed()));

                        }
                    }
                }
            }
        }
    }


}
