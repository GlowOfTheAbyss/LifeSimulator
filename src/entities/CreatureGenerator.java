package entities;

import entities.animals.herbivore.Sheep;
import entities.animals.predators.Wolf;
import entities.plants.Grass;
import map.Cell;
import map.IslandMap;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatureGenerator {

    private static final CreatureGenerator creatureGenerator = new CreatureGenerator();
    private final List<Creature> creatures = new ArrayList<>();

    private CreatureGenerator() {
        creatures.add(new Grass());
        creatures.add(new Wolf());
        creatures.add(new Sheep());
    }

    public void islandMapFillCreature() {

        for (Cell[] cells : IslandMap.getIslandMap().getCells()) {

            for (Cell cell : cells) {

                cellFillCreature(cell);

            }

        }

    }

    private void cellFillCreature(Cell cell) {

        for (Creature creature : creatures) {

            int numberOfCreatures = new Random().nextInt(creature.getMaxNumberPerCell() / 2);

            for (int i = 0; i < numberOfCreatures; i++) {
                try {
                    Constructor<? extends Creature> constructor = creature.getClass().getConstructor();
                    cell.getCreatures().add(constructor.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public static CreatureGenerator getCreatureGenerator() {
        return creatureGenerator;
    }

}
