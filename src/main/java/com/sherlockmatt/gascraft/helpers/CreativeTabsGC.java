package com.sherlockmatt.gascraft.helpers;

import com.sherlockmatt.gascraft.GasCraft;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabsGC extends CreativeTabs
{
        public CreativeTabsGC(int position, String tabID)
        {
                super(position, tabID);
        }

        @Override
        public ItemStack func_151244_d() 
        {
                return new ItemStack(GameRegistry.findItem(GasCraft.MODID, "GasCreator"), 1, 0);
        }

        @Override
        public Item getTabIconItem() 
        {
                return null;
        }
}