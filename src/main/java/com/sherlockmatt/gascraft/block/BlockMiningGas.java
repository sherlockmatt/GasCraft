package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMiningGas extends BlockBreakable {
	public BlockMiningGas()
	{
		super("MiningGas",Material.vine,false);  //FIND OUT WHAT MATERIAL THIS WAS MEANT TO BE!
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
    public int getRenderBlockPass() //Alpha block
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return super.shouldSideBeRendered(var1, var2, var3, var4, 1 - var5);
    }

    @Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:mininggas");
    }

    public int quantityDropped(Random _)
    {
        return 0;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }

    public int getMobilityFlag()
    {
        return 1;
    }
}
