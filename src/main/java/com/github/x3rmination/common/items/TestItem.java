package com.github.x3rmination.common.items;

import com.github.x3rmination.pitchants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TestItem extends Item {

    public TestItem() {
        setRegistryName("testitem");
        setUnlocalizedName(pitchants.MODID + ".testitem");
        setCreativeTab(CreativeTabs.MISC);
    }
}
