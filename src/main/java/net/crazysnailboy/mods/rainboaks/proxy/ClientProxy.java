package net.crazysnailboy.mods.rainboaks.proxy;

import net.crazysnailboy.mods.rainboaks.client.renderer.color.ModBlockColors;
import net.crazysnailboy.mods.rainboaks.init.ModBlocks;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit()
	{
		super.preInit();
	}

	@Override
	public void init()
	{
		super.init();
		this.registerInventoryModels();
		this.registerColorHandlers();
	}

	@Override
	public void postInit()
	{
		super.postInit();
	}


	private void registerColorHandlers()
	{
		ModBlockColors.registerColourHandlers();
	}

	private void registerInventoryModels()
	{
		ModBlocks.registerInventoryModels();
	}

}
