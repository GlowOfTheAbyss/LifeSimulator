package main.java.loger;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.map.IslandMap;
import main.java.map.Location;

import java.util.*;

public class Logger {

    private static final Logger LOGGER = new Logger();

    private final List<Creature> deadCreature = new ArrayList<>();
    private final List<Creature> newCreature = new ArrayList<>();

    private int counter = 1;

    private Logger() {}

    public List<Creature> getDeadCreature() {
        return deadCreature;
    }

    public void addDeadCreature(Creature creature) {
        deadCreature.add(creature);
    }

    public void clearDeadCreature() {
        deadCreature.clear();
    }

    public List<Creature> getNewCreature() {
        return newCreature;
    }

    public void addNewCreature(Creature creature) {
        newCreature.add(creature);
    }

    public void clearNewCreature() {
        newCreature.clear();
    }

    public void printStatistic(IslandMap map) {

        System.out.println("----STEP : " + counter);

        printMap(map);
        printStats(map);
        clearDeadCreature();
        clearNewCreature();

        System.out.println("----");
        System.out.println();

        counter++;

    }

    public void printMap(IslandMap map) {

        System.out.println("----MAP");

        for (Location[] locations : map.getLocations()) {
            for (Location location : locations) {
                Map<Creature, Integer> creatureInLocation = findNumberCreaturesInLocation(location);

                System.out.print("|");
                for (Creature creature : creatureInLocation.keySet()) {
                    System.out.print(creature.getImage() + ":" + creatureInLocation.get(creature) + " ");
                }
                System.out.println("|");

            }
            System.out.println();
        }

    }

    public void printStats(IslandMap map) {

        System.out.println("----STATS");
        System.out.println("ALL_CREATURE:");
        for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {
            int number = 0;
            for (Location[] locations : map.getLocations()) {
                for (Location location : locations) {
                    number = number + findNumberCreaturePerList(creature, location.getCreatures());
                }
            }
            System.out.print(creature.getImage() + " : " + number + " ");
        }
        System.out.println();

        System.out.println("DEAD_CREATURE:");
        for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {
            System.out.print(creature.getImage() + " : " + findNumberCreaturePerList(creature, getDeadCreature()) + " ");
        }
        System.out.println();

        System.out.println("NEW_CREATURE:");
        for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {
            System.out.print(creature.getImage() + " : " + findNumberCreaturePerList(creature, getNewCreature()) + " ");
        }
        System.out.println();

    }

    private int findNumberCreaturePerList(Creature creature, List<Creature> creatures) {
        int result = 0;
        for (Creature creatureInList : creatures) {
            if (creatureInList.getName().equals(creature.getName())) {
                result++;
            }
        }
        return result;
    }

    private Map<Creature, Integer> findNumberCreaturesInLocation(Location location) {

        Map<Creature, Integer> creatureInLocation = new HashMap<>();
        for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {
            int thisCreaturesInLocation = location.thisCreaturesInLocation(creature);
            creatureInLocation.put(creature, thisCreaturesInLocation);
        }

        return creatureInLocation;

    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
