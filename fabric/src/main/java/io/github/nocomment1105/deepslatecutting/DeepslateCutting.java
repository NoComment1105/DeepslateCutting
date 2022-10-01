package io.github.nocomment1105.deepslatecutting;

import io.github.nocomment1105.deepslatecutting.config.Config;
import io.github.nocomment1105.deepslatecutting.registry.ModRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class DeepslateCutting implements ModInitializer {
    public static Config CONFIG;

    @Override
    public void onInitialize() {
        CONFIG = new Config(false);
        boolean enableExtras = CONFIG.smoothStuff;
        if (enableExtras) {
            ModRegistry.init();
            DeepslateCuttingMain.LOGGER.info("Loaded Config! Regular deepslate extras are ON");
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
                DeepslateCuttingMain.LOGGER.warn("Please tell your clients they need to enable deepslate " +
                        "extras to see the regular deepslate blocks!");
            }
        } else {
            DeepslateCuttingMain.LOGGER.info("Loaded Config! Regular deepslate extras are OFF");
        }
    }
}
