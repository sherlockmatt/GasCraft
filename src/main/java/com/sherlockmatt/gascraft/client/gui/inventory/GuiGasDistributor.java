package com.sherlockmatt.gascraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import com.sherlockmatt.gascraft.inventory.ContainerGasDistributor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGasDistributor extends GuiContainer
{
    private static final ResourceLocation field_147085_u = new ResourceLocation("gascraft", "textures/gui/gasamplifier1.png");
    private IInventory playerInvent;
    private IInventory chestInvent;

    public GuiGasDistributor(InventoryPlayer par1InventoryPlayer, IInventory par2IInventory)
    {
        super(new ContainerGasDistributor(par1InventoryPlayer, par2IInventory));
        this.playerInvent = par1InventoryPlayer;
        this.chestInvent = par2IInventory;
        //this.something = 133; Apparently unimportant =D

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString(this.chestInvent.hasCustomInventoryName() ? this.chestInvent.getInventoryName() : I18n.format(this.chestInvent.getInventoryName(), new Object[0]), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInvent.hasCustomInventoryName() ? this.playerInvent.getInventoryName() : I18n.format(this.playerInvent.getInventoryName(), new Object[0]), 8, this.width - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(field_147085_u);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}