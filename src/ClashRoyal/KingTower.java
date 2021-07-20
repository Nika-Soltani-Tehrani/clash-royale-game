package ClashRoyal;

import javafx.geometry.Point2D;

public class KingTower extends Tower{

    private boolean isActive = false;

    public KingTower(String color,Point2D location,int id,BoardManager.CellValue cellValue) {
        super(color,location,id,cellValue);
        this.range = 7;
        this.hitSpeed = 1;
        this.location = location;
    }

    @Override
    public void action(BoardManager boardManager)
    {
        if (isKingActive(boardManager)){
            int x = (int) location.getX();
            int y = (int) location.getY();
            Creature enemy = null;

            for (int i = x - (int) range; i < y + range; i++) {
                for (int j = y - (int) range; i < x + range; j++) {
                    if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.GRASS)
                            ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                            ||boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
                            || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.ROAD
                            || boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.BRIDGE) {
                        enemy = boardManager.enemyFinder(boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(y)], color);
                        if (enemy != null) {
                            if (enemy instanceof Troop) {
                                ((Troop) enemy).loseHP(this.damage);
                            }
                            if (enemy instanceof Building) {
                                ((Building) enemy).loseHP(this.damage);
                            }

                        }
                    }
                }
            }
        }
    }

    public boolean isKingActive(BoardManager boardManager)
    {
        if(this.color.equals("blue"))
        {
            if (boardManager.bPrincess < 2)
                return true;
        }

        if(this.color.equals("red"))
        {
            if (boardManager.rPrincess < 2)
                return true;
        }
        return false;
    }
}
