package com.sherlockmatt.gascraft.tileentity;

import com.sherlockmatt.gascraft.GasCraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityGasCreator extends TileEntity{
	private boolean canWork = false;
	private Block block;
	private World world;

	@Override
	public void func_145845_h() { //Not even sure what this does...
		if (this.field_145850_b != null && !this.field_145850_b.isRemote && this.field_145850_b.getTotalWorldTime() % 20L == 0L)
        {
			this.block = this.func_145838_q();
			this.world = func_145831_w();
			if (this.canWork) {
				this.canWork = checkMultiBlock();
				this.world.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
				System.out.println(checkMultiBlock() ? "Work Ticking." : "Stopped Work");
			} else {
				if (checkMultiBlock()){
					this.world.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
					startWork();
				}
			}
        }
	}
	
	private boolean checkMultiBlock(){
		this.block = this.func_145838_q(); //getBlock()? Not the same as that one down there \/ though.
		this.world = func_145831_w(); //getWorld()?
		if(this.world.getBlock(this.xCoord, this.yCoord, this.zCoord) == this.block)
		{
			if(this.world.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == GameRegistry.findBlock(GasCraft.MODID, "GasDistributor"))
			{
				if(this.world.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.getBlock(this.xCoord - 1, this.yCoord, this.zCoord) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.getBlock(this.xCoord, this.yCoord, this.zCoord + 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.getBlock(this.xCoord, this.yCoord, this.zCoord - 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier"))
				{
					return true;
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
