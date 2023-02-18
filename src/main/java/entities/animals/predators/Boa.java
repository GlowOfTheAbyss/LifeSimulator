package main.java.entities.animals.predators;

public class Boa extends Predator {

    public Boa() {

        super();

        name = "Удав";
        image = "\uD83D\uDC0D";

        weight = 15;
        maxCreaturePerLocation = 30;

        maxMovement = 1;
        remainingMovement = maxMovement;
        maxSaturation = 3;
        currentSaturation = maxSaturation / 2;

    }
}
