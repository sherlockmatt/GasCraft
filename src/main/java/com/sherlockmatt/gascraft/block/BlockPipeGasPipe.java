package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasPipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Arrays;

public class BlockPipeGasPipe extends Block implements ITileEntityProvider{

    @SideOnly(Side.CLIENT)
    private IIcon altTexture;

    private String[] canConnectTo = {"tile.GasDistributor", "tile.MiningGas", "tile.GasPipe"};
    private IIcon[] textureMap = {this.blockIcon, this.blockIcon, this.blockIcon, this.blockIcon, this.blockIcon, this.blockIcon};

    public BlockPipeGasPipe() {
        super(Material.rock);
        this.setCreativeTab(GasCraft.tabGasCraft);
        this.setBlockName("GasPipe");
        //this.setBlockBounds(0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("gascraft:gaspipe");
        this.altTexture = par1IconRegister.registerIcon("gascraft:gaspipe1");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityGasPipe();
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return textureMap[side];
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if(Arrays.binarySearch(canConnectTo, world.getBlock(x, y - 1, z).getUnlocalizedName()) > 0) { textureMap[0] = this.altTexture; } else { textureMap[0] = this.blockIcon; }
        if(Arrays.binarySearch(canConnectTo, world.getBlock(x, y + 1, z).getUnlocalizedName()) > 0) { textureMap[1] = this.altTexture; } else { textureMap[1] = this.blockIcon; }
        if(Arrays.binarySearch(canConnectTo, world.getBlock(x - 1, y, z).getUnlocalizedName()) > 0) { textureMap[2] = this.altTexture; } else { textureMap[2] = this.blockIcon; }
        if(Arrays.binarySearch(canConnectTo, world.getBlock(x + 1, y, z).getUnlocalizedName()) > 0) { textureMap[3] = this.altTexture; } else { textureMap[3] = this.blockIcon; }
        if(Arrays.binarySearch(canConnectTo, world.getBlock(x, y, z - 1).getUnlocalizedName()) > 0) { textureMap[4] = this.altTexture; } else { textureMap[4] = this.blockIcon; }
        if(Arrays.binarySearch(canConnectTo, world.getBlock(x, y, z + 1).getUnlocalizedName()) > 0) { textureMap[5] = this.altTexture; } else { textureMap[5] = this.blockIcon; }
    }

}
