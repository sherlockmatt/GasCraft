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
		this.setTickRandomly(true); //set tick randomly
	}
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gascreator");
    }
	
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if(world.getBlock(x, y, z) == this)
		{
			if(world.getBlock(x, y + 1, z) == GameRegistry.findBlock(GasCraft.MODID, "GasDistributor"))
			{
				if(world.getBlock(x + 1, y, z) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.getBlock(x - 1, y, z) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.getBlock(x, y, z + 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.getBlock(x, y, z - 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier"))
				{
					world.setBlock(x, y - 1, z, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
					((TileEntityGasCreator) world.getTileEntity(x, y, z)).startWork();
				}
				else System.out.println("3: " + world.getBlock(x + 1, y, z).getUnlocalizedName());
			}
			else System.out.println("2: " + world.getBlock(x, y + 1, z).getUnlocalizedName());
		}
		else System.out.println("1: " + world.getBlock(x, y, z).getUnlocalizedName());
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityGasCreator();
	}
	
}
