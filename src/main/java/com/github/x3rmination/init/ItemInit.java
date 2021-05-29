package com.github.x3rmination.init;

import com.github.x3rmination.pitchants;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

public class ItemInit {

    public static final List<Item> ITEMS = new ArrayList<Item>();


    //Material
    public static final Item.ToolMaterial TOOL_TEST = EnumHelper.addToolMaterial("tool_test", 1, 1, 1, 1, 1);
    public static final ItemArmor.ArmorMaterial ARMOR_TEST = EnumHelper.addArmorMaterial("custom_armor", pitchants.MODID + ":custom_armor", 1, new int[]{3, 6, 8, 3}, 1, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1);


}

