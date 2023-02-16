package map;

import entities.Creature;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final List<Creature> creatures = new ArrayList<>();

    private final List<Cell> adjacentCells = new ArrayList<>();

    public Cell() {}

    public List<Creature> getCreatures() {
        return creatures;
    }

    public List<Cell> getAdjacentCells() {
        return adjacentCells;
    }
}
