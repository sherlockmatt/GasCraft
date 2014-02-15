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
		super(Material.rock);
		this.setCreativeTab(GasCraft.tabGasCraft);
		this.setBlockName("GasAmplifier");
	}
	
	@Override
<<<<<<< HEAD
	public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntityGasAmplifier tileEntity = (TileEntityGasAmplifier) world.func_147438_o(x, y, z);
=======
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntityGasAmplifier tileEntity = (TileEntityGasAmplifier) world.getTileEntity(x, y, z);
>>>>>>> origin/dev2_mappings
		if (tileEntity == null || player.isSneaking()) {
            return false;
		}
		player.openGui(GasCraft.instance, 0, world, x, y, z);
		return true;
	}
	
	@Override
<<<<<<< HEAD
	public void func_149651_a(IIconRegister par1IconRegister)
=======
	public void registerBlockIcons(IIconRegister par1IconRegister)
>>>>>>> origin/dev2_mappings
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gasamplifier");
    }
	
	@Override
<<<<<<< HEAD
	public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityGasAmplifier();
	}

	@Override
	public TileEntity func_149915_a(World var1, int var2) {
		return new TileEntityGasAmplifier();
	}
=======
	public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityGasAmplifier();
	}

	/*
	Don't think this is necessary any more.
	@Override
	public TileEntity func_149915_a(World var1, int var2) {
		return new TileEntityGasAmplifier();
	}*/
>>>>>>> origin/dev2_mappings
}
