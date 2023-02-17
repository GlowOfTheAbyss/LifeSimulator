package main.java.entities;

import main.java.fileManager.FileLoader;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.Map;

public class FoodSystem {

    private static final FoodSystem foodSystem = new FoodSystem();

    private final Map<Creature, Map<Creature, Integer>> foodSystemMap = new HashMap<>();

    private FoodSystem() {}

    public void generateFoodSystem() {

        Sheet sheet = FileLoader.getFileLoader().loadFoodTable();

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

            Creature thisCreature = null;
            for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {
                if (creature.getName().equals(sheet.getRow(i).getCell(0).getStringCellValue())) {
                    thisCreature = creature;
                }
            }
            if (thisCreature == null) {
                continue;
            }

            Map<Creature, Integer> valuesMap = new HashMap<>();

            for (int i1 = 1; i1 < sheet.getRow(i).getPhysicalNumberOfCells(); i1++) {

                if (sheet.getRow(i).getCell(i1).getNumericCellValue() == 0) {
                    continue;
                }

                for (Creature creature : CreatureGenerator.getCreatureGenerator().getCreatures()) {

                    if (creature.getName().equals(sheet.getRow(0).getCell(i1).getStringCellValue())) {

                        valuesMap.put(creature, (int) sheet.getRow(i).getCell(i1).getNumericCellValue());

                    }

                }

            }

            foodSystemMap.put(thisCreature, valuesMap);

        }

    }

    public Map<Creature, Integer> getFoodForCreature(Creature searchCreature) {
        Map<Creature, Integer> foodMap = null;

        for (Creature creature : foodSystemMap.keySet()) {
            if (searchCreature.getName().equals(creature.getName())) {
                foodMap = foodSystemMap.get(creature);
            }
        }

        return foodMap;
    }

    public Map<Creature, Map<Creature, Integer>> getFoodSystemMap() {
        return foodSystemMap;
    }

    public static FoodSystem getFoodSystem() {
        return foodSystem;
    }
}
