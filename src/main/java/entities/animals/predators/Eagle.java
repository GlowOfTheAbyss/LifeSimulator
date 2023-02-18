package main.java.entities.animals.predators;

public class Eagle extends Predator {

    public Eagle() {

        super();

        name = "Орел";
        image = "\uD83E\uDD85";

        weight = 6;
        maxCreaturePerLocation = 20;

        maxMovement = 3;
        remainingMovement = maxMovement;
        maxSaturation = 1;
        currentSaturation = maxSaturation / 2;

    }
}
