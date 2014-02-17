package com.sherlockmatt.gascraft.block;

import com.sherlockmatt.gascraft.GasCraft;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasPipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class BlockPipeGasPipe extends Block implements ITileEntityProvider{

    /*static class RaytraceResult {

        RaytraceResult(MovingObjectPosition movingObjectPosition, AxisAlignedBB boundingBox, ForgeDirection side) {
            this.movingObjectPosition = movingObjectPosition;
            this.boundingBox = boundingBox;
            this.sideHit = side;
        }
        public final MovingObjectPosition movingObjectPosition;
        public final AxisAlignedBB boundingBox;
        public final ForgeDirection sideHit;

        @Override
        public String toString() {
            return String.format("RayTraceResult: %s", boundingBox == null ? "null" : boundingBox.toString());
        }
    }*/

    //private String[] canConnectTo = {"tile.GasDistributor", "tile.MiningGas", "tile.GasPipe"};
    private IIcon[] textureMap = new IIcon[7];

    private int renderMask = 0;
    private static final ForgeDirection[] DIR_VALUES = ForgeDirection.VALID_DIRECTIONS;

    public BlockPipeGasPipe() {
        super(Material.glass);
        this.setCreativeTab(GasCraft.tabGasCraft);
        this.setBlockName("GasPipe");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return GasCraft.pipeModel;
    }

    public void setRenderMask(int mask) {
        renderMask = mask;
    }

    public int getRenderMask() {
        return renderMask;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntity tile = blockAccess.getTileEntity(x, y, z);
        if (tile instanceof TileEntityGasPipe) {
            TileEntityGasPipe pipe = (TileEntityGasPipe) tile;
            return !pipe.isConnected(side);
        }
        else {
            return true;
        }
    }

    @Override
    public boolean isSideSolid(IBlockAccess blockAccess, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisalignedBB, List arrayList, Entity entity) {
        setBlockBounds(0.3125f, 0.3125f, 0.3125f, 0.6875f, 0.6875f, 0.6875f);
        super.addCollisionBoxesToList(world, x, y, z, axisalignedBB, arrayList, entity);

        TileEntity tempTile = world.getTileEntity(x, y, z);
        if(tempTile instanceof TileEntityGasPipe) {
            TileEntityGasPipe tile = (TileEntityGasPipe) tempTile;

            if(tile.isConnected(ForgeDirection.WEST)) {
                setBlockBounds(0.0f, 0.3125f, 0.3125f, 0.6875f, 0.6875f, 0.6875f);
                super.addCollisionBoxesToList(world, x, y, z, axisalignedBB, arrayList, entity);
            }

            if(tile.isConnected(ForgeDirection.EAST)) {
                setBlockBounds(0.3125f, 0.3125f, 0.3125f, 1.0f, 0.6875f, 0.6875f);
                super.addCollisionBoxesToList(world, x, y, z, axisalignedBB, arrayList, entity);
            }

            if(tile.isConnected(ForgeDirection.DOWN)) {
                setBlockBounds(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.6875f, 0.6875f);
                super.addCollisionBoxesToList(world, x, y, z, axisalignedBB, arrayList, entity);
            }

            if(tile.isConnected(ForgeDirection.UP)) {
                setBlockBounds(0.3125f, 0.3125f, 0.3125f, 0.6875f, 1.0f, 0.6875f);
                super.addCollisionBoxesToList(world, x, y, z, axisalignedBB, arrayList, entity);
            }

            if(tile.isConnected(ForgeDirection.NORTH)) {
                setBlockBounds(0.3125f, 0.3125f, 0.0f, 0.6875f, 0.6875f, 0.6875f);
                super.addCollisionBoxesToList(world, x, y, z, axisalignedBB, arrayList, entity);
            }

            if(tile.isConnected(ForgeDirection.SOUTH)) {
                setBlockBounds(0.3125f, 0.3125f, 0.3125f, 0.6875f, 0.6875f, 1.0f);
                super.addCollisionBoxesToList(world, x, y, z, axisalignedBB, arrayList, entity);
            }
        }
        setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        float scale = 0.08F;
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityGasPipe) {
            TileEntityGasPipe pipe = (TileEntityGasPipe) tile;
            int mask = pipe.getConnectionMask();
            double[] bb = new double[6];
            for (ForgeDirection side : DIR_VALUES) {
                bb[side.ordinal()] = ((mask & (1 << side.ordinal())) != 0) ? 0.3125 - scale : 0.0;
                bb[side.ordinal()] = side.ordinal() >= 3 ? 1 - bb[side.ordinal()] : bb[side.ordinal()];
            }
            return AxisAlignedBB.getAABBPool().getAABB(bb[0], bb[1], bb[2], bb[3], bb[4], bb[5]);
        }
        return null;
    }
    
    /*@SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        RaytraceResult rayTraceResult = doRayTrace(world, x, y, z, Minecraft.getMinecraft().thePlayer);

        if (rayTraceResult != null && rayTraceResult.boundingBox != null) {
            AxisAlignedBB box = rayTraceResult.boundingBox;
            float scale = 0.08F;
            box = box.expand(scale, scale, scale);
            return box.getOffsetBoundingBox(x, y, z);
        }
        return super.getSelectedBoundingBoxFromPool(world, x, y, z).expand(-0.85F, -0.85F, -0.85F);
    }

    private RaytraceResult doRayTrace(World world, int x, int y, int z, EntityPlayer player) {
        double reachDistance = 5;

        if (player instanceof EntityPlayerMP) {
            reachDistance = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
        }

        double eyeHeight = world.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight();
        Vec3 lookVec = player.getLookVec();
        Vec3 origin = world.getWorldVec3Pool().getVecFromPool(player.posX, player.posY + eyeHeight, player.posZ);
        Vec3 direction = origin.addVector(lookVec.xCoord * reachDistance, lookVec.yCoord * reachDistance, lookVec.zCoord * reachDistance);

        return doRayTrace(world, x, y, z, origin, direction);
    }

    private RaytraceResult doRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 direction) {
        TileEntity pipeTileEntity = world.getTileEntity(x, y, z);

        TileEntityGasPipe tileG = null;
        if (pipeTileEntity instanceof TileEntityGasPipe)
            tileG = (TileEntityGasPipe) pipeTileEntity;

        if (tileG == null)
            return null;

        //Pipe pipe = tileG.pipe;

        //if (!isValid(pipe))
        //    return null;

        MovingObjectPosition[] hits = new MovingObjectPosition[25];
        AxisAlignedBB[] boxes = new AxisAlignedBB[25];
        ForgeDirection[] sideHit = new ForgeDirection[25];
        Arrays.fill(sideHit, ForgeDirection.UNKNOWN);

        // pipe

        for (ForgeDirection side : DIR_VALUES) {
            if (side == ForgeDirection.UNKNOWN || tileG.isConnected(side)) {
                AxisAlignedBB bb = getPipeBoundingBox(side);
                setBlockBounds(bb);
                boxes[side.ordinal()] = bb;
                hits[side.ordinal()] = super.collisionRayTrace(world, x, y, z, origin, direction);
                sideHit[side.ordinal()] = side;
            }
        }

        // get closest hit

        double minLengthSquared = Double.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int i = 0; i < hits.length; i++) {
            MovingObjectPosition hit = hits[i];
            if (hit == null)
                continue;

            double lengthSquared = hit.hitVec.squareDistanceTo(origin);

            if (lengthSquared < minLengthSquared) {
                minLengthSquared = lengthSquared;
                minIndex = i;
            }
        }

        // reset bounds

        setBlockBounds(0, 0, 0, 1, 1, 1);

        if (minIndex == -1) {
            return null;
        }

        return new RaytraceResult(hits[minIndex], boxes[minIndex], sideHit[minIndex]);
    }

    private void setBlockBounds(AxisAlignedBB bb) {
        setBlockBounds((float) bb.minX, (float) bb.minY, (float) bb.minZ, (float) bb.maxX, (float) bb.maxY, (float) bb.maxZ);
    }

    private AxisAlignedBB getPipeBoundingBox(ForgeDirection side) {
        float min = 0.3125f;
        float max = 0.6875f;

        if (side == ForgeDirection.UNKNOWN) {
            return AxisAlignedBB.getAABBPool().getAABB(min, min, min, max, max, max);
        }

        float[][] bounds = new float[3][2];
        // X START - END
        bounds[0][0] = min;
        bounds[0][1] = max;
        // Y START - END
        bounds[1][0] = 0;
        bounds[1][1] = min;
        // Z START - END
        bounds[2][0] = min;
        bounds[2][1] = max;

        MatrixTransformations.transform(bounds, side);
        return AxisAlignedBB.getAABBPool().getAABB(bounds[0][0], bounds[1][0], bounds[2][0], bounds[0][1], bounds[1][1], bounds[2][1]);
    }*/

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, par6);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        if (world.isRemote) {
            return null;
        }

        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

        list.add(new ItemStack(Item.getItemFromBlock(this), 1, 0));

        //This method is only here in case we want it to drop extra items later, i.e. the contents of the pipe.
        return list;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float par6, float par7, float par8, int meta) {
        super.onBlockPlaced(world, x, y, z, side, par6, par7, par8, meta);
        TileEntity tile = world.getTileEntity(x, y, z);
        if (!(tile instanceof TileEntityGasPipe) || tile.isInvalid()) {
            return meta;
        }
        TileEntityGasPipe pipe = (TileEntityGasPipe) tile;
        pipe.onBlockPlaced();
        return meta;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, placer, stack);
        TileEntity tile = world.getTileEntity(x, y, z);
        if (!(tile instanceof TileEntityGasPipe) || tile.isInvalid()) {
            return;
        }
        TileEntityGasPipe pipe = (TileEntityGasPipe) tile;
        pipe.onBlockPlacedBy(placer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (ForgeDirection dir : DIR_VALUES) {
            textureMap[dir.ordinal()] = par1IconRegister.registerIcon("gascraft:gaspipe" + dir.ordinal());
        }
    }

    public IIcon[] getTextureMap() {
        return textureMap;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityGasPipe();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntity tile = blockAccess.getTileEntity(x, y, z);
        if (!(tile instanceof TileEntityGasPipe)) {
            return null;
        }
        if (((TileEntityGasPipe) tile).textureArray != null) {
            return ((TileEntityGasPipe) tile).textureArray[side];
        }
        return ((TileEntityGasPipe) tile).currentTexture;
    }

}
