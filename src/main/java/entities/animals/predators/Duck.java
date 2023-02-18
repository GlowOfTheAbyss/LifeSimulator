package main.java.entities.animals.predators;

public class Duck extends Predator {

    public Duck() {

        super();

        name = "Утка";
        image = "\uD83E\uDD86";

        weight = 1;
        maxCreaturePerLocation = 200;

        maxMovement = 4;
        remainingMovement = maxMovement;
        maxSaturation = 0.15;
        currentSaturation = maxSaturation / 2;

    }

}
