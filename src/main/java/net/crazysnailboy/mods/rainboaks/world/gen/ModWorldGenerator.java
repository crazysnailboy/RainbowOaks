package net.crazysnailboy.mods.rainboaks.world.gen;

import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
import net.crazysnailboy.mods.rainboaks.common.config.ModConfiguration;
import net.crazysnailboy.mods.rainboaks.world.gen.feature.TFGenLargeRainboak;
import net.crazysnailboy.mods.rainboaks.world.gen.feature.TFGenSmallRainboak;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGenerator implements IWorldGenerator
{

	private BlockPos chunkPos = BlockPos.ORIGIN;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		chunkPos = new BlockPos(blockX, 0, blockZ);

		switch (world.provider.getDimension())
		{
			case -1: generateNether(world, random, blockX, blockZ); break;
			case 0: generateOverworld(world, random, blockX, blockZ); break;
			case 1: generateEnd(world, random, blockX, blockZ); break;
		}
	}

	private void generateNether(World world, Random rand, int blockX, int blockZ)
	{
	}

	private void generateOverworld(World world, Random rand, int blockX, int blockZ)
	{
		WorldGenerator largeTree = new TFGenLargeRainboak(true);
		WorldGenerator smallTree = new TFGenSmallRainboak(true);

		Biome biome = world.getBiomeForCoordsBody(new BlockPos(blockX, 64, blockZ));

		if (ArrayUtils.contains(ModConfiguration.biomeList, biome.getRegistryName().toString()))
		{
			if (ModConfiguration.treesPerChunk >= 1)
			{
				for ( int i = 0 ; i < Math.round(ModConfiguration.treesPerChunk) ; i++ )
				{
					int i8 = rand.nextInt(16) + 8;
					int l11 = rand.nextInt(16) + 8;
					BlockPos blockpos2 = world.getHeight(this.chunkPos.add(i8, 0, l11));

					WorldGenerator tree = (rand.nextInt(ModConfiguration.largeTreeChance) == 0 ? largeTree : smallTree);
					tree.generate(world, rand, blockpos2);
				}
			}
			else
			{
				if (rand.nextInt((int)Math.round(1 / ModConfiguration.treesPerChunk)) == 0)
				{
					int i8 = rand.nextInt(16) + 8;
					int l11 = rand.nextInt(16) + 8;
					BlockPos blockpos2 = world.getHeight(this.chunkPos.add(i8, 0, l11));

					WorldGenerator tree = (rand.nextInt(ModConfiguration.largeTreeChance) == 0 ? largeTree : smallTree);
					tree.generate(world, rand, blockpos2);
				}
			}
		}
	}

	private void generateEnd(World world, Random rand, int blockX, int blockZ)
	{
	}

}
