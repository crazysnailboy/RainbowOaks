package net.crazysnailboy.mods.rainboaks.proxy;

import net.crazysnailboy.mods.rainboaks.client.renderer.color.ModBlockColors;


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
		this.registerColorHandlers();
	}

	@Override
	public void postInit()
	{
		super.postInit();
	}


	private void registerColorHandlers()
	{
		ModBlockColors.registerColorHandlers();
	}

}