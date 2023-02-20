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
        if (isDead) {
            return;
        }

        synchronized (location) {

            int thisCreaturesInLocation = location.thisCreaturesInLocation(this);

            if (thisCreaturesInLocation >= 2 && thisCreaturesInLocation < maxCreaturePerLocation) {

                if (ThreadLocalRandom.current().nextInt(101) > 25) {
                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this, location);

                    location.addCreature(newCreature);
                    Logger.getLogger().addNewCreature(newCreature);
                    System.out.println(image + " " + id + " MAKE " + newCreature.getImage() + " " + newCreature.getId());
                }
            }
        }
    }

    @Override
    public void move() {
        if (isDead) {
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

        System.out.println(image + " " + id + " " + location.getId() + " -> " + targetLocation.getId());

        location.removeCreature(this);
        this.location = targetLocation;
        remainingMovement--;

    }

    @Override
    public void eat() {
        if (isDead) {
            return;
        }

        Map<Creature, Integer> foodForCreature = FoodSystem.getFoodSystem().getFoodForCreature(this);
        List<Creature> potentialTarget = new ArrayList<>();

        synchronized (location) {

            for (Creature creature : location.getCreatures()) {
                for (Creature foodFromCreature : foodForCreature.keySet()) {
                    if (creature.getName().equals(foodFromCreature.getName())) {
                        potentialTarget.add(creature);
                    }
                }
            }

            if (potentialTarget.isEmpty()) {
            } else {

                int randomTarget = ThreadLocalRandom.current().nextInt(potentialTarget.size());
                Creature targetCreature = potentialTarget.get(randomTarget);
                int killChance = 0;

                for (Creature creature : foodForCreature.keySet()) {
                    if (creature.getName().equals(targetCreature.getName())) {
                        killChance = foodForCreature.get(creature);
                    }
                }

                if (ThreadLocalRandom.current().nextInt(101) < killChance) {

                    currentSaturation = currentSaturation + targetCreature.getWeight();
                    if (currentSaturation > maxSaturation) {
                        currentSaturation = maxSaturation;
                    }

                    Logger.getLogger().addDeadCreature(targetCreature);
                    location.removeCreature(targetCreature);
                    targetCreature.setDead(true);

                    System.out.println(image + " " + id + " EAT " + targetCreature.getImage() + " " + targetCreature.getId());

                }

            }

        }
    }

    @Override
    public void sleep() {

        synchronized (location) {

            currentSaturation = currentSaturation - maxSaturation / 4;

            if (currentSaturation <= 0) {
                Logger.getLogger().addDeadCreature(this);
                this.setDead(true);
                location.removeCreature(this);
                System.out.println(getImage() + " " + getId() + " DEAD_OF_EXHAUSTION");
            } else {
                remainingMovement = maxMovement;
            }

        }

    }

}
