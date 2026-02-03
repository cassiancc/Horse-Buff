package net.F53.HorseBuff;

import net.F53.HorseBuff.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HorseBuffInit implements ModInitializer {
    public static final String MOD_ID = "horsebuff";
    public static final Logger LOGGER = LogManager.getLogger("HorseBuff");
    public static final ModConfig CONFIG = ModConfig.init();

    @Override
    public void onInitialize() {
        LOGGER.info("Horse Buff Initialized");
    }
}
