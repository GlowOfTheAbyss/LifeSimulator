package entities.animals;

import entities.Creature;

public abstract class Animal extends Creature implements Moving, Eating {

    protected int maxSpeed;
    protected int maxSaturation;

    protected int currentSaturation;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxSaturation() {
        return maxSaturation;
    }

    public int getCurrentSaturation() {
        return currentSaturation;
    }

    public void setCurrentSaturation(int currentSaturation) {
        this.currentSaturation = currentSaturation;
    }

    public void reproduce() {
        // reproduce logic
    }

    public void move() {
        // move logic
    }

}
