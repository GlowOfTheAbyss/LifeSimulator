package main.java.entities.animals.predators;

public class Mouse extends Predator {

    public Mouse() {

        super();

        name = "Мышь";
        image = "\uD83D\uDC00";

        weight = 0.05;
        maxCreaturePerLocation = 500;

        maxMovement = 1;
        remainingMovement = maxMovement;
        maxSaturation = 0.01;
        currentSaturation = maxSaturation / 2;

    }

}
