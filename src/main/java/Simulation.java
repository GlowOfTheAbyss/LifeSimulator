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

    public Simulation() {

        int length = 5;
        int height = 5;

        islandMap = new IslandMap().createMap(length, height);
        CreatureGenerator.getCreatureGenerator().islandMapFillCreature(islandMap);

        FoodSystem.getFoodSystem().generateFoodSystem();

    }

    public void start() {

        ExecutorService executorService = Executors.newFixedThreadPool(6, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        while (endCheck()) {

            for (Creature creature : islandMap.getAllCreature()) {

                if (creature instanceof Animal) {
                    ((Animal) creature).move();
                    ((Animal) creature).eat();
                }
                creature.reproduce();
                if (creature instanceof Animal) {
                    ((Animal) creature).sleep();
                }

            }

            Logger.getLogger().printStatistic(islandMap);

            sleep(3000);

        }

    }
    
    private boolean endCheck() {
        int alivePredator = 0;

        for (Creature creature : islandMap.getAllCreature()) {
            
            if (creature instanceof Herbivore) {
                System.out.println("----SIMULATION_END");
                System.out.println("ALL_HERBIVORE_DIED");
                return true;
            }
            
            if (creature instanceof Predator) {
                alivePredator++;
            }
            
            if (alivePredator > 10) {
                System.out.println("----SIMULATION_END");
                System.out.println("<10_PREDATORS_LEFT");
                return true;
            }
            
        }

        return false;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("sleep error");
        }
    }
}
