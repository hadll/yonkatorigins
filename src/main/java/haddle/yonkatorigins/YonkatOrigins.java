package haddle.yonkatorigins;

import haddle.yonkatorigins.item.YOModItems;
import haddle.yonkatorigins.registry.YOActionTypes;
import haddle.yonkatorigins.registry.YOCommands;
import haddle.yonkatorigins.registry.YOComponents;
import haddle.yonkatorigins.registry.YOPowers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YonkatOrigins implements ModInitializer {
	public static final String MOD_ID = "yonkatorigins";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		YOPowers.init();
		YOCommands.init();
		YOActionTypes.init();
		YOModItems.init();
		LOGGER.info("Finished Loading");
	}
}