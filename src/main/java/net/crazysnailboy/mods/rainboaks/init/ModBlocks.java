package net.crazysnailboy.mods.rainboaks.init;

import net.crazysnailboy.mods.rainboaks.block.BlockRainbowLeaves;
import net.crazysnailboy.mods.rainboaks.block.BlockRainbowLog;
import net.crazysnailboy.mods.rainboaks.block.BlockRainbowSapling;
import net.crazysnailboy.mods.rainboaks.item.ItemRainbowLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;


@EventBusSubscriber
public class ModBlocks
{

	public static final Block LOG = new BlockRainbowLog().setRegistryName("rainboak_log").setUnlocalizedName("log.rainboak");
	public static final BlockLeaves LEAVES = (BlockLeaves)(new BlockRainbowLeaves().setRegistryName("rainboak_leaves").setUnlocalizedName("leaves.rainboak"));
	public static final Block SAPLING = new BlockRainbowSapling().setRegistryName("rainboak_sapling").setUnlocalizedName("sapling.rainboak");


	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(LOG, LEAVES, SAPLING);

		Blocks.FIRE.setFireInfo(LOG, 5, 5);
		Blocks.FIRE.setFireInfo(LEAVES, 30, 60);
	}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
			new ItemBlock(LOG).setRegistryName(LOG.getRegistryName()),
			new ItemRainbowLeaves(LEAVES).setRegistryName(LEAVES.getRegistryName()),
			new ItemBlock(SAPLING).setRegistryName(SAPLING.getRegistryName())
		);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerInventoryModels(final ModelRegistryEvent event)
	{
		for ( Block block : new Block[] { LOG, LEAVES, SAPLING })
		{
			final Item item = Item.getItemFromBlock(block);
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}


	public static void registerOreDictionaryEntries()
	{
		OreDictionary.registerOre("logWood", new ItemStack(LOG, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(SAPLING, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(LEAVES, 1, OreDictionary.WILDCARD_VALUE));
	}

}
