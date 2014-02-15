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

<<<<<<< HEAD
=======
import java.util.Random;

>>>>>>> origin/dev2_mappings
public class BlockGasCreator extends Block implements ITileEntityProvider{
	
	public BlockGasCreator()
	{
<<<<<<< HEAD
		super(Material.field_151576_e);
		this.func_149647_a(GasCraft.tabGasCraft);
		this.func_149663_c("GasCreator");
		this.func_149675_a(true); //set tick randomly
=======
		super(Material.rock);
		this.setCreativeTab(GasCraft.tabGasCraft);
		this.setBlockName("GasCreator");
		this.setTickRandomly(true); //set tick randomly
>>>>>>> origin/dev2_mappings
	}
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gascreator");
    }
	
<<<<<<< HEAD
	public void func_149726_b(World world, int x, int y, int z)
	{
		if(world.func_147439_a(x, y, z) == this)
		{
			if(world.func_147439_a(x, y + 1, z) == GameRegistry.findBlock(GasCraft.MODID, "GasDistributor"))
			{
				if(world.func_147439_a(x + 1, y, z) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.func_147439_a(x - 1, y, z) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.func_147439_a(x, y, z + 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier") && world.func_147439_a(x, y, z - 1) == GameRegistry.findBlock(GasCraft.MODID, "GasAmplifier"))
				{
					world.func_147465_d(x, y - 1, z, GameRegistry.findBlock(GasCraft.MODID, "MiningGas"), 0, 2);
					((TileEntityGasCreator) world.func_147438_o(x, y, z)).startWork();
				}
				else System.out.println("3: " + world.func_147439_a(x + 1, y, z).func_149739_a());
			}
			else System.out.println("2: " + world.func_147439_a(x, y + 1, z).func_149739_a());
		}
		else System.out.println("1: " + world.func_147439_a(x, y, z).func_149739_a());
	}

	@Override
	public TileEntity func_149915_a(World var1, int var2) {
=======
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
>>>>>>> origin/dev2_mappings
		return new TileEntityGasCreator();
	}
	
}
