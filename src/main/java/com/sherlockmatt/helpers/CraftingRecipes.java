package com.sherlockmatt.helpers;

import com.sherlockmatt.gascraft.GasCraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {

	public static void init()
	{
		
		addCraftingRecipes();
		addSmeltingRecipes();
		
	}
	
	private static void addCraftingRecipes()
	{
		
		GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasCreator"), 1, 0), new Object[] { "IWI", "WSW", "IWI", 'I', Items.iron_ingot, 'W', Blocks.wool, 'S', Items.fermented_spider_eye });
		GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasAmplifier"), 1, 0), new Object[] { "ISI", "SGS", "ISI", 'I', Items.iron_ingot, 'S', Items.string, 'G', Blocks.glass });
		GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasDistributor"), 1, 0), new Object[] { "LGL", "GCG", "LGL", 'L', Items.leather, 'G', Blocks.glass, 'C', Blocks.chest });
		//GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasCreator"), 1, 0), new Object[] { });
		//GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasCreator"), 1, 0), new Object[] { });
		//GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasCreator"), 1, 0), new Object[] { });
		//GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasCreator"), 1, 0), new Object[] { });
		
	}
	
	private static void addSmeltingRecipes()
	{
		
		
		
	}
	
}
