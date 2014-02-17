package com.sherlockmatt.gascraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.sherlockmatt.gascraft.*;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasCreator;

import cpw.mods.fml.common.registry.GameRegistry;

import java.util.Random;

public class BlockGasCreator extends Block implements ITileEntityProvider{
	
	public BlockGasCreator()
	{
		super(Material.rock);
		this.setCreativeTab(GasCraft.tabGasCraft);
		this.setBlockName("GasCreator");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gascreator");
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityGasCreator();
	}
	
}
