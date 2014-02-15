package com.sherlockmatt.gascraft.tileentity;

import com.sherlockmatt.gascraft.GasCraft;

import com.sherlockmatt.gascraft.block.BlockGasCreator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGasCreator extends TileEntity{
	private boolean canWork = false;

	@Override
	public void updateEntity() { //Not even sure what this does...
        if (this.worldObj != null && !this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 20L == 0L)
        {
			this.blockType = this.getBlockType();
            if (this.blockType instanceof BlockGasCreator) {
			    if (this.canWork) {
    				this.canWork = checkMultiBlock();
    				this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
    				System.out.println(checkMultiBlock() ? "Work Ticking." : "Stopped Work");
    			} else {
    				if (checkMultiBlock()){
                        this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
    					startWork();
    				}
                }
			}
        }
	}
	
	private boolean checkMultiBlock(){
		this.blockType = this.getBlockType(); //getBlock()? Not the same as that one down there \/ though.
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
	public void startWork(){
		if (!this.canWork)
		{
			this.canWork = true;
			//set hashmap
		}
	}
}
