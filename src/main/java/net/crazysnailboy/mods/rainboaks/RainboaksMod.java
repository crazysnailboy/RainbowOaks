package net.crazysnailboy.mods.rainboaks;

import net.crazysnailboy.mods.rainboaks.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RainboaksMod.MODID, name = RainboaksMod.NAME, version = RainboaksMod.VERSION, updateJSON = RainboaksMod.UPDATEJSON)
public class RainboaksMod
{

	public static final String MODID = "rainboaks";
	public static final String NAME = "Rainbow Oak Trees";
	public static final String VERSION = "${version}";
	public static final String UPDATEJSON = "https://raw.githubusercontent.com/crazysnailboy/RainbowOaks/master/update.json";

	private static final String CLIENT_PROXY_CLASS = "net.crazysnailboy.mods.rainboaks.proxy.ClientProxy";
	private static final String SERVER_PROXY_CLASS = "net.crazysnailboy.mods.rainboaks.proxy.CommonProxy";


	@Instance(MODID)
	public static RainboaksMod instance;

	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
	public static CommonProxy proxy;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}

}
