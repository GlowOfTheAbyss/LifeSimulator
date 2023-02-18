package main.java.entities.animals.predators;

public class Fox extends  Predator {

    public Fox() {

        super();

        name = "Лиса";
        image = "\uD83E\uDD8A";

        weight = 8;
        maxCreaturePerLocation = 30;

        maxMovement = 2;
        remainingMovement = maxMovement;
        maxSaturation = 2;
        currentSaturation = maxSaturation / 2;

    }

}
