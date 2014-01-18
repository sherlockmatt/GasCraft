package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockGasCreator extends Block {
	
	public BlockGasCreator()
	{
		super(Material.field_151576_e);
		this.func_149647_a(GasCraft.tabGasCraft);
		this.func_149663_c("GasCreator");
	}
	
	@Override
	public void func_149651_a(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("gascraft:gascreator");
    }
	
}
