package main.java;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.entities.animals.Animal;
import main.java.loger.Logger;
import main.java.map.IslandMap;

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

        int temp = 5;

        while (temp > 0) {

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
            temp--;

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
