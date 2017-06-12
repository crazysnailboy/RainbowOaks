package net.crazysnailboy.mods.rainboaks.init;

import java.util.UUID;
import net.crazysnailboy.mods.rainboaks.block.BlockRainbowLeaves;
import net.crazysnailboy.mods.rainboaks.block.BlockRainbowLog;
import net.crazysnailboy.mods.rainboaks.block.BlockRainbowSapling;
import net.crazysnailboy.mods.rainboaks.item.ItemRainbowLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{

	public static final Block LOG = new BlockRainbowLog().setRegistryName("rainboak_log").setUnlocalizedName("log.rainboak");
	public static final BlockLeaves LEAVES = (BlockLeaves)(new BlockRainbowLeaves().setRegistryName("rainboak_leaves").setUnlocalizedName("leaves.rainboak"));
	public static final Block SAPLING = new BlockRainbowSapling().setRegistryName("rainboak_sapling").setUnlocalizedName("sapling.rainboak");

	public static void registerBlocks()
	{
		registerBlock(LOG);
		registerBlock(LEAVES, new ItemRainbowLeaves(LEAVES));
		registerBlock(SAPLING);

		Blocks.FIRE.setFireInfo(LOG, 5, 5);
		Blocks.FIRE.setFireInfo(LEAVES, 30, 60);
	}

	public static void registerInventoryModels()
	{
		registerInventoryModel(LOG);
		registerInventoryModel(LEAVES);
		registerInventoryModel(SAPLING);
	}

	public static void registerOreDictionaryEntries()
	{
		OreDictionary.registerOre("logWood", new ItemStack(LOG, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(SAPLING, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(LEAVES, 1, OreDictionary.WILDCARD_VALUE));
	}


	private static void registerBlock(Block block)
	{
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	private static void registerBlock(Block block, ItemBlock item)
	{
		GameRegistry.register(block);
		GameRegistry.register(item.setRegistryName(block.getRegistryName()));
	}

	public static void registerCraftingRecipes()
	{
		addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4, 0), new Object[] { new ItemStack(ModBlocks.LOG, 1, 0) });
	}

	private static void registerInventoryModel(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}


	private static void addShapelessRecipe(ItemStack stack, Object... recipeComponents)
	{
		String name = UUID.randomUUID().toString();
		NonNullList<Ingredient> list = NonNullList.create();

		for (Object object : recipeComponents)
		{
			if (object instanceof ItemStack)
			{
				list.add(Ingredient.func_193369_a(((ItemStack)object).copy()));
			}
			else if (object instanceof Item)
			{
				list.add(Ingredient.func_193369_a(new ItemStack((Item)object)));
			}
			else
			{
				if (!(object instanceof Block))
				{
					throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
				}

				list.add(Ingredient.func_193369_a(new ItemStack((Block)object)));
			}
		}

		CraftingManager.func_193379_a(name, new ShapelessRecipes(name, stack, list));
	}

}
