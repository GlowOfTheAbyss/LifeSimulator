package main.java.entities.animals.herbivore;

public class Rabbit extends Herbivore {

    public Rabbit() {

        super();

        name = "Кролик";
        image = "\uD83D\uDC07";

        weight = 2;
        maxCreaturePerLocation = 150;

        maxMovement = 2;
        remainingMovement = maxMovement;
        maxSaturation = 0.45;
        currentSaturation = maxSaturation / 2;

    }

}
