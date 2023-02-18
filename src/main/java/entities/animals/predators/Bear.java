package main.java.entities.animals.predators;

public class Bear extends Predator {

    public Bear() {

        super();

        name = "Медведь";
        image = "\uD83D\uDC3B";

        weight = 500;
        maxCreaturePerLocation = 5;

        maxMovement = 2;
        remainingMovement = maxMovement;
        maxSaturation = 80;
        currentSaturation = maxSaturation / 2;

    }

}
