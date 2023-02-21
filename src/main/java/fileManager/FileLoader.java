package main.java.fileManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.settings.Setting;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileLoader {

    private static final FileLoader fileLoader = new FileLoader();

    private final Path tablePath = Path.of("src/main/resources/foodTable.xlsx");
    private final Path settingsPath = Path.of("src/main/resources/setting.json");

    private FileLoader() {}

    public Sheet loadFoodTable() {

        try (FileInputStream fileInputStream = new FileInputStream(tablePath.toAbsolutePath().toFile());
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            return workbook.getSheet("food");

        } catch (IOException e) {
            System.out.println("file loading error");
            e.printStackTrace();
            return null;
        }

    }

    public Setting loadSettingFile() {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(settingsPath.toAbsolutePath().toUri()), Setting.class);
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
