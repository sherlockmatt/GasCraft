package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockGasDistributor extends Block {

	public BlockGasDistributor()
	{
		super(Material.rock);
		this.setCreativeTab(GasCraft.tabGasCraft);
		this.setBlockName("GasDistributor");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gasdistributor");
    }
	
}
