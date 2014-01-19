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

public class BlockGasCreator extends Block implements ITileEntityProvider{
	
	public BlockGasCreator()
	{
		super(Material.field_151576_e);
		this.func_149647_a(GasCraft.tabGasCraft);
		this.func_149663_c("GasCreator");
		this.func_149675_a(true); //set tick randomly
	}
	
	@Override
	public void func_149651_a(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("gascraft:gascreator");
    }
	
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
		return new TileEntityGasCreator();
	}
	
}
