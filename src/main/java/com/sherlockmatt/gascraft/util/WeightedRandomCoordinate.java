package com.sherlockmatt.gascraft.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandomCoordinate {

    private static double getTotalWeight(Collection var1)
    {
        double TotalWeight = 0;
        WeightedRandomCoordinateItem item;

        for (Iterator iterator = var1.iterator(); iterator.hasNext(); TotalWeight += item.itemWeight)
        {
            item = (WeightedRandomCoordinateItem) iterator.next();
        }

        return TotalWeight;
    }

    public static WeightedRandomCoordinateItem getRandomItem(Random var1, Collection var2, double var3)
    {
        if (var3 <= 0)
        {
            return null;
        }
        else
        {
            double weight = var3*var1.nextDouble();
            Iterator iterator;
            iterator = var2.iterator();
            WeightedRandomCoordinateItem item;
            do
            {
                if (!iterator.hasNext())
                {
                    return null;
                }
                item = (WeightedRandomCoordinateItem) iterator.next();
                weight -= item.itemWeight;
            }
            while (weight >= 0);

            return item;
        }
    }

    public static WeightedRandomCoordinateItem getRandomItem(Random var1, Collection var2)
    {
        return getRandomItem(var1, var2, getTotalWeight(var2));
    }

}
