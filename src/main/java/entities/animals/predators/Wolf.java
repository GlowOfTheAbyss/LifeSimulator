package main.java.entities.animals.predators;

public class Wolf extends Predator {

    public Wolf() {

        super();

        name = "Волк";
        image = "\uD83D\uDC3A";

        weight = 50;
        maxCreaturePerLocation = 30;

        maxMovement = 3;
        remainingMovement = maxMovement;
        maxSaturation = 8;
        currentSaturation = maxSaturation / 2;

    }

}
