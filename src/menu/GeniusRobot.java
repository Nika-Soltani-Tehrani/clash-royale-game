package menu;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * The type Genius robot.
 */
public class GeniusRobot extends Robot{

    /**
     * Instantiates a new Genius robot.
     *
     * @param boardManager the board manager
     */
    public GeniusRobot(BoardManager boardManager) {
        super(boardManager);
    }

    @Override
    public void makeEnemies() {
        super.makeEnemies();
    }

    /**
     * Add enemy.
     *
     * @param name     the name
     * @param location the location
     */
    public void addEnemy(String name, Point2D location)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();

        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                    if (creature instanceof Troop)
                    {
                        for (int i = 0; i < ((Troop) creature).getCount(); i++)
                        {
                           aliveEnemies.add(new Archer("red",new Point2D(32-x+1,y),i, BoardManager.CellValue.rARCHER));
                        }
                    }
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
        if (name.equals("archer"))
        {
            Iterator<Creature> it = this.getEnemies().iterator();
            while (it.hasNext())
            {
                Creature creature = it.next();
                if (creature.getName().equals(name))
                {
                    aliveEnemies.add(creature);
                    creature.setLocation(new Point2D(32 - x,y));
                }
            }
        }
    }

    /**
     * Smart adder.
     */
    public void smartAdder(){
        int right=0;
        int left=0;
        int column;
        int row;
        for(Creature creature: boardManager.getFriends()){
            if(creature.getLocation().getY()<=9){
                left++;
            }else {
                right++;
            }
        }
        if(right>=left){
            column = (int) Math.random()*(15-9+1)+9;
            row = (int) Math.random()*(14-2+1)+2;
        }else{
            column = (int) Math.random()*(15-9+1)+9;
            row = (int) Math.random()*(14-2+1)+2;
        }
        addEnemy(randomCreature(),new Point2D(row,column));

    }

    /**
     * Random creature string.
     *
     * @return the string
     */
    public String randomCreature(){
        String[] names= {"archer","arrow","babyDragon","barbarian","cannon","fireball","giant","inferno",
        "miniPekka","valkyrie","wizard"};
        Random r = new Random();
        int choice = r.nextInt(11);
        return names[choice];
    }
}