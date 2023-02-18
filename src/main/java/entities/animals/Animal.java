package main.java.entities.animals;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.map.Location;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void reproduce() {
        synchronized (location) {

            int thisCreaturesInLocation = location.thisCreaturesInLocation(this);

            if (thisCreaturesInLocation >= 2 && thisCreaturesInLocation < maxCreaturePerLocation) {

                if (ThreadLocalRandom.current().nextInt(101) > 25) {
                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this);

                    location.getCreatures().add(newCreature);

                }
            }
        }
    }

    @Override
    public void move() {

        int thisCreaturesInLocation = location.thisCreaturesInLocation(this);

        synchronized (location) {

            if (remainingMovement > 0) {

                if (thisCreaturesInLocation == maxCreaturePerLocation) {
                    moving();
                } else if (location.foodInLocation(this) < thisCreaturesInLocation * 2
                        && ThreadLocalRandom.current().nextInt(101) > 50) {
                    moving();
                } else if (ThreadLocalRandom.current().nextInt(101) > 90) {
                    moving();
                }

            }

        }

    }

    private void moving() {

        Location targetLocation = null;
        int thisCreaturesInTargetLocation = 0;
        int foodInTargetLocation = 0;

        for (Location adjacentLocation : location.getAdjacentLocations()) {

            if (targetLocation == null) {
                targetLocation = adjacentLocation;
                thisCreaturesInTargetLocation = adjacentLocation.thisCreaturesInLocation(this);
                foodInTargetLocation = adjacentLocation.foodInLocation(this);
            } else {
                if (adjacentLocation.thisCreaturesInLocation(this) < thisCreaturesInTargetLocation
                        || adjacentLocation.foodInLocation(this) > foodInTargetLocation) {
                    targetLocation = adjacentLocation;
                }
            }

        }

        if (thisCreaturesInTargetLocation == maxCreaturePerLocation) {
            return;
        }

        assert targetLocation != null;
        targetLocation.getCreatures().add(this);
        location.getCreatures().remove(this);
        this.location = targetLocation;

    }

    @Override
    public void eat() {

        Map<Creature, Integer> foodForCreature = FoodSystem.getFoodSystem().getFoodForCreature(this);
        List<Creature> food = new ArrayList<>();

        synchronized (location) {

            for (Creature creature : location.getCreatures()) {
                for (Creature foodCreature : foodForCreature.keySet()) {
                    if (creature.getName().equals(foodCreature.getName())) {
                        food.add(foodCreature);
                    }
                }
            }

            for (int i = 0; i < 3; i++) {

                int randomFood = ThreadLocalRandom.current().nextInt(food.size());
                Creature targetCreature = food.get(randomFood);

                for (Creature creature : foodForCreature.keySet()) {
                    if (creature.getName().equals(targetCreature.getName())) {
                        int eatChance = foodForCreature.get(creature);

                        if (eatChance > ThreadLocalRandom.current().nextInt(101)) {

                            currentSaturation = currentSaturation + targetCreature.getWeight();
                            if (currentSaturation > maxSaturation) {
                                currentSaturation = maxSaturation;
                            }

                            location.getCreatures().remove(targetCreature);
                        }
                    }
                }

            }

        }
    }
}
