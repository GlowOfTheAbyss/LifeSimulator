package main.java.entities.animals;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.loger.Logger;
import main.java.map.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Creature implements Moving, Eating, Sleeping {

    protected int maxMovement;

    protected int remainingMovement;

    protected double maxSaturation;

    protected double currentSaturation;

    @Override
    public void reproduce() {
        if (isDead()) {
            return;
        }

        synchronized (location) {

            int thisCreaturesInLocation = location.thisCreaturesInLocation(this);

            if (thisCreaturesInLocation >= 2 && thisCreaturesInLocation < maxCreaturePerLocation) {

                if (ThreadLocalRandom.current().nextInt(101) > 25) {
                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this);

                    location.addCreature(newCreature);
                    Logger.getLogger().addNewCreature(newCreature);
                }
            }
        }
    }

    @Override
    public void move() {
        if (isDead()) {
            return;
        }

        int thisCreaturesInLocation = location.thisCreaturesInLocation(this);

        synchronized (location) {

            while (remainingMovement > 0) {

                if (thisCreaturesInLocation == maxCreaturePerLocation) {
                    moving();
                } else if (location.foodInLocation(this) < thisCreaturesInLocation * 2
                        && ThreadLocalRandom.current().nextInt(101) > 50) {
                    moving();
                } else if (ThreadLocalRandom.current().nextInt(101) > 90) {
                    moving();
                } else {
                    remainingMovement = 0;
                    break;
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
        targetLocation.addCreature(this);
        location.removeCreature(this);
        this.location = targetLocation;
        remainingMovement--;

    }

    @Override
    public void eat() {
        if (isDead()) {
            return;
        }

        Map<Creature, Integer> foodForCreature = FoodSystem.getFoodSystem().getFoodForCreature(this);
        List<Creature> food = new ArrayList<>();

        synchronized (location) {

            for (Creature creature : location.getCreatures()) {
                for (Creature foodCreature : foodForCreature.keySet()) {
                    if (creature.getName().equals(foodCreature.getName())) {
                        food.add(creature);
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

                            targetCreature.setDead(true);
                            Logger.getLogger().addDeadCreature(targetCreature);
                        }
                    }
                }

            }

        }
    }

    @Override
    public void sleep() {

        synchronized (location) {

            currentSaturation = currentSaturation - maxSaturation / 4;

//            if (currentSaturation <= 0) {
//                this.setDead(true);
//                Logger.getLogger().addDeadCreature(this);
//            }

            if (isDead) {
                location.removeCreature(this);
            } else {
                remainingMovement = maxMovement;
            }

        }

    }

}
