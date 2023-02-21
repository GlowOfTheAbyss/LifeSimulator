package main.java;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.entities.animals.Animal;
import main.java.entities.animals.herbivore.Herbivore;
import main.java.entities.animals.predators.Predator;
import main.java.loger.Logger;
import main.java.map.IslandMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

    private final IslandMap islandMap;

    private final ExecutorService executorService = Executors.newFixedThreadPool(6, r -> {
        Thread thread = new Thread();
        thread.setDaemon(true);
        return thread;
    });

    public Simulation() {

        int length = 5;
        int height = 5;

        islandMap = new IslandMap().createMap(length, height);
        CreatureGenerator.getCreatureGenerator().islandMapFillCreature(islandMap);

        FoodSystem.getFoodSystem().generateFoodSystem();

    }

    public void start() {

        while (endCheck()) {

            for (Creature creature : islandMap.getAllCreature()) {

                boolean isAnimal = creature instanceof Animal;

                if (isAnimal) {
                    ((Animal) creature).move();
                    ((Animal) creature).eat();
                }

                creature.reproduce();

                if (isAnimal) {
                    ((Animal) creature).sleep();
                }
            }
            sleep(1000);
            Logger.getLogger().printStatistic(islandMap);

            sleep(3000);

        }

    }
    
    private boolean endCheck() {

        int maxHerbivore = 0;
        int maxPredators = 0;

        for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {
            if (creature instanceof Herbivore) {
                maxHerbivore = maxHerbivore + (creature.getMaxCreaturePerLocation() * islandMap.getSize());
            } else if (creature instanceof Predator) {
                maxPredators = maxPredators + (creature.getMaxCreaturePerLocation() * islandMap.getSize());
            }
        }

        int currentHerbivore = 0;
        int currentPredators = 0;

        for (Creature creature : islandMap.getAllCreature()) {

            if (creature instanceof Herbivore) {
                currentHerbivore++;
            } else if (creature instanceof Predator) {
                currentPredators++;
            }

        }

        if (currentHerbivore <= (maxHerbivore * (0.1 / 100))) {
            System.out.println("----SIMULATION_END");
            System.out.println("----HERBIVORE<0.1%");
            return false;
        } else if (currentPredators <= (maxPredators * (1.0 / 100))) {
            System.out.println("----SIMULATION_END");
            System.out.println("----PREDATORS<1%");
            return false;
        }

        return true;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("sleep error");
        }
    }
}
