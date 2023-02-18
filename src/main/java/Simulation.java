package main.java;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.entities.animals.Animal;
import main.java.map.Location;
import main.java.map.IslandMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

    private final IslandMap islandMap;

    public Simulation() {

        int length = 2;
        int height = 2;

        islandMap = new IslandMap().createMap(length, height);
        CreatureGenerator.getCreatureGenerator().islandMapFillCreature(islandMap);

        FoodSystem.getFoodSystem().generateFoodSystem();

    }

    public void start() {

        int counter = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(6, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        while (counter > 0) {

            for (Location[] cells : islandMap.getLocations()) {
                for (Location cell : cells) {

                    for (int i = 0; i < cell.getCreatures().size(); i++) {
                        Creature creature = cell.getCreatures().get(i);

                        executorService.submit(() -> {
                            if (creature instanceof Animal) {
                                ((Animal) creature).move();
                                ((Animal) creature).eat();
                            }
                            creature.reproduce();
                        });
                    }

                }
            }

            sleep(5000);
            counter--;

        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("sleep error");
        }
    }
}
