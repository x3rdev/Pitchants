package com.github.x3rmination;


import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.util.handlers.EventHandler;
import com.github.x3rmination.util.handlers.RenderGuiHandler;
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

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
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
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new EnchantmentInit());
//        RegistryHandler.postInitRegistries(event);

    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
//        RegistryHandler.serverRegistries(event);
    }

}
