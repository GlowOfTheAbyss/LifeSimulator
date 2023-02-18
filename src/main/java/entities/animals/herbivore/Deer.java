package main.java.entities.animals.herbivore;

public class Deer extends Herbivore {

    public Deer() {

        super();

        name = "Олень";
        image = "\uD83E\uDD8C";

        weight = 300;
        maxCreaturePerLocation = 20;

        maxMovement = 4;
        remainingMovement = maxMovement;
        maxSaturation = 50;
        currentSaturation = maxSaturation / 2;

    }
}
