package com.sherlockmatt.gascraft;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import net.minecraft.creativetab.CreativeTabs;

import com.sherlockmatt.gascraft.block.*;
import com.sherlockmatt.gascraft.helpers.CraftingRecipes;
import com.sherlockmatt.gascraft.helpers.CreativeTabsGC;
import com.sherlockmatt.gascraft.helpers.GuiHandler;
import com.sherlockmatt.gascraft.render.RenderGasPipe;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasAmplifier;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasCreator;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasDistributor;
import com.sherlockmatt.gascraft.tileentity.TileEntityGasPipe;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

@Mod(modid = GasCraft.MODID, version = GasCraft.VERSION, name = GasCraft.NAME)
public class GasCraft
{
	@Instance("gascraft")
	public static GasCraft instance;
    public static final String MODID = "gascraft";
    public static final String VERSION = "1.0";
    public static final String NAME = "Gas Craft";
    
    public static CreativeTabs tabGasCraft;

    public static int pipeModel = -1;
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	tabGasCraft = new CreativeTabsGC(CreativeTabs.getNextID(), "tabGasCraft");
    	
    	GameRegistry.registerBlock(new BlockGasCreator(), "GasCreator");
    	GameRegistry.registerBlock(new BlockGasDistributor(), "GasDistributor");
    	GameRegistry.registerBlock(new BlockGasAmplifier(), "GasAmplifier");
    	GameRegistry.registerBlock(new BlockMiningGas(), "MiningGas");
        GameRegistry.registerBlock(BlockPipeGasPipe.class, "GasPipe");

    	GameRegistry.registerTileEntity(TileEntityGasAmplifier.class, "GasAmplifier");
        GameRegistry.registerTileEntity(TileEntityGasDistributor.class, "GasDistributor");
    	GameRegistry.registerTileEntity(TileEntityGasCreator.class, "GasCreator");
    	GameRegistry.registerTileEntity(TileEntityGasPipe.class, "GasPipe");
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	
    	CraftingRecipes.init();
    	
		System.out.println("GasCraft version " + GasCraft.VERSION + " has been initialised correctly.");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GasCraft.pipeModel = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderGasPipe());
    }
    
}
