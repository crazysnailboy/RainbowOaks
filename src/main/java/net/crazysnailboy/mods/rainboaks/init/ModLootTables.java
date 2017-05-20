package net.crazysnailboy.mods.rainboaks.init;

import org.apache.commons.lang3.ArrayUtils;
import net.crazysnailboy.mods.rainboaks.RainboaksMod;
import net.crazysnailboy.mods.rainboaks.common.config.ModConfiguration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModLootTables
{

	public static final ResourceLocation LOOT_TABLE_TEST = new ResourceLocation(RainboaksMod.MODID, "loot_table_rainboak");

	public static void registerLootTables()
	{
		LootTableList.register(LOOT_TABLE_TEST);
	}


	@EventBusSubscriber
	public static class EventHandlers
	{
		@SubscribeEvent
		public static void onLootTableLoad(LootTableLoadEvent event)
		{
			if (ModConfiguration.addToLootTables && ArrayUtils.contains(ModConfiguration.lootTableList, event.getName().toString()))
			{
				final String name = ModLootTables.LOOT_TABLE_TEST.toString();

				final LootEntry entry = new LootEntryTable(ModLootTables.LOOT_TABLE_TEST, 1, 0, new LootCondition[0], name);
				final RandomValueRange rolls = new RandomValueRange(0, 1);
				final LootPool pool = new LootPool(new LootEntry[]{entry}, new LootCondition[0], rolls, rolls, name);

				event.getTable().addPool(pool);
			}
		}


	}


}
