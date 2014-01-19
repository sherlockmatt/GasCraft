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
		super("MiningGas",Material.field_151581_o,false);
		this.func_149647_a(GasCraft.tabGasCraft);
		this.func_149663_c("MiningGas");
	}
	
	public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
    {
        p_149670_5_.setInWeb();
        p_149670_5_.setFire(1);
    }
	
	@SideOnly(Side.CLIENT)
    public int func_149701_w() //Alpha block
    {
        return 1;
    }
	
    @Override
	public void func_149651_a(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("gascraft:mininggas");
    }
    
    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }  
}
