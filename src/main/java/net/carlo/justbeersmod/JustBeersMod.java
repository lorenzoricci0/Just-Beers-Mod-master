package net.carlo.justbeersmod;

import net.carlo.justbeersmod.block.ModBlocks;
import net.carlo.justbeersmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JustBeersMod implements ModInitializer {
	public static final String MOD_ID = "justbeersmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
	}
}