package main.java.map;

import main.java.entities.Creature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IslandMap {

    private Location[][] locations;

    private int size;
    private final List<Location> locationList = new ArrayList<>();

    public IslandMap() {}

    public IslandMap createMap(int length, int height) {

        size = length * height;
        generateLocations(length, height);
        linkLocations();
        return this;

    }

    private void generateLocations(int length, int height) {
        locations = new Location[length][height];

        for (int i = 0; i < locations.length; i++) {

            for (int i1 = 0; i1 < locations[i].length; i1++) {

                locations[i][i1] = new Location();

            }

        }

    }

    private void linkLocations() {

        for (int i = 0; i < locations.length; i++) {

            for (int i1 = 0; i1 < locations[i].length; i1++) {

                if (i1 + 1 < locations[i].length) {
                    locations[i][i1].getAdjacentLocations().add(locations[i][i1 + 1]);
                    locations[i][i1 + 1].getAdjacentLocations().add(locations[i][i1]);
                }

                if (i + 1 < locations.length) {
                    locations[i][i1].getAdjacentLocations().add(locations[i + 1][i1]);
                    locations[i + 1][i1].getAdjacentLocations().add(locations[i][i1]);
                }

            }

        }

    }

    public int getSize() {
        return size;
    }

    public List<Location> getLocationList() {
        if (locationList.isEmpty()) {
            for (Location[] location : locations) {
                locationList.addAll(Arrays.asList(location));
            }
        }
        return locationList;
    }

    public List<Creature> getAllCreature() {
        List<Creature> allCreatures = new ArrayList<>();

        for (Location location : getLocationList()) {
            allCreatures.addAll(location.getCreatures());
        }

        return allCreatures;

    }

    public Location[][] getLocations() {
        return locations;
    }

}
