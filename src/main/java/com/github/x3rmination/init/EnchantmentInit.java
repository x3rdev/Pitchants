package com.github.x3rmination.init;



import com.github.x3rmination.common.enchantments.EnchantmentPerun;
import com.github.x3rmination.common.enchantments.bow.*;
import com.github.x3rmination.common.enchantments.bow.rare.*;
import com.github.x3rmination.common.enchantments.pants.*;
import com.github.x3rmination.common.enchantments.pants.rare.*;
import com.github.x3rmination.common.enchantments.sword.EnchantmentBeatTheSpammer;
import com.github.x3rmination.common.enchantments.sword.EnchantmentBerserker;
import com.github.x3rmination.common.enchantments.sword.EnchantmentBulletTime;
import com.github.x3rmination.common.enchantments.sword.EnchantmentComboDamage;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.DamageSource;
import java.util.ArrayList;
import java.util.List;

public class EnchantmentInit {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
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
    public static final Enchantment DEVIL_CHICKS = new EnchantmentDevilChicks();
    public static final Enchantment EXPLOSIVE = new EnchantmentExplosive();
    public static final Enchantment LUCKY_SHOT = new EnchantmentLuckyShot();
    public static final Enchantment MEGA_LONGBOW = new EnchantmentMegaLongbow();
    public static final Enchantment PULL_BOW = new EnchantmentPullbow();
    public static final Enchantment TELE_BOW = new EnchantmentTelebow();
    public static final Enchantment TRUE_SHOT = new EnchantmentTrueShot();
    public static final Enchantment VOLLEY = new EnchantmentVolley();
    /*
    Pants Enchantments
     */
    public static final Enchantment BOOBOO = new EnchantmentBooboo();
    public static final Enchantment CRICKET = new EnchantmentCricket();
    public static final Enchantment CRITICALLY_FUNKY = new EnchantmentCriticallyFunky();
    public static final Enchantment COUNTER_OFFENSIVE = new EnchantmentCounterOffensive();
    public static final Enchantment DANGER_CLOSE = new EnchantmentDangerClose();
    public static final Enchantment DIAMOND_ALLERGY = new EnchantmentDiamondAllergy();
    public static final Enchantment EGGS = new EnchantmentEggs();
    public static final Enchantment ELECTROLYTES = new EnchantmentElectrolytes();
    public static final Enchantment GOLDEN_HEART = new EnchantmentGoldenHeart();
    public static final Enchantment GOTTA_GO_FAST = new EnchantmentGottaGoFast();
    public static final Enchantment HEARTS = new EnchantmentHearts();
    public static final Enchantment MCSWIMMER = new EnchantmentMcSwimmer();
    public static final Enchantment MIRROR = new EnchantmentMirror();
    public static final Enchantment NOT_GLADIATOR = new EnchantmentNotGladiator();
    public static final Enchantment PIT_BLOB = new EnchantmentPitBlob();
    public static final Enchantment PRICK = new EnchantmentPrick();
    public static final Enchantment REVITALIZE = new EnchantmentRevitalize();
    public static final Enchantment RING_ARMOR = new EnchantmentRingArmor();
    public static final Enchantment TNT = new EnchantmentTNT();
    public static final Enchantment ASSASSIN = new EnchantmentAssassin();
    public static final Enchantment ESCAPE_POD = new EnchantmentEscapePod();
    public static final Enchantment GOMRAWS_HEART = new EnchantmentGomrawsHeart();
    public static final Enchantment INSTABOOM = new EnchantmentInstaboom();
    public static final Enchantment PHOENIX = new EnchantmentPhoenix();
    public static final Enchantment SINGULARITY = new EnchantmentSingularity();
    public static final Enchantment SOLITUDE = new EnchantmentSolitude();
    public static final Enchantment WOLF_PACK = new EnchantmentWolfPack();
    /*
    Sword Enchantments
     */
    public static final Enchantment BEAT_THE_SPAMMER = new EnchantmentBeatTheSpammer();
    public static final Enchantment BERSERKER = new EnchantmentBerserker();
    public static final Enchantment BULLET_TIME = new EnchantmentBulletTime();
    public static final Enchantment COMBO_DAMAGE = new EnchantmentComboDamage();

}
