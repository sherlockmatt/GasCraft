package com.sherlockmatt.gascraft.tileentity;

import com.sherlockmatt.gascraft.GasCraft;

import com.sherlockmatt.gascraft.block.BlockGasCreator;
import com.sherlockmatt.gascraft.util.Coords;
import com.sherlockmatt.gascraft.util.WeightedRandomCoordinate;
import com.sherlockmatt.gascraft.util.WeightedRandomCoordinateItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class TileEntityGasCreator extends TileEntity{
	private boolean canWork = false;
    private int maxWork = 4;
    private boolean finishedAllWork = false;
    ArrayList<WeightedRandomCoordinateItem> blockMap = new ArrayList<WeightedRandomCoordinateItem>();

    @Override
	public void updateEntity() {
        if (worldObj != null && !worldObj.isRemote && worldObj.getTotalWorldTime() % 5L == 0L)
        {
			this.blockType = this.getBlockType();
            if (blockType instanceof BlockGasCreator) {
			    if (canWork) {
    				canWork = checkMultiBlock();
                    if (canWork)
                    {
                        expandGas();
                    }
    				//this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
    				//System.out.println(canWork ? "Work Ticking." : "Stopped Work");
    			} else {
    				if (checkMultiBlock()){
                        //this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
    					startWork();
    				}
                }
			}
        }
	}

    private void expandGas() {
        for (int _ = 0; _<maxWork; _++) {
            WeightedRandomCoordinateItem temp = WeightedRandomCoordinate.getRandomItem(worldObj.rand,blockMap);
            if (temp != null) {
                this.worldObj.setBlock(temp.coords.x, temp.coords.y, temp.coords.z, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
                blockMap.addAll(getExpandableBlocks(temp.coords));
                blockMap.remove(temp);
            }
            /*Handle Mining*/
        }
    }

	private boolean checkMultiBlock(){
		this.blockType = this.getBlockType();
        if (this.blockType instanceof BlockGasCreator) {
		    if(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == this.blockType)
		    {
    			if(this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == GameRegistry.findBlock(GasCraft.MODID, "GasDistributor"))
    			{
    				if(this.worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord + 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord - 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier"))
    				{
    					return true;
    				}
    			}
    		}
        }
		return false;
	}

    private double evaluateBlock(int par1, int par2, int par3) {
        int dx = Math.abs(this.xCoord-par1);
        int dy = this.yCoord-par2-3;
        int dz = Math.abs(this.xCoord-par3);
        return Math.sqrt(dx*dx + dz*dz + (dy*dy)*1.1);
    }

    private Queue<WeightedRandomCoordinateItem> getExpandableblocks(int xC, int yC, int zC) {
        Queue<WeightedRandomCoordinateItem> airMap = new LinkedList<WeightedRandomCoordinateItem>();
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            Block tempBlock = worldObj.getBlock(xC+dir.offsetX,yC+dir.offsetY,zC+dir.offsetZ);
            if (tempBlock != GameRegistry.findBlock(GasCraft.MODID, "MiningGas")) {
                if (tempBlock == Blocks.air) {
                    WeightedRandomCoordinateItem tempWRCI = new WeightedRandomCoordinateItem(evaluateBlock(xC+dir.offsetX,yC+dir.offsetY,zC+dir.offsetZ), new Coords(xC+dir.offsetX,yC+dir.offsetY,zC+dir.offsetZ));
                    if (!airMap.contains(tempWRCI) && !blockMap.contains(tempWRCI)) {
                        airMap.add(tempWRCI);
                    }
                }
            }
        }
        return airMap;
    }

    private Queue<WeightedRandomCoordinateItem> getExpandableBlocks(Coords c) {
        return getExpandableblocks(c.x,c.y,c.z);
    }

    private Queue<WeightedRandomCoordinateItem> getExternalExpandableBlocks(int xC, int yC, int zC) {
        LinkedList<Coords> gasMap = new LinkedList<Coords>();
        LinkedList<Coords> gasMapF = new LinkedList<Coords>();
        LinkedList<WeightedRandomCoordinateItem> airMap = new LinkedList<WeightedRandomCoordinateItem>();

        if (worldObj.getBlock(xC, yC, zC) == GameRegistry.findBlock(GasCraft.MODID, "MiningGas")) {
            gasMap.add(new Coords(xC,yC,zC));
            while (!gasMap.isEmpty()) {
                Coords cC = gasMap.pop();
                gasMapF.add(cC);
                for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                    Block tempBlock = worldObj.getBlock(cC.x+dir.offsetX,cC.y+dir.offsetY,cC.z+dir.offsetZ);
                    Coords tmpC = new Coords(cC.x+dir.offsetX,cC.y+dir.offsetY,cC.z+dir.offsetZ);
                    if (tempBlock == Blocks.air) {
                        WeightedRandomCoordinateItem tempWRCI = new WeightedRandomCoordinateItem(evaluateBlock(cC.x+dir.offsetX,cC.y+dir.offsetY,cC.z+dir.offsetZ), tmpC);
                        if (!airMap.contains(tempWRCI) && !blockMap.contains(tempWRCI)) {
                            airMap.add(tempWRCI); //Test
                        }
                    } else if (tempBlock == GameRegistry.findBlock(GasCraft.MODID, "MiningGas") && !gasMapF.contains(tmpC) && !gasMap.contains(tmpC)) {
                        gasMap.add(tmpC);
                    }
                }
            }
        }
        if (airMap.isEmpty() && blockMap.isEmpty()) {
            this.finishedAllWork = true;
            this.canWork = false;
        }
        return airMap;
    }

	public void startWork(){
		if (!this.canWork && !this.finishedAllWork)
		{
            if (blockMap.isEmpty()) {
                if (worldObj.getBlock(xCoord,yCoord-1,zCoord) == GameRegistry.findBlock(GasCraft.MODID, "MiningGas"))
                {
                    blockMap.addAll(getExternalExpandableBlocks(xCoord,yCoord-1,zCoord));
                } else {
                    blockMap.add(new WeightedRandomCoordinateItem(evaluateBlock(xCoord, yCoord - 1, zCoord), new Coords(xCoord, yCoord - 1, zCoord)));
                }
            }
            this.canWork = true;
		}
	}



    /*@Override
    public void readFromNBT(NBTTagCompound nbtdata) //Uh, what? Pls fix.
    {
        super.readFromNBT(nbtdata);
        NBTTagList nbttaglist = nbtdata.getTagList("Items", 10);
        //this.items = new ItemStack[this.getSizeInventory()];

        if (nbtdata.hasKey("CustomName", 8))
        {
            this.containerName = nbtdata.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.items.length)
            {
                //this.items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtdata) //sjdfjsnfjsnjn. I'M PANICKING!
    {
        super.writeToNBT(nbtdata);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i)
        {
            if (this.items[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.items[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtdata.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            nbtdata.setString("CustomName", this.containerName);
        }
    }*/
}
