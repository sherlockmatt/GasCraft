package com.sherlockmatt.gascraft;

import net.minecraft.creativetab.CreativeTabs;

import com.sherlockmatt.gascraft.block.*;
import com.sherlockmatt.helpers.CreativeTabsGC;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = GasCraft.MODID, version = GasCraft.VERSION, name = GasCraft.NAME)
public class GasCraft
{
    public static final String MODID = "gascraft";
    public static final String VERSION = "1.0";
    public static final String NAME = "Gas Craft";
    
    public static CreativeTabs tabGasCraft;
    
    @EventHandler
    public void preInit(FMLInitializationEvent event)
    {
    	tabGasCraft = new CreativeTabsGC(CreativeTabs.getNextID(), "tabGasCraft");
    	
    	GameRegistry.registerBlock(new BlockGasCreator(), "GasCreator");
    	GameRegistry.registerBlock(new BlockGasDistributor(), "GasDistributor");
    	GameRegistry.registerBlock(new BlockGasAmplifier(), "GasAmplifier");
		System.out.println("GasCraft version " + GasCraft.VERSION + " has been initialised correctly.");
    }
    
}
