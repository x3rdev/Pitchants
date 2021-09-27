package com.github.x3rmination;


import com.github.x3rmination.common.enchantments.EnchantmentPerun;
import com.github.x3rmination.common.enchantments.bow.*;
import com.github.x3rmination.common.enchantments.bow.rare.*;
import com.github.x3rmination.common.enchantments.pants.*;
import com.github.x3rmination.common.enchantments.pants.rare.*;
import com.github.x3rmination.common.enchantments.sword.*;
import com.github.x3rmination.common.enchantments.sword.rare.EnchantmentBillionare;
import com.github.x3rmination.proxy.CommonProxy;
import com.github.x3rmination.util.handlers.RegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = pitchants.MODID, name = pitchants.NAME, version = pitchants.VERSION)
public class pitchants {

    public static final String MODID = "pitchants";
    public static final String NAME = "pitchants";
    public static final String VERSION = "1.0";
    public static final String ACCEPTED_MINECRAFT_VERSIONS = "[1.12.2]";
    public static final Logger LOGGER = LogManager.getLogger();

    @SidedProxy(clientSide = "com.github.x3rmination.proxy.ClientProxy", serverSide = "com.github.x3rmination.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static pitchants instance;

    public static Logger gamelogger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        gamelogger = event.getModLog();
        proxy.preInit(event);
        RegistryHandler.preInitRegistries(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
//        RegistryHandler.initRegistries(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
        MinecraftForge.EVENT_BUS.register(new EnchantmentArrowArmory());
        MinecraftForge.EVENT_BUS.register(new EnchantmentBottomlessQuiver());
        MinecraftForge.EVENT_BUS.register(new EnchantmentChipping());
        MinecraftForge.EVENT_BUS.register(new EnchantmentFasterThanTheirShadow());
        MinecraftForge.EVENT_BUS.register(new EnchantmentFirstShot());
        MinecraftForge.EVENT_BUS.register(new EnchantmentFletching());
        MinecraftForge.EVENT_BUS.register(new EnchantmentJumpSpammer());
        MinecraftForge.EVENT_BUS.register(new EnchantmentMixedCombat());
        MinecraftForge.EVENT_BUS.register(new EnchantmentPerun());
        MinecraftForge.EVENT_BUS.register(new EnchantmentPushComesToShove());
        MinecraftForge.EVENT_BUS.register(new EnchantmentSpammerAndProud());
        MinecraftForge.EVENT_BUS.register(new EnchantmentSniper());
        MinecraftForge.EVENT_BUS.register(new EnchantmentLuckyShot());
        MinecraftForge.EVENT_BUS.register(new EnchantmentMegaLongbow());
        MinecraftForge.EVENT_BUS.register(new EnchantmentPullbow());
        MinecraftForge.EVENT_BUS.register(new EnchantmentTelebow());
        MinecraftForge.EVENT_BUS.register(new EnchantmentTrueShot());
        MinecraftForge.EVENT_BUS.register(new EnchantmentVolley());
        MinecraftForge.EVENT_BUS.register(new EnchantmentDevilChicks());
        MinecraftForge.EVENT_BUS.register(new EnchantmentExplosive());

        MinecraftForge.EVENT_BUS.register(new EnchantmentBooboo());
        MinecraftForge.EVENT_BUS.register(new EnchantmentCricket());
        MinecraftForge.EVENT_BUS.register(new EnchantmentCriticallyFunky());
        MinecraftForge.EVENT_BUS.register(new EnchantmentDangerClose());
        MinecraftForge.EVENT_BUS.register(new EnchantmentDiamondAllergy());
        MinecraftForge.EVENT_BUS.register(new EnchantmentEggs());
        MinecraftForge.EVENT_BUS.register(new EnchantmentElectrolytes());
        MinecraftForge.EVENT_BUS.register(new EnchantmentGoldenHeart());
        MinecraftForge.EVENT_BUS.register(new EnchantmentGottaGoFast());
        MinecraftForge.EVENT_BUS.register(new EnchantmentHearts());
        MinecraftForge.EVENT_BUS.register(new EnchantmentMcSwimmer());
        MinecraftForge.EVENT_BUS.register(new EnchantmentMirror());
        MinecraftForge.EVENT_BUS.register(new EnchantmentNotGladiator());
        MinecraftForge.EVENT_BUS.register(new EnchantmentPitBlob());
        MinecraftForge.EVENT_BUS.register(new EnchantmentRingArmor());
        MinecraftForge.EVENT_BUS.register(new EnchantmentTNT());
        MinecraftForge.EVENT_BUS.register(new EnchantmentEscapePod());
        MinecraftForge.EVENT_BUS.register(new EnchantmentGomrawsHeart());
        MinecraftForge.EVENT_BUS.register(new EnchantmentInstaboom());
        MinecraftForge.EVENT_BUS.register(new EnchantmentPhoenix());
        MinecraftForge.EVENT_BUS.register(new EnchantmentSingularity());
        MinecraftForge.EVENT_BUS.register(new EnchantmentSolitude());
        MinecraftForge.EVENT_BUS.register(new EnchantmentWolfPack());

        MinecraftForge.EVENT_BUS.register(new EnchantmentBeatTheSpammer());
        MinecraftForge.EVENT_BUS.register(new EnchantmentBerserker());
        MinecraftForge.EVENT_BUS.register(new EnchantmentBulletTime());
        MinecraftForge.EVENT_BUS.register(new EnchantmentComboDamage());
        MinecraftForge.EVENT_BUS.register(new EnchantmentDiamondStomp());
        MinecraftForge.EVENT_BUS.register(new EnchantmentGoldAndBoosted());
        MinecraftForge.EVENT_BUS.register(new EnchantmentGrasshopper());
        MinecraftForge.EVENT_BUS.register(new EnchantmentKingBuster());
        MinecraftForge.EVENT_BUS.register(new EnchantmentLifesteal());
        MinecraftForge.EVENT_BUS.register(new EnchantmentPainFocus());
        MinecraftForge.EVENT_BUS.register(new EnchantmentPunisher());
        MinecraftForge.EVENT_BUS.register(new EnchantmentShark());
        MinecraftForge.EVENT_BUS.register(new EnchantmentBillionare());


//        RegistryHandler.postInitRegistries(event);

    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
//        RegistryHandler.serverRegistries(event);
    }

}
