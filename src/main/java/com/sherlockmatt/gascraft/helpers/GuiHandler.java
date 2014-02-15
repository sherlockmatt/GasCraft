package com.sherlockmatt.gascraft.helpers;

import com.sherlockmatt.gascraft.client.gui.inventory.GuiGasAmplifier;
import com.sherlockmatt.gascraft.inventory.ContainerGasAmplifier;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasAmplifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
        //returns an instance of the Container you made earlier
		
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            switch (id) {
                case 0:
                    if(tileEntity instanceof TileEntityGasAmplifier){
                    	return new ContainerGasAmplifier(player.inventory, (TileEntityGasAmplifier) tileEntity);
                    }
                default:
                    return null;
            }
        }

        //returns an instance of the Gui you made earlier
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        	TileEntity tileEntity = world.getTileEntity(x, y, z);
            switch (id) {
                case 0:
                    if(tileEntity instanceof TileEntityGasAmplifier){
                    	return new GuiGasAmplifier(player.inventory, (TileEntityGasAmplifier) tileEntity);
                    }
                default:
                    return null;
            }
        }
}