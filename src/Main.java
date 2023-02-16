import entities.Creature;
import entities.CreatureGenerator;
import map.Cell;
import map.IslandMap;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        IslandMap.getIslandMap().createMap(2, 2);
        CreatureGenerator.getCreatureGenerator().islandMapFillCreature();
        for (Cell[] cells : IslandMap.getIslandMap().getCells()) {
            for (Cell cell : cells) {
                for (Creature creature : cell.getCreatures()) {
                    System.out.println(creature.getId() + " " + creature.getName() + " " + creature.getImage());
                }
            }
        }

        FileInputStream file = new FileInputStream("D:\\Java\\LifeSimulatorNew\\resources\\foodTable.xlsx");
        Workbook workbook = new XSSFWorkbook(file);

        Sheet myExcelSheet = workbook.getSheet("food");



    }

}