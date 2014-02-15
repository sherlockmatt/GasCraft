package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockMiningGas extends BlockBreakable {
	public BlockMiningGas()
	{
		super("MiningGas",Material.ice,false);  //FIND OUT WHAT MATERIAL THIS WAS MEANT TO BE!
		this.setCreativeTab(GasCraft.tabGasCraft);
		this.setBlockName("MiningGas");
	}

    @Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        entity.setInWeb();
        entity.setFire(1);
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube() //Alpha block
    {
        return false;
    }
	
    @Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:mininggas");
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }  
}
