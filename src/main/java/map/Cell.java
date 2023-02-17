package main.java.map;

import main.java.entities.Creature;
import main.java.entities.FoodSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cell {

    private static int idCount = 0;

    private final int id;

    private final List<Creature> creatures = new ArrayList<>();

    private final List<Cell> adjacentCells = new ArrayList<>();

    public Cell() {
        id = idCount;
        idCount++;
    }

    public int getId() {
        return id;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public List<Cell> getAdjacentCells() {
        return adjacentCells;
    }

    public int thisCreaturesInCell(Creature thisCreature) {
        int result = 1;
        for (Creature creature : this.getCreatures()) {
            if (creature.getName().equals(thisCreature.getName())) {
                result++;
            }
        }
        return result;
    }

    public int foodInTheCell(Creature thisCreature) {
        int result = 0;
        Map<Creature, Integer> foodMap = FoodSystem.getFoodSystem().getFoodForCreature(thisCreature);

        for (Creature creature : this.getCreatures()) {
            if (foodMap.containsKey(creature)) {
                result++;
            }
        }

        return result;
    }

}
