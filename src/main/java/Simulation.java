package main.java;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.entities.animals.Animal;
import main.java.loger.Logger;
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

        int counter = 10;

        while (counter > 0) {

            for (Location[] locations : islandMap.getLocations()) {
                for (Location location : locations) {
                    for (int i = 0; i < location.getCreatures().size(); i++) {
                        Creature creature = location.getCreatures().get(i);

                        if (creature instanceof Animal) {
                            ((Animal) creature).move();
                            ((Animal) creature).eat();
                        }
                        creature.reproduce();
                        if (creature instanceof  Animal) {
                            ((Animal) creature).sleep();
                        }

                    }
                }
            }

            Logger.getLogger().printStatistic(islandMap);

            sleep(3000);
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
