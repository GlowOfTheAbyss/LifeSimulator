package main.java.entities.animals;

import main.java.entities.Creature;
import main.java.entities.FoodSystem;
import main.java.map.Cell;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Creature implements Moving, Eating {

    protected int maxMovement;

    protected int remainingMovement;

    protected int maxSaturation;

    protected int currentSaturation;

    public int getMaxMovement() {
        return maxMovement;
    }

    public int getRemainingMovement() {
        return remainingMovement;
    }

    public void setRemainingMovement(int remainingMovement) {
        this.remainingMovement = remainingMovement;
    }

    public int getMaxSaturation() {
        return maxSaturation;
    }

    public int getCurrentSaturation() {
        return currentSaturation;
    }

    public void setCurrentSaturation(int currentSaturation) {
        this.currentSaturation = currentSaturation;
    }

    public void reproduce() {
        // reproduce logic
    }

    public void move(Cell cell) {

        int thisCreatureInCell = cell.thisCreaturesInCell(this);

        if (remainingMovement > 0) {

            if (thisCreatureInCell == maxNumberPerCell) {

                moving(cell);
                remainingMovement--;

            } else if (cell.foodInTheCell(this) < thisCreatureInCell * 2
                    || ThreadLocalRandom.current().nextInt(100) < 50) {

                moving(cell);
                remainingMovement--;

            } else if (ThreadLocalRandom.current().nextInt(100) < 10) {

                moving(cell);
                remainingMovement--;

            }

        }

    }

    private void moving(Cell cell) {
        Cell targetCell = null;
        int targetCellThisCreature = 0;
        int targetCellFood = 0;

        for (Cell adjacentCell : cell.getAdjacentCells()) {

            if (targetCell == null) {
                targetCell = adjacentCell;
                targetCellThisCreature = adjacentCell.thisCreaturesInCell(this);
                targetCellFood = cell.foodInTheCell(this);
            } else {
                if (adjacentCell.thisCreaturesInCell(this) < targetCellThisCreature || cell.foodInTheCell(this) > targetCellFood) {
                    targetCell = adjacentCell;
                }
            }

        }

        if (targetCellThisCreature == maxNumberPerCell) {
            return;
        }

        cell.getCreatures().remove(this);
        targetCell.getCreatures().add(this);

    }

    @Override
    public void eat(Cell cell) {

        if (cell.foodInTheCell(this) == 0) {
            return;
        }

        Map<Creature, Integer> foodMap = FoodSystem.getFoodSystem().getFoodForCreature(this);

        for (Creature creature : foodMap.keySet()) {
            for (Creature cellCreature : cell.getCreatures()) {

                if (creature.getName().equals(cellCreature.getName())) {

                    Integer chance = foodMap.get(creature);



                }

            }
        }


    }
}
