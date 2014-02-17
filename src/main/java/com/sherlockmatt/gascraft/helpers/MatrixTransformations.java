package com.sherlockmatt.gascraft.helpers;

import net.minecraftforge.common.util.ForgeDirection;

public class MatrixTransformations {

    public static void mirrorY(float[][] targetArray) {
        float temp = targetArray[1][0];
        targetArray[1][0] = (targetArray[1][1] - 0.5F) * -1F + 0.5F; // 1 -> 0.5F -> -0.5F -> 0F
        targetArray[1][1] = (temp - 0.5F) * -1F + 0.5F; // 0 -> -0.5F -> 0.5F -> 1F
    }

    public static void rotate(float[][] targetArray) {
        for (int i = 0; i < 2; i++) {
            float temp = targetArray[2][i];
            targetArray[2][i] = targetArray[1][i];
            targetArray[1][i] = targetArray[0][i];
            targetArray[0][i] = temp;
        }
    }

    public static void transform(float[][] targetArray, ForgeDirection direction) {
        if ((direction.ordinal() & 0x1) == 1) {
            mirrorY(targetArray);
        }

        for (int i = 0; i < (direction.ordinal() >> 1); i++) {
            rotate(targetArray);
        }
    }

}
