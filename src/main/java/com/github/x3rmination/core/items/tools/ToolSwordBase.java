package com.github.x3rmination.core.items.tools;

import com.github.x3rmination.init.ItemInit;
import com.github.x3rmination.pitchants;
import com.github.x3rmination.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ToolSwordBase extends ItemSword implements IHasModel {

    public ToolSwordBase(String name, ToolMaterial material, CreativeTabs tab) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        pitchants.proxy.registerModel(this, 0);
    }
}
