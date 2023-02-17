package main.java.entities;

public abstract class Creature implements Reproducing {

    protected static long idCounter = 0;

    protected long id;
    protected String name;
    protected String image;
    protected int weight;
    protected int maxNumberPerCell;

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

    public int getMaxNumberPerCell() {
        return maxNumberPerCell;
    }

}
