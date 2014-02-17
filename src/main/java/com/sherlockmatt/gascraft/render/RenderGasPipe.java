package com.sherlockmatt.gascraft.render;

import com.sherlockmatt.gascraft.GasCraft;
import com.sherlockmatt.gascraft.block.BlockPipeGasPipe;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasPipe;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderGasPipe implements ISimpleBlockRenderingHandler{

    public void renderPipe(RenderBlocks renderBlocks, IBlockAccess iBlockAccess, BlockPipeGasPipe block, TileEntityGasPipe tile, int x, int y, int z) {

        IIcon[] icons = tile.getIcons();

        if (icons == null) {
            return;
        }

        int connectionMask = tile.getConnectionMask();
        float[] dimensions = new float[6];

        if (connectionMask != 0x3f) {
            resetToCenterDimensions(dimensions);
            tile.currentTexture = icons[ForgeDirection.UNKNOWN.ordinal()];
            renderTwoWayBlock(renderBlocks, block, x, y, z, dimensions, connectionMask ^ 0x3f);
        }

        for (int dir = 0; dir < 6; dir++) {
            int mask = 1 << dir;

            if ((connectionMask & mask) == 0) {
                continue; // no connection towards dir
            }

            // center piece offsets
            resetToCenterDimensions(dimensions);

            // extend block towards dir as it's connected to there
            dimensions[dir / 2] = dir % 2 == 0 ? 0 : 0.6875f;
            dimensions[dir / 2 + 3] = dir % 2 == 0 ? 0.3125f : 1;

            // the mask points to all faces perpendicular to dir, i.e. dirs 0+1 -> mask 111100, 1+2 -> 110011, 3+5 -> 001111
            int renderMask = (3 << (dir / 2 * 2)) ^ 0x3f;

            // render sub block
            tile.currentTexture = icons[dir];

            renderTwoWayBlock(renderBlocks, block, x, y, z, dimensions, renderMask);
        }

    }

    private void renderTwoWayBlock(RenderBlocks renderblocks, BlockPipeGasPipe block, int x, int y, int z, float[] dimensions, int mask) {
        assert mask != 0;

        block.setRenderMask(mask);
        renderblocks.setRenderBounds(dimensions[2], dimensions[0], dimensions[1], dimensions[5], dimensions[3], dimensions[4]);
        renderblocks.renderStandardBlock(block, x, y, z);
        block.setRenderMask((mask & 0x15) << 1 | (mask & 0x2a) >> 1); // pairwise swapped mask
        renderblocks.setRenderBounds(dimensions[5], dimensions[3], dimensions[4], dimensions[2], dimensions[0], dimensions[1]);
        renderblocks.renderStandardBlock(block, x, y, z);
    }

    private void resetToCenterDimensions(float[] dimensions) {
        for (int i = 0; i < 3; i++) {
            dimensions[i] = 0.3125f;
        }

        for (int i = 3; i < 6; i++) {
            dimensions[i] = 0.6875f;
        }
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        //Uhhhh...
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess iBlockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        TileEntity tile = iBlockAccess.getTileEntity(x, y, z);

        if (tile instanceof TileEntityGasPipe) {
            TileEntityGasPipe pipeTile = (TileEntityGasPipe) tile;
            renderPipe(renderer, iBlockAccess, (BlockPipeGasPipe) block, pipeTile, x, y, z);
        }

        return true;
    }

    @Override
    public int getRenderId() {
        return GasCraft.pipeModel;
    }

    @Override
    public boolean shouldRender3DInInventory(int _) {
        return false;
    }

}
