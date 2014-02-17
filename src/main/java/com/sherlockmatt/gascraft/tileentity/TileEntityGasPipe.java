package com.sherlockmatt.gascraft.tileentity;

import com.sherlockmatt.gascraft.block.BlockGasDistributor;
import com.sherlockmatt.gascraft.block.BlockPipeGasPipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGasPipe extends TileEntity{

    private ForgeDirection[] directions = ForgeDirection.VALID_DIRECTIONS;
    private int connectionMask = 0;

    // These are just placeholders, they will be overwritten by the renderer
    @SideOnly(Side.CLIENT)
    public IIcon currentTexture;
    @SideOnly(Side.CLIENT)
    public IIcon[] textureArray;

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

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

    public boolean isConnected(int side) {
        return (connectionMask & (1 << side)) != 0;
    }

    public void setConnected(ForgeDirection side, boolean value) {
        if (isConnected(side) != value) {
            connectionMask ^= 1 << side.ordinal();
        }
    }

    public int getConnectionMask() {
        return connectionMask;
    }

    public void computeConnections() {
        World world = this.worldObj;
        for (ForgeDirection dir : directions) {
            TileEntity tile = world.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
            setConnected(dir, (tile != null) && (tile instanceof TileEntityGasPipe || tile instanceof TileEntityGasAmplifier)); //Amplifier for now, will update when I have the distributor tile code.
        }
    }

    public void onBlockPlaced() {
        icons = ((BlockPipeGasPipe)this.worldObj.getBlock(xCoord, yCoord, zCoord)).getTextureMap();
        computeConnections();
    }

    public void onBlockPlacedBy(EntityLivingBase placer) {
        computeConnections();
    }

    public IIcon[] getIcons() {
        return icons;
    }

}
