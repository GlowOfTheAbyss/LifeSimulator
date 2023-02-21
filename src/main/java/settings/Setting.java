package main.java.settings;

public class Setting {

    int length;
    int height;

    int sleepTime;
    int cyclesBetweenPrintStatistics;

    double herbivoreExitPercent;
    double predatorExitPercent;

    public Setting() {}

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getCyclesBetweenPrintStatistics() {
        return cyclesBetweenPrintStatistics;
    }

    public void setCyclesBetweenPrintStatistics(int cyclesBetweenPrintStatistics) {
        this.cyclesBetweenPrintStatistics = cyclesBetweenPrintStatistics;
    }

    public double getHerbivoreExitPercent() {
        return herbivoreExitPercent;
    }

    public void setHerbivoreExitPercent(double herbivoreExitPercent) {
        this.herbivoreExitPercent = herbivoreExitPercent;
    }

    public double getPredatorExitPercent() {
        return predatorExitPercent;
    }

    public void setPredatorExitPercent(double predatorExitPercent) {
        this.predatorExitPercent = predatorExitPercent;
    }

}
