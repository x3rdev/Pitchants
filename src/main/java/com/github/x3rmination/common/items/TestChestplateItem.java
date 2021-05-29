package com.github.x3rmination.common.items;

import com.github.x3rmination.init.ItemInit;
import com.github.x3rmination.pitchants;
import com.github.x3rmination.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class TestChestplateItem extends ItemArmor implements IHasModel {
    public TestChestplateItem() {
        super(ItemInit.ARMOR_TEST,1, EntityEquipmentSlot.CHEST);
        setRegistryName("testchestplateitem");
        setUnlocalizedName(pitchants.MODID + ".testchestplateitem");
        setCreativeTab(CreativeTabs.MISC);

    }

    @Override
    public void registerModels()
    {
        pitchants.proxy.registerModel(this, 0);
    }
}
