package entities.animals;

import entities.Creature;

public abstract class Animal extends Creature implements Moving{

    protected int maxSpeed;
    protected int maxSaturation;

    protected int currentSaturation;

    public void reproduce() {
        // reproduce logic
    }

    public void move() {
        // move logic
    }

}
