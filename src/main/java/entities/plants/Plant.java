package main.java.entities.plants;

import main.java.entities.Creature;
import main.java.entities.CreatureGenerator;
import main.java.loger.Logger;
import main.java.map.Location;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Plant extends Creature {

    @Override
    public void reproduce() {

        int thisCreaturesInLocation = location.creaturesInLocation(this);

        if (ThreadLocalRandom.current().nextInt(101) > 75) {

            if (thisCreaturesInLocation < maxCreaturePerLocation) {

                Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this, location);
                location.getCreatures().add(newCreature);
                Logger.getLogger().addNewCreature(newCreature);

            } else {

                Location randomAdjacentLocation = location.getAdjacentLocations().get(ThreadLocalRandom.current().nextInt(location.getAdjacentLocations().size()));

                if (randomAdjacentLocation.creaturesInLocation(this) < maxCreaturePerLocation) {

                    Creature newCreature = CreatureGenerator.getCreatureGenerator().getNewCreature(this, location);
                    randomAdjacentLocation.getCreatures().add(newCreature);
                    Logger.getLogger().addNewCreature(newCreature);

                }
            }

        }
    }

}
