package main.java.map;

import main.java.entities.Creature;
import main.java.entities.FoodSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Location {

    private static int idCount = 0;

    private final int id;

    private final List<Creature> creatures = new ArrayList<>();

    private final List<Location> adjacentLocations = new ArrayList<>();

    public Location() {
        id = idCount;
        idCount++;
    }

    public int getId() {
        return id;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public List<Location> getAdjacentLocations() {
        return adjacentLocations;
    }

    public int thisCreaturesInLocation(Creature thisCreature) {
        int result = 0;
        for (Creature creature : this.getCreatures()) {
            if (creature.getName().equals(thisCreature.getName())) {
                result++;
            }
        }
        return result;
    }

    public int foodInLocation(Creature thisCreature) {
        int result = 0;
        Map<Creature, Integer> foodMap = FoodSystem.getFoodSystem().getFoodForCreature(thisCreature);

        for (Creature creature : foodMap.keySet()) {
            for (Creature locationCreature : creatures) {
                if (creature.getName().equals(locationCreature.getName())) {
                    result++;
                }
            }
        }
        return result;
    }

}
