package entities.animals.herbivore;

public class Sheep extends Herbivore {

    public Sheep() {

        super();

        name = "Овца";
        image = "\uD83D\uDC11";

        weight = 70;
        maxNumberPerCell = 140;

        maxSpeed = 3;
        maxSaturation = 15;
        currentSaturation = maxSaturation / 2;

    }
}
