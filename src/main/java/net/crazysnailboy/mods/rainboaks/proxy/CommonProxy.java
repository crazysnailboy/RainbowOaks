package net.crazysnailboy.mods.rainboaks.proxy;

import net.crazysnailboy.mods.rainboaks.common.config.ModConfiguration;
import net.crazysnailboy.mods.rainboaks.init.ModBlocks;
import net.crazysnailboy.mods.rainboaks.init.ModLootTables;
import net.crazysnailboy.mods.rainboaks.world.gen.ModWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{

	public void preInit()
	{
		this.initializeConfiguration();
		this.registerBlocks();
		this.registerLootTables();
	}

	public void init()
	{
		this.registerOreDictionaryEntries();
		this.registerCraftingRecipes();
		this.registerWorldGenerator();
	}

	public void postInit()
	{
	}



	private void initializeConfiguration()
	{
		ModConfiguration.initializeConfiguration();
	}

	private void registerBlocks()
	{
		ModBlocks.registerBlocks();
	}

	private void registerCraftingRecipes()
	{
		ModBlocks.registerCraftingRecipes();
	}

	private void registerLootTables()
	{
		ModLootTables.registerLootTables();
	}

	private void registerOreDictionaryEntries()
	{
		ModBlocks.registerOreDictionaryEntries();
	}

	private void registerWorldGenerator()
	{
		if (ModConfiguration.addToWorldGen)
		{
			GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 0);
		}
	}

}
