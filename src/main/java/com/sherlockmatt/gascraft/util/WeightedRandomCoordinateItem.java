package com.sherlockmatt.gascraft.util;


public class WeightedRandomCoordinateItem implements Comparable
{
    public double itemWeight;
    public Coords coords;

    public WeightedRandomCoordinateItem(double weight, Coords coords)
    {
        this.itemWeight = weight;
        this.coords = coords;
    }

    @Override
    public int compareTo(Object o) {
        WeightedRandomCoordinateItem wrci = (WeightedRandomCoordinateItem) o;
        if (wrci == null)
            throw new NullPointerException("Object is null. This is bad, 'kay?");

        return (this.itemWeight > wrci.itemWeight) ? 1 : (this.itemWeight == wrci.itemWeight) ? 0 : -1;
    }

    @Override
    public boolean equals(Object o) {
        return this.itemWeight == ((WeightedRandomCoordinateItem)o).itemWeight && this.coords.equals(((WeightedRandomCoordinateItem)o).coords);
    }
}
