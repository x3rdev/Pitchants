package com.github.x3rmination.core.helpers;

import com.github.x3rmination.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class CheckEnchantments {

    public boolean isNormalEnchantment(ItemStack targetStack) {
        Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(targetStack);
        int c = EnchantmentInit.ENCHANTMENTS.size();
        while(c > 0) {
            c-=1;
            if(enchantmentMap.containsKey(EnchantmentInit.ENCHANTMENTS.get(c))) {
                break;
            }
        }
        return enchantmentMap.containsKey(EnchantmentInit.ENCHANTMENTS.get(c));
    }
}
