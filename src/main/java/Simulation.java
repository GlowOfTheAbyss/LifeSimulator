package main.java;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.entities.animals.Animal;
import main.java.entities.animals.herbivore.Herbivore;
import main.java.entities.animals.predators.Predator;
import main.java.fileManager.FileLoader;
import main.java.loger.Logger;
import main.java.map.IslandMap;
import main.java.settings.Setting;

public class Simulation {

    private final IslandMap islandMap;
    private final Setting setting;

    public Simulation() {

        setting = FileLoader.getFileLoader().loadSettingFile();

        islandMap = new IslandMap().createMap(setting.getLength(), setting.getHeight());
        CreatureGenerator.getCreatureGenerator().islandMapFillCreature(islandMap);

        FoodSystem.getFoodSystem().generateFoodSystem();

    }

    public void start() {

        Logger.getLogger().printMap(islandMap);
        int stepCounter = 0;

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

            if (stepCounter == setting.getCyclesBetweenPrintStatistics()) {
                Logger.getLogger().printStatistic(islandMap);
                stepCounter = 0;
            }

            sleep(setting.getSleepTime() * 1000);
            stepCounter++;

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

        if (currentHerbivore <= (maxHerbivore * (setting.getHerbivoreExitPercent() / 100))) {
            System.out.println("----SIMULATION_END");
            System.out.println("----HERBIVORE<0.1%");
            return false;
        } else if (currentPredators <= (maxPredators * (setting.getPredatorExitPercent() / 100))) {
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
