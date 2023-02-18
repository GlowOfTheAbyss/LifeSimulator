package main.java.entities.animals.herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar() {

        super();

        name = "Гусеница";
        image = "\uD83D\uDC1B";

        weight = 0.01;
        maxCreaturePerLocation = 1000;

        maxMovement = 0;
        remainingMovement = maxMovement;
        maxSaturation = 0;
        currentSaturation = maxSaturation / 2;

    }

    @Override
    public void sleep() {
    }
}
