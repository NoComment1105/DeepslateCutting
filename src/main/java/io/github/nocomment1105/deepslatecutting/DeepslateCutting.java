package io.github.nocomment1105.deepslatecutting;

import io.github.nocomment1105.deepslatecutting.config.Config;
import io.github.nocomment1105.deepslatecutting.registry.ModRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeepslateCutting implements ModInitializer {
    public static final String MOD_ID = "deepslatecutting";
    public static Config CONFIG;
    public static final Logger LOGGER = LogManager.getLogger("Deepslate Cutting");
    @Override
    public void onInitialize() {
        CONFIG = new Config(false);
        boolean enableExtras = CONFIG.smoothStuff;
        if (enableExtras) {
            ModRegistry.init();
            LOGGER.info("Loaded Config! Regular deepslate extras are ON");
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
                LOGGER.warn("Please tell your clients they need to enable deepslate " +
                        "extras to see the regular deepslate blocks!");
            }
        } else {
            LOGGER.info("Loaded Config! Regular deepslate extras are OFF");
        }
    }
}
