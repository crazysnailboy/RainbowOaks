package net.crazysnailboy.mods.rainboaks.proxy;

import net.crazysnailboy.mods.rainboaks.common.config.ModConfiguration;
import net.crazysnailboy.mods.rainboaks.init.ModBlocks;
import net.crazysnailboy.mods.rainboaks.init.ModLootTables;
import net.crazysnailboy.mods.rainboaks.world.gen.ModWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class CommonProxy
{

	public void preInit()
	{
		this.initializeConfiguration();
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

	private void registerCraftingRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(Blocks.PLANKS, 4, 0), new Object[] { "w", 'w', new ItemStack(ModBlocks.LOG, 1, 0) });
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