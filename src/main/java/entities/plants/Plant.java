package main.java.entities.plants;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.loger.Logger;
import main.java.map.Location;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Plant extends Creature {

    @Override
    public void reproduce() {
        synchronized (location) {

            int thisCreaturesInLocation = location.thisCreaturesInLocation(this);

            int random = ThreadLocalRandom.current().nextInt(1, 5);

            for (int i = 0; i < random; i++) {

                if (thisCreaturesInLocation < maxCreaturePerLocation) {

                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this, location);
                    location.getCreatures().add(newCreature);
                    Logger.getLogger().addNewCreature(newCreature);

                } else {

                    Location randomAdjacentLocation = location.getAdjacentLocations().get(ThreadLocalRandom.current().nextInt(location.getAdjacentLocations().size()));
                    if (randomAdjacentLocation.thisCreaturesInLocation(this) < maxCreaturePerLocation) {
                        Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this, location);
                        randomAdjacentLocation.getCreatures().add(newCreature);
                        Logger.getLogger().addNewCreature(newCreature);

                    }
                }
            }
        }
    }

}
