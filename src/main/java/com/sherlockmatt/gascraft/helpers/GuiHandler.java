package com.sherlockmatt.gascraft.helpers;

import com.sherlockmatt.gascraft.client.gui.inventory.GuiGasAmplifier;
import com.sherlockmatt.gascraft.client.gui.inventory.GuiGasDistributor;
import com.sherlockmatt.gascraft.inventory.ContainerGasAmplifier;
import com.sherlockmatt.gascraft.inventory.ContainerGasDistributor;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasAmplifier;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasDistributor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
		
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            switch (id) {
                case 0:
                    if(tileEntity instanceof TileEntityGasAmplifier){
                    	return new ContainerGasAmplifier(player.inventory, (TileEntityGasAmplifier) tileEntity);
                    }
                    break;
                case 1:
                    if(tileEntity instanceof TileEntityGasDistributor){
                        return new ContainerGasDistributor(player.inventory, (TileEntityGasDistributor) tileEntity);
                    }
                    break;
                default:
                    return null;
            }
            return null;
        }

        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        	TileEntity tileEntity = world.getTileEntity(x, y, z);
            switch (id) {
                case 0:
                    if(tileEntity instanceof TileEntityGasAmplifier){
                    	return new GuiGasAmplifier(player.inventory, (TileEntityGasAmplifier) tileEntity);
                    }
                    break;
                case 1:
                    if(tileEntity instanceof TileEntityGasDistributor){
                        return new GuiGasDistributor(player.inventory, (TileEntityGasDistributor) tileEntity);
                    }
                    break;
                default:
                    return null;
            }
            return null;
        }
}