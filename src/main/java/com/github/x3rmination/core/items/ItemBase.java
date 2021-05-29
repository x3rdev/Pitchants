package com.github.x3rmination.core.items;

import com.github.x3rmination.init.ItemInit;
import com.github.x3rmination.pitchants;
import com.github.x3rmination.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name, CreativeTabs tab)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        pitchants.proxy.registerModel(this, 0);
    }
}
