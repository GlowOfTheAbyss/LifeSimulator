package main.java.entities.plants;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.map.Location;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Plant extends Creature {

    @Override
    public void reproduce() {
        synchronized (location) {

            int thisCreaturesInLocation = location.thisCreaturesInLocation(this);

            if (thisCreaturesInLocation < maxCreaturePerLocation) {

                if (ThreadLocalRandom.current().nextInt(101) > 25) {
                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this);

                    location.getCreatures().add(newCreature);

                }

            } else {

                Location randomAdjacentLocation = location.getAdjacentLocations().get(ThreadLocalRandom.current().nextInt(location.getAdjacentLocations().size()));

                if (randomAdjacentLocation.thisCreaturesInLocation(this) < maxCreaturePerLocation) {

                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this);
                    randomAdjacentLocation.getCreatures().add(newCreature);

                }

            }
        }
    }

}
