package entities;

public abstract class Creature implements Reproducing {

    protected static int idCounter = 0;

    protected int id;
    protected String name;
    protected String image;
    protected int weight;
    protected int maxNumberPerCell;

    public Creature() {
        this.id = idCounter;
        idCounter++;
    }

    public int getId() {
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
