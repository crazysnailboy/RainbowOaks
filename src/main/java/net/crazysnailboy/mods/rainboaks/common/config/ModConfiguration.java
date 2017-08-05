package net.crazysnailboy.mods.rainboaks.common.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.crazysnailboy.mods.rainboaks.RainboaksMod;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;


public class ModConfiguration
{

	private static class DefaultValues
	{
		private static final int largeTreeChance = 7;

		private static final boolean addToWorldGen = true;
		private static final double treesPerChunk = 0.2D;
		private static final String[] biomeList = new String[] { "minecraft:mutated_forest" };

		private static final boolean addToLootTables = true;
		private static final String[] lootTableList = new String[] { "minecraft:chests/simple_dungeon", "minecraft:chests/stronghold_library", "minecraft:chests/village_blacksmith", "minecraft:chests/desert_pyramid", "minecraft:chests/jungle_temple" };
	}


	private static Configuration config = null;

	public static final String CATEGORY_WORLDGEN = "worldgen";
	public static final String CATEGORY_LOOTTABLES = "loottables";

	public static int largeTreeChance = DefaultValues.largeTreeChance;

	public static boolean addToWorldGen = DefaultValues.addToWorldGen;
	public static double treesPerChunk = DefaultValues.treesPerChunk;
	public static String[] biomeList = DefaultValues.biomeList;

	public static boolean addToLootTables = DefaultValues.addToLootTables;
	public static String[] lootTableList = DefaultValues.lootTableList;


	public static void initializeConfiguration()
	{
		// load the configuration from the file
		config = new Configuration(new File(Loader.instance().getConfigDir(), RainboaksMod.MODID + ".cfg"));
		config.load();


		// get the configuration properties
		config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, "");
		Property propLargeTreeChance = config.get(Configuration.CATEGORY_GENERAL, "largeTreeChance", DefaultValues.largeTreeChance, "Chance of getting a large tree from a sapling. The higher the number, the lower the probability.\nDefault is " + DefaultValues.largeTreeChance + ".");

		config.addCustomCategoryComment(CATEGORY_WORLDGEN, "See https://github.com/crazysnailboy/RainbowOaks/wiki/List-of-Vanilla-Biomes for a list of biome names");
		Property propAddToWorldGen = config.get(CATEGORY_WORLDGEN, "addToWorldGen", DefaultValues.addToWorldGen, "Should trees appear naturally in the world.\nDefault is " + DefaultValues.addToWorldGen + ".");
		Property propTreesPerChunk = config.get(CATEGORY_WORLDGEN, "treesPerChunk", DefaultValues.treesPerChunk, "Number of trees per chunk.\nDefault is " + DefaultValues.treesPerChunk + ".");
		Property propBiomeList = config.get(CATEGORY_WORLDGEN, "biomeList", DefaultValues.biomeList, "List of biomes trees should generate in.\nDefault is " + Arrays.toString(DefaultValues.biomeList) + ".");

		config.addCustomCategoryComment(CATEGORY_LOOTTABLES, "See http://minecraft.gamepedia.com/Loot_table#List_of_loot_tables for a list of vanilla loot tables");
		Property propAddToLootTables = config.get(CATEGORY_LOOTTABLES, "addToLootTables", DefaultValues.addToLootTables, "Should saplings be added to loot tables.\nDefault is " + DefaultValues.addToLootTables + ".");
		Property propLootTableList = config.get(CATEGORY_LOOTTABLES, "lootTableList", DefaultValues.lootTableList, "List of loot tables to add saplings to.\nDefault is " + Arrays.toString(DefaultValues.lootTableList) + ".");


		// set the category property order
		List<String> propOrderGeneral = new ArrayList<String>();
		propOrderGeneral.add(propLargeTreeChance.getName());
		config.setCategoryPropertyOrder(Configuration.CATEGORY_GENERAL, propOrderGeneral);

		List<String> propOrderWorldGen = new ArrayList<String>();
		propOrderWorldGen.add(propAddToWorldGen.getName());
		propOrderWorldGen.add(propTreesPerChunk.getName());
		propOrderWorldGen.add(propBiomeList.getName());
		config.setCategoryPropertyOrder(CATEGORY_WORLDGEN, propOrderWorldGen);

		List<String> propOrderLootTables = new ArrayList<String>();
		propOrderLootTables.add(propAddToLootTables.getName());
		propOrderLootTables.add(propLootTableList.getName());
		config.setCategoryPropertyOrder(CATEGORY_LOOTTABLES, propOrderLootTables);


		// read the property values
		largeTreeChance = propLargeTreeChance.getInt();

		addToWorldGen = propAddToWorldGen.getBoolean();
		treesPerChunk = propTreesPerChunk.getDouble();
		biomeList = propBiomeList.getStringList();

		addToLootTables = propAddToLootTables.getBoolean();
		lootTableList = propLootTableList.getStringList();


		// save the configuration to the file
		if (config.hasChanged()) config.save();
	}

}