package net.crazysnailboy.mods.rainboaks.client.renderer.color;

import net.crazysnailboy.mods.rainboaks.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModBlockColors
{
	private static final Minecraft minecraft = Minecraft.getMinecraft();

	public static void registerColourHandlers()
	{
		final BlockColors blockColors = minecraft.getBlockColors();
		final ItemColors itemColors = minecraft.getItemColors();

		registerBlockColorHandlers(blockColors);
		registerItemColorHandlers(blockColors, itemColors);
	}


	private static void registerBlockColorHandlers(final BlockColors blockColors)
	{
		final IBlockColor leafColorHandler = new BlockColorRainbowLeaves();
		blockColors.registerBlockColorHandler(leafColorHandler, ModBlocks.LEAVES);
	}

	private static void registerItemColorHandlers(final BlockColors blockColors, final ItemColors itemColors)
	{
		final IItemColor itemBlockColorHandler = new IItemColor(){

			@SuppressWarnings("deprecation")
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex)
			{
				IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
				return blockColors.colorMultiplier(iblockstate, null, null, tintIndex);
			}
		};

		itemColors.registerItemColorHandler(itemBlockColorHandler, ModBlocks.LEAVES);
	}


	public static class BlockColorRainbowLeaves implements IBlockColor
	{

		@Override
		public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex)
		{
			if (worldIn != null && pos != null)
			{
				int red = 0;
				int green = 0;
				int blue = 0;

				for (int var9 = -1; var9 <= 1; ++var9)
				{
					for (int var10 = -1; var10 <= 1; ++var10)
					{
						int var11 = BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
						red += (var11 & 16711680) >> 16;
						green += (var11 & 65280) >> 8;
						blue += var11 & 255;
					}
				}

				int normalColor = (red / 9 & 0xFF) << 16 | (green / 9 & 0xFF) << 8 | blue / 9 & 0xFF;

				red = pos.getX() * 32 + pos.getY() * 16;
				if ((red & 256) != 0)
				{
					red = 255 - (red & 255);
				}
				red &= 255;

				blue = pos.getY() * 32 + pos.getZ() * 16;
				if ((blue & 256) != 0)
				{
					blue = 255 - (blue & 255);
				}
				blue ^= 255;

				green = pos.getX() * 16 + pos.getZ() * 32;
				if ((green & 256) != 0)
				{
					green = 255 - (green & 255);
				}
				green &= 255;

				return red << 16 | blue << 8 | green;
			}
			else
			{
				return ColorizerFoliage.getFoliageColorBasic();
			}
		}

	}


}
