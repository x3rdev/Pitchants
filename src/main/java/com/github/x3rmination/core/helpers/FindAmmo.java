package com.github.x3rmination.core.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;

public class FindAmmo {

    public boolean isArrow(ItemStack stack) {
        return stack.getItem() instanceof ItemArrow;
    }

    public ItemStack findAmmo(EntityPlayer player) {

        ItemStack offHand = player.getHeldItemOffhand();
        ItemStack mainHand = player.getHeldItemMainhand();

        if (isArrow(offHand)) {
            return offHand;
        } else if (isArrow(mainHand)) {
            return mainHand;
        }
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (isArrow(stack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
