package com.github.x3rmination.init;



import com.github.x3rmination.common.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.DamageSource;
import java.util.ArrayList;
import java.util.List;

public class EnchantmentInit {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
    public static final DamageSource TRUE_DAMAGE = (new DamageSource("trueDamage")).setDamageBypassesArmor().setDamageIsAbsolute();




    public static final Enchantment PERUN = new EnchantmentPerun();
    public static final Enchantment ARROW_ARMORY = new EnchantmentArrowArmory();
    public static final Enchantment BOTTOMLESS_QUIVER = new EnchantmentBottomlessQuiver();
    public static final Enchantment CHIPPING = new EnchantmentChipping();
    public static final Enchantment FASTER_THAN_THEIR_SHADOW = new EnchantmentFasterThanTheirShadow();
    public static final Enchantment FLETCHING = new EnchantmentFletching();
    public static final Enchantment FIRST_SHOT = new EnchantmentFirstShot();
    public static final Enchantment JUMP_SPAMMER = new EnchantmentJumpSpammer();
    public static final Enchantment MIXED_COMBAT = new EnchantmentMixedCombat();
    public static final Enchantment PARASITE = new EnchantmentParasite();
    public static final Enchantment PIN_DOWN = new EnchantmentPinDown();
    public static final Enchantment PUSH_COMES_TO_SHOVE = new EnchantmentPushComesToShove();
    public static final Enchantment SPAMMER_AND_PROUD = new EnchantmentSpammerAndProud();
}
