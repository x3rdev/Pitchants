package com.github.x3rmination.core.items.armor;

import com.github.x3rmination.init.ItemInit;
import com.github.x3rmination.pitchants;
import com.github.x3rmination.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor implements IHasModel
{
    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        pitchants.proxy.registerModel(this, 0);
    }
}
