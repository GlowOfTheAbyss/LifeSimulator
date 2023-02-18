package main.java.entities;

import main.java.map.Location;

public abstract class Creature implements Reproducing {

    protected static long idCounter = 0;

    protected boolean isDead;

    protected long id;
    protected String name;
    protected String image;
    protected double weight;
    protected int maxCreaturePerLocation;

    protected Location location;

    public Creature() {
        isDead = false;
        this.id = idCounter;
        idCounter++;
    }

    public void setDead(boolean dead) {
        isDead = dead;
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

    public double getWeight() {
        return weight;
    }

    public int getMaxCreaturePerLocation() {
        return maxCreaturePerLocation;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
