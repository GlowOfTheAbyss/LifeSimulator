package main.java.entities;

import main.java.map.Location;

public abstract class Creature implements Reproducing {

    protected static long idCounter = 0;

    protected long id;
    protected String name;
    protected String image;
    protected int weight;
    protected int maxCreaturePerLocation;

    protected Location location;

    public Creature() {
        this.id = idCounter;
        idCounter++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxCreaturePerLocation() {
        return maxCreaturePerLocation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
