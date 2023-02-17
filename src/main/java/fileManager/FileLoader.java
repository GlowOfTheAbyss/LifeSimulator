package main.java.fileManager;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class FileLoader {

    private static final FileLoader fileLoader = new FileLoader();

    private FileLoader() {}

    public Sheet loadFoodTable() {

        try (FileInputStream fileInputStream = new FileInputStream("D:\\Java\\LifeSimulatorNew\\src\\main\\resources\\foodTable.xlsx");
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            return workbook.getSheet("food");

        } catch (IOException e) {
            System.out.println("file loading error");
            e.printStackTrace();
            return null;
        }

    }

    public static FileLoader getFileLoader() {
        return fileLoader;
    }
}
