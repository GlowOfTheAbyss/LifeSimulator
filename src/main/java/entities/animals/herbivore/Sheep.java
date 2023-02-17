package main.java.entities.animals.herbivore;

public class Sheep extends Herbivore {

    public Sheep() {

        super();

        name = "Овца";
        image = "\uD83D\uDC11";

        weight = 70;
        maxNumberPerCell = 140;

        maxMovement = 3;
        remainingMovement = maxMovement;
        maxSaturation = 15;
        currentSaturation = maxSaturation / 2;

    }
}
