package main.java.worker;

import main.java.Simulation;
import main.java.fileManager.FileLoader;
import main.java.settings.Setting;

import java.util.Scanner;

public class UserWorker {

    private static final String START = "start";
    private static final String SETTING = "setting";
    private static final String EXIT = "exit";

    private final Scanner scanner = new Scanner(System.in);

    public void start() {

        System.out.println("LifeSimulator Running");

        while (true) {

            System.out.println("write - \"" + START + "\" to start the simulation");
            System.out.println("write - \"" + SETTING + "\" to print settings");
            System.out.println("write - \"" + EXIT + "\" to close the application");

            String line = scanner.nextLine();

            if (START.equalsIgnoreCase(line)) {

                new Simulation().start();
                return;

            } else if (SETTING.equalsIgnoreCase(line)) {

                printSetting();

            } else if (EXIT.equalsIgnoreCase(line)) {
                return;
            } else {
                System.out.println("unknown command");
            }

        }

    }

    private void printSetting() {

        Setting setting = FileLoader.getFileLoader().loadSettingFile();

        System.out.println("----SETTING");
        System.out.println("Island length : " + setting.getLength());
        System.out.println("Island height : " + setting.getHeight());

        System.out.println("Delay between each simulation step : " + setting.getSleepTime());
        System.out.println("Once in how many steps statistics are displayed : " + setting.getCyclesBetweenPrintStatistics());

        System.out.println("Percentage of herbivores to exit the simulation : " + setting.getHerbivoreExitPercent());
        System.out.println("Percentage of predators to exit the simulation : " + setting.getPredatorExitPercent());

        System.out.println("----");

    }

}
