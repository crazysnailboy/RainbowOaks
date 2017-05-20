package net.crazysnailboy.mods.rainboaks.init;

import net.crazysnailboy.mods.rainboaks.block.BlockTFLeaves;
import net.crazysnailboy.mods.rainboaks.block.BlockTFLog;
import net.crazysnailboy.mods.rainboaks.block.BlockTFSapling;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{

	public static final Block LOG = new BlockTFLog().setRegistryName("rainboak_log").setUnlocalizedName("log.rainboak");
	public static final Block LEAVES = new BlockTFLeaves().setRegistryName("rainboak_leaves").setUnlocalizedName("leaves.rainboak");
	public static final Block SAPLING = new BlockTFSapling().setRegistryName("rainboak_sapling").setUnlocalizedName("sapling.rainboak");

	public static void registerBlocks()
	{
		GameRegistry.register(LOG);
		GameRegistry.register(LEAVES);
		GameRegistry.register(SAPLING);

		GameRegistry.register(new ItemBlock(LOG).setRegistryName(LOG.getRegistryName()));
		GameRegistry.register(new ItemBlock(LEAVES).setRegistryName(LEAVES.getRegistryName()));
		GameRegistry.register(new ItemBlock(SAPLING).setRegistryName(SAPLING.getRegistryName()));

		Blocks.FIRE.setFireInfo(LOG, 5, 5);
		Blocks.FIRE.setFireInfo(LEAVES, 30, 60);
	}

	public static void registerInventoryModels()
	{
		registerInventoryModel(LOG);
		registerInventoryModel(LEAVES);
		registerInventoryModel(SAPLING);
	}

	private static void registerInventoryModel(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}


	public static void registerOreDictionaryEntries()
	{
		OreDictionary.registerOre("logWood", new ItemStack(LOG, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(SAPLING, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(LEAVES, 1, OreDictionary.WILDCARD_VALUE));
	}

}
