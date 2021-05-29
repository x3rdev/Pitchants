package com.github.x3rmination.core.items.armor;

import com.github.x3rmination.init.ItemInit;
import com.github.x3rmination.pitchants;
import com.github.x3rmination.rendering.models.items.ModelCustomArmor;
import com.github.x3rmination.util.interfaces.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorModel extends ItemArmor implements IHasModel
{
    public ArmorModel(String name, CreativeTabs tab, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, 1, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setMaxStackSize(1);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        pitchants.proxy.registerModel(this, 0);
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        if(itemStack != ItemStack.EMPTY)
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
                ModelCustomArmor model = new ModelCustomArmor();

                model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;

                model.isChild = _default.isChild;
                model.isRiding = _default.isRiding;
                model.isSneak = _default.isSneak;
                model.rightArmPose = _default.rightArmPose;
                model.leftArmPose = _default.leftArmPose;

                return model;
            }
        }

        return null;
    }

}
