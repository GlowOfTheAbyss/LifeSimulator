package main.java.entities.plants;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.map.Cell;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Plant extends Creature {

    @Override
    public void reproduce(Cell cell) {
        synchronized (cell) {

            if (cell.thisCreaturesInCell(this) < maxNumberPerCell) {

                if (ThreadLocalRandom.current().nextInt(100) > 25) {
                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this);

                    cell.getCreatures().add(newCreature);
                    System.out.println(newCreature.getId() + " " + newCreature.getImage() + " появился в " + cell.getId());

                } else {

                    Cell randomCell = cell.getAdjacentCells().get(ThreadLocalRandom.current().nextInt(cell.getAdjacentCells().size()));

                    if (randomCell.thisCreaturesInCell(this) < maxNumberPerCell) {

                        Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this);
                        randomCell.getCreatures().add(newCreature);
                        System.out.println(newCreature.getId() + " " + newCreature.getImage() + " появился в " + cell.getId());

                    }

                }

            }

        }


    }

}
