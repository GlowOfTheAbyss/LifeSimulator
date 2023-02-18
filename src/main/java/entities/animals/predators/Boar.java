package main.java.entities.animals.predators;

public class Boar extends Predator {

    public Boar() {

        super();

        name = "Кабан";
        image = "\uD83D\uDC17";

        weight = 400;
        maxCreaturePerLocation = 50;

        maxMovement = 2;
        remainingMovement = maxMovement;
        maxSaturation = 50;
        currentSaturation = maxSaturation / 2;

    }

}
