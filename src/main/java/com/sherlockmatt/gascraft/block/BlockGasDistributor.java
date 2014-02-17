package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasDistributor;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGasDistributor extends Block implements ITileEntityProvider {

    public BlockGasDistributor()
    {
        super(Material.rock);
        this.setCreativeTab(GasCraft.tabGasCraft);
        this.setBlockName("GasDistributor");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
        TileEntityGasDistributor tileEntity = (TileEntityGasDistributor) world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        player.openGui(GasCraft.instance, 1, world, x, y, z);
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gasdistributor");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityGasDistributor();
    }
}
