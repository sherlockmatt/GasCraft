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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntityGasAmplifier tileEntity = (TileEntityGasAmplifier) world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) return false;
		player.openGui(GasCraft.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gasamplifier");
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityGasAmplifier();
	}
}