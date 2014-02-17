package com.sherlockmatt.gascraft.util;

public class Coords implements Comparable {
    public final int x;
    public final int y;
    public final int z;

    public Coords(double X, double Y, double Z) {
        x = (int) X;
        y = (int) Y;
        z = (int) Z;
    }

    public Coords(int X, int Y, int Z) {
        x = X;
        y = Y;
        z = Z;
    }

    @Override
    public int compareTo(Object o) {
        Coords O = (Coords) o;
        return O == null ? 1 : Math.max(-1,Math.min(1,(x-O.x)+(y-O.y)+(z-O.z)));
    }

    @Override
    public boolean equals (Object o) {
        Coords tO = (Coords) o;
        return tO != null && this.equals(tO.x, tO.y, tO.z);
    }

    @Override
    public int hashCode ()
    {
        int hash = 1;
        hash = 29 * hash + x;
        hash = 29 * hash + y;
        hash = 29 * hash + z;
        return hash;
    }

    public boolean equals (int X, int Y, int Z)
    {
        return (this.x == X && this.y == Y && this.z == Z);
    }

    public boolean equals (double X, double Y, double Z)
    {
        return (this.x == (int) X && this.y == (int) Y && this.z == (int) Z);
    }

    public String toString ()
    {
        return "X: " + x + " Y: " + y + " Z: " + z;
    }
}
