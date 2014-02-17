package com.sherlockmatt.gascraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGasPipe extends TileEntity{

    private ForgeDirection[] directions = ForgeDirection.VALID_DIRECTIONS;
    private int connectionMask = 0;

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("connectionMask", connectionMask);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        connectionMask = nbt.getInteger("connectionMask");
    }

    public boolean isConnected(ForgeDirection side) {
        return (connectionMask & (1 << side.ordinal())) != 0;
    }

    public void setConnected(ForgeDirection side, boolean value) {
        if (isConnected(side) != value) {
            connectionMask ^= 1 << side.ordinal();
        }
    }

    public void computeConnections(World world) {
        for (ForgeDirection dir : directions) {
            //comment
        }
    }

}
