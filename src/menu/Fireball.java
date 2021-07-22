package menu;

import javafx.geometry.Point2D;
import menu.BoardManager;

/**
 * The type Fireball.
 */
public class Fireball extends Spell{

    private boolean isAlive = true;

    /**
     * Instantiates a new Fireball.
     *
     * @param color     the color
     * @param location  the location
     * @param cellValue the cell value
     */
    public Fireball(String color, Point2D location,BoardManager.CellValue cellValue) {
        super(color,location,cellValue);
        this.cost = 4;
        this.range = 2.5;
        this.name = "fireball";
    }

    @Override
    public void action(BoardManager boardManager)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();
        if (isAlive){
            Creature enemy;

            for (int i = x - (int) range; i < y + range; i++) {
                for (int j = y - (int) range; i < x + range; j++) {
                    if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] != BoardManager.CellValue.GRASS)
                            || boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.WATER
                            || boardManager.getGrid()[(int) Math.ceil(x)][(int) Math.ceil(i)] != BoardManager.CellValue.DESTROY
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
                            if (this.color.equals("red")) {
                                if (boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] == BoardManager.CellValue.BLUEKING) {
                                    boardManager.getBlueKingTower().loseHP(10);
                                }
                                if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] == BoardManager.CellValue.BLUEQUEEN)) {
                                    if (y < 9) {
                                        boardManager.getBlue1PrincessTower().loseHP(10);
                                    }
                                    if (y > 9) {
                                        boardManager.getBlue2PrincessTower().loseHP(10);
                                    }
                                }
                            }
                            if (this.color.equals("blue")) {
                                if (boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] == BoardManager.CellValue.REDKING) {
                                    boardManager.getBlueKingTower().loseHP(10);
                                }
                                if ((boardManager.getGrid()[(int) Math.ceil(i)][(int) Math.ceil(j)] == BoardManager.CellValue.REDQUEEN)) {
                                    if (y < 9) {
                                        boardManager.getRed1PrincessTower().loseHP(10);
                                    }
                                    if (y > 9) {
                                        boardManager.getRed1PrincessTower().loseHP(10);
                                    }
                                }
                            }

                        }
                        isAlive = false;
                        boardManager.setCellValue(x,y,BoardManager.CellValue.GRASS);
                        boardManager.getCreatures().remove(this);
                        if (this.color.equals("blue"))
                        {
                            boardManager.getFriends().remove(this);
                        }
                        if (this.color.equals("red"))
                        {
                            boardManager.getRobot().getEnemies().remove(this);
                        }
                    }
                }
            }
            System.out.println(toString());
        }
    }
    @Override
    public String toString() {
        return "fireball{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}