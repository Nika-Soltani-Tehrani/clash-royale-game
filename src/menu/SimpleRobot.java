package menu;

/**
 * The type Simple robot.
 */
public class SimpleRobot extends Robot{

    private static int index = 0;

    /**
     * Instantiates a new Simple robot.
     *
     * @param boardManager the board manager
     */
    public SimpleRobot(BoardManager boardManager) {
        super(boardManager);
    }

    @Override
    public void makeEnemies() {
        super.makeEnemies();
    }

    /**
     * This method is used to check if we need to insert enemies to the game or not.
     */
    public void addEnemy() {
        Creature creature;
        if (this.getAliveEnemies().size() < 4)
        {
            creature = this.getEnemies().get(index++);
            this.getAliveEnemies().add(creature);
            this.getEnemies().remove(creature);
            boardManager.setCellValue((int)creature.getLocation().getX(),(int)creature.getLocation().getY(),creature.getCellValue());
        }
    }
}