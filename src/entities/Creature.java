package entities;

public abstract class Creature implements Reproducing {

    protected int weight;
    protected int maxNumberPerCell;

    public int getWeight() {
        return weight;
    }

    public int getMaxNumberPerCell() {
        return maxNumberPerCell;
    }

}
