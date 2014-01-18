package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGasDistributor extends Block {

	public BlockGasDistributor()
	{
		super(Material.field_151576_e);
		this.func_149647_a(GasCraft.tabGasCraft);
		this.func_149663_c("GasDistributor");
	}
	
}
