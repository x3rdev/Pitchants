package com.github.x3rmination.common.items;

import com.github.x3rmination.core.items.tools.ToolSwordBase;
import com.github.x3rmination.init.ItemInit;
import com.github.x3rmination.pitchants;
import com.github.x3rmination.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class TestSword extends ToolSwordBase implements IHasModel {

//    public TestSword(String name, CreativeTabs tab) {
////        setRegistryName("testsword");
////        setUnlocalizedName(pitchants.MODID + ".testsword");
//        setRegistryName(name);
//        setUnlocalizedName(name);
//        setCreativeTab(tab);
//        ItemInit.ITEMS.add(this);
//    }

    public TestSword(String name, ToolMaterial material, CreativeTabs tab) {
        super(name, material, tab);
    }


    @Override
    public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity entity) {
        super.onLeftClickEntity(itemStack, player, entity);
        if (true) {
            entity.setFire(5);
        }
        return false;
    }
    @Override
    public void registerModels()
    {
        pitchants.proxy.registerModel(this, 0);
    }
}
