package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasAmplifier;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGasAmplifier extends Block implements ITileEntityProvider {

	public BlockGasAmplifier()
	{
		super(Material.field_151576_e);
		this.func_149647_a(GasCraft.tabGasCraft);
		this.func_149663_c("GasAmplifier");
	}
	
	@Override
	public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntityGasAmplifier tileEntity = (TileEntityGasAmplifier) world.func_147438_o(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
            return false;
		}
		player.openGui(GasCraft.instance, 0, world, x, y, z);
		return true;
	}
	
	@Override
	public void func_149651_a(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("gascraft:gasamplifier");
    }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityGasAmplifier();
	}

	@Override
	public TileEntity func_149915_a(World var1, int var2) {
		return new TileEntityGasAmplifier();
	}
}
