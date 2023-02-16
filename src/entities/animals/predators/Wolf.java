package entities.animals.predators;

public class Wolf extends Predator {

    public Wolf() {

        super();

        name = "Волк";
        image = "\uD83D\uDC3A";

        weight = 50;
        maxNumberPerCell = 30;

        maxSpeed = 3;
        maxSaturation = 8;
        currentSaturation = maxSaturation / 2;

    }

}
