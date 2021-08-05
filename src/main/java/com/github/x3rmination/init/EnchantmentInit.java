package com.github.x3rmination.init;



import com.github.x3rmination.common.enchantments.EnchantmentPerun;
import com.github.x3rmination.common.enchantments.bow.*;
import com.github.x3rmination.common.enchantments.bow.rare.*;
import com.github.x3rmination.common.enchantments.pants.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.DamageSource;
import java.util.ArrayList;
import java.util.List;

public class EnchantmentInit {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
    public static final DamageSource TRUE_DAMAGE = (new DamageSource("trueDamage")).setDamageBypassesArmor().setDamageIsAbsolute();



    /*
    Bow Enchantments
     */
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
    public static final Enchantment SPRINT_DRAIN = new EnchantmentSprintDrain();
    public static final Enchantment SNIPER = new EnchantmentSniper();
    public static final Enchantment WHAT_DOESNT_KILL_YOU = new EnchantmentWhatDoesntKillYou();
    public static final Enchantment WASP = new EnchantmentWasp();
    public static final Enchantment LUCKY_SHOT = new EnchantmentLuckyShot();
    public static final Enchantment MEGA_LONGBOW = new EnchantmentMegaLongbow();
    public static final Enchantment PULL_BOW = new EnchantmentPullbow();
    public static final Enchantment TELE_BOW = new EnchantmentTelebow();
    public static final Enchantment TRUE_SHOT = new EnchantmentTrueShot();
    public static final Enchantment VOLLEY = new EnchantmentVolley();
    /*
    Pants Enchantment
     */
    public static final Enchantment BOOBOO = new EnchantmentBooboo();
    public static final Enchantment CRICKET = new EnchantmentCricket();
    public static final Enchantment CRITICALLY_FUNKY = new EnchantmentCriticallyFunky();
    public static final Enchantment COUNTER_OFFENSIVE = new EnchantmentCounterOffensive();
    public static final Enchantment DANGER_CLOSE = new EnchantmentDangerClose();
}
