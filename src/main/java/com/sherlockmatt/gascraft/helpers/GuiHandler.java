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
        	System.out.println(id);
<<<<<<< HEAD
        	TileEntity tileEntity = (TileEntity) world.func_147438_o(x, y, z);
=======
        	TileEntity tileEntity = (TileEntity) world.getTileEntity(x, y, z);
>>>>>>> origin/dev2_mappings
            if(tileEntity instanceof TileEntityGasAmplifier){
            	return new ContainerGasAmplifier(player.inventory, (TileEntityGasAmplifier) tileEntity);
            }
            return null;
        }

        //returns an instance of the Gui you made earlier
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        	System.out.println(id);
<<<<<<< HEAD
        	TileEntity tileEntity = (TileEntity) world.func_147438_o(x, y, z);
=======
        	TileEntity tileEntity = (TileEntity) world.getTileEntity(x, y, z);
>>>>>>> origin/dev2_mappings
            if(tileEntity instanceof TileEntityGasAmplifier){
            	return new GuiGasAmplifier(player.inventory, (TileEntityGasAmplifier) tileEntity);
            }
            return null;
        }
}