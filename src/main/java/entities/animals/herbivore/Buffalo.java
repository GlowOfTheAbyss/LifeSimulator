package main.java.entities.animals.herbivore;

public class Buffalo extends Herbivore {

    public Buffalo() {

        super();

        name = "Буйвол";
        image = "\uD83D\uDC03";

        weight = 700;
        maxCreaturePerLocation = 10;

        maxMovement = 3;
        remainingMovement = maxMovement;
        maxSaturation = 100;
        currentSaturation = maxSaturation / 2;

    }

}
