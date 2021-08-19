package com.github.x3rmination.common.enchantments.bow.rare;

import com.github.x3rmination.core.helpers.FindAmmo;
import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.item.ItemBow.getArrowVelocity;

@Mod.EventBusSubscriber(modid= pitchants.MODID)
public class EnchantmentVolley extends Enchantment {

    public EnchantmentVolley() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("volley");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":volley"));
        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }


    @SubscribeEvent
    public void onArrowLoose(ArrowLooseEvent event) {
        FindAmmo ammoFinder = new FindAmmo();
        ItemStack itemArrow;
        EntityArrow ammoArrow;
        int i = event.getCharge();
        float f = getArrowVelocity(i);
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.VOLLEY, event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
        if (level > 0 && !event.getWorld().isRemote) {
            if(ammoFinder.findAmmo(event.getEntityPlayer()).getItem() instanceof ItemArrow || event.getEntityPlayer().isCreative()) {
                itemArrow = ammoFinder.findAmmo(event.getEntityPlayer());
                int iterable = level + 1;
                while (iterable > 0) {
                    iterable -= 1;
                    ItemStack itemArrow1 = itemArrow.copy();
                    if (event.getEntityPlayer().isCreative()) {
                        ammoArrow = (new ItemArrow()).createArrow(event.getWorld(), new ItemStack(Items.ARROW), event.getEntityLiving());
                    } else {
                        ammoArrow = ((ItemArrow) itemArrow1.getItem()).createArrow(event.getWorld(), ammoFinder.findAmmo(event.getEntityPlayer()), event.getEntityLiving());
                    }
                    ammoArrow.setEnchantmentEffectsFromEntity(event.getEntityLiving(), 1);
                    ammoArrow.shoot(event.getEntityLiving(), event.getEntityLiving().rotationPitch, event.getEntityLiving().rotationYaw, 0, f * 3.0F, 6);
                    event.getWorld().spawnEntity(ammoArrow);
                }
            }
        }
    }
}
