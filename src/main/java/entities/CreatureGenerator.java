package main.java.entities;

import main.java.entities.animals.herbivore.*;
import main.java.entities.animals.predators.*;
import main.java.entities.plants.Grass;
import main.java.map.Location;
import main.java.map.IslandMap;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatureGenerator {

    private static final CreatureGenerator CREATURE_GENERATOR = new CreatureGenerator();
    private final List<Creature> creatures = new ArrayList<>();

    private CreatureGenerator() {
        creatures.add(new Buffalo());
        creatures.add(new Caterpillar());
        creatures.add(new Deer());
        creatures.add(new Goat());
        creatures.add(new Horse());
        creatures.add(new Rabbit());
        creatures.add(new Sheep());

        creatures.add(new Bear());
        creatures.add(new Boa());
        creatures.add(new Boar());
        creatures.add(new Duck());
        creatures.add(new Eagle());
        creatures.add(new Fox());
        creatures.add(new Mouse());
        creatures.add(new Wolf());

        creatures.add(new Grass());
    }

    public void islandMapFillCreature(IslandMap islandMap) {

        for (Location[] cells : islandMap.getLocations()) {

            for (Location cell : cells) {

                locationFillCreature(cell);

            }

        }

    }

    private void locationFillCreature(Location location) {

        for (Creature creature : creatures) {

            int numberOfCreatures = new Random().nextInt(creature.getMaxCreaturePerLocation() / 2);

            for (int i = 0; i < numberOfCreatures; i++) {

                Creature newCreature = getNewCreature(creature, location);

                newCreature.setLocation(location);
                location.getCreatures().add(newCreature);

            }

        }

    }

    public Creature getNewCreature(Creature creature, Location location) {

        try {
            Constructor<? extends Creature> constructor = creature.getClass().getConstructor();
            Creature newCreature = constructor.newInstance();
            newCreature.setLocation(location);
            return newCreature;

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
