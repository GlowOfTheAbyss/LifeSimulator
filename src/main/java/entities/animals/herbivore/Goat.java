package main.java.entities.animals.herbivore;

public class Goat extends Herbivore {

    public Goat() {

        super();

        name = "Коза    ";
        image = "\uD83D\uDC10";

        weight = 60;
        maxCreaturePerLocation = 140;

        maxMovement = 3;
        remainingMovement = maxMovement;
        maxSaturation = 10;
        currentSaturation = maxSaturation / 2;

    }

}
