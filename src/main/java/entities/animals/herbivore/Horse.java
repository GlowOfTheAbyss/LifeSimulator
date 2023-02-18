package main.java.entities.animals.herbivore;

public class Horse extends Herbivore {

    public Horse() {

        super();

        name = "Лошадь";
        image = "\uD83D\uDC34";

        weight = 400;
        maxCreaturePerLocation = 20;

        maxMovement = 4;
        remainingMovement = maxMovement;
        maxSaturation = 60;
        currentSaturation = maxSaturation / 2;

    }

}
