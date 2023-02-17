package main.java;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.entities.FoodSystem;
import main.java.entities.animals.Animal;
import main.java.map.Cell;
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

        int counter = 10;

        while (counter > 0) {

            printStatistics();

            for (Cell[] cells : islandMap.getCells()) {
                for (Cell cell : cells) {

                    for (int i = 0; i < cell.getCreatures().size(); i++) {
                        Creature creature = cell.getCreatures().get(i);

                        if (creature instanceof Animal) {
                            ((Animal) creature).move(cell);
                            ((Animal) creature).eat(cell);
                        }

                        creature.reproduce();

                    }

                }
            }

            printStatistics();

            sleep(5000);
            counter--;

        }

    }

    private void printStatistics() {

        for (Cell[] cells : islandMap.getCells()) {
            for (Cell cell : cells) {
                System.out.println("--- " + cell.getId() + " ---");
                for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {
                    int quantity = 0;
                    for (Creature cellCreature : cell.getCreatures()) {
                        if (creature.getName().equals(cellCreature.getName())) {
                            quantity++;
                        }
                    }
                    System.out.println(creature.getImage() + " : " + quantity);
                }
                System.out.println("--- ---");
            }
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
