package main.java.entities;

import main.java.entities.animals.herbivore.Sheep;
import main.java.entities.animals.predators.Wolf;
import main.java.entities.plants.Grass;
import main.java.map.Cell;
import main.java.map.IslandMap;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatureGenerator {

    private static final CreatureGenerator CREATURE_GENERATOR = new CreatureGenerator();
    private final List<Creature> creatures = new ArrayList<>();

    private CreatureGenerator() {
        creatures.add(new Grass());
        creatures.add(new Wolf());
        creatures.add(new Sheep());
    }

    public void islandMapFillCreature(IslandMap islandMap) {

        for (Cell[] cells : islandMap.getCells()) {

            for (Cell cell : cells) {

                cellFillCreature(cell);

            }

        }

    }

    private void cellFillCreature(Cell cell) {

        for (Creature creature : creatures) {

            int numberOfCreatures = new Random().nextInt(creature.getMaxNumberPerCell() / 2);

            for (int i = 0; i < numberOfCreatures; i++) {

                cell.getCreatures().add(getNewCreature(creature));

            }

        }

    }

    public Creature getNewCreature(Creature creature) {

        try {
            Constructor<? extends Creature> constructor = creature.getClass().getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            System.out.println("error when creating a creature");
            e.printStackTrace();
            return null;
        }

    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public static CreatureGenerator getCreatureGenerator() {
        return CREATURE_GENERATOR;
    }

}
