package io.github.nocomment1105.deepslatecutting.fabric;

import io.github.nocomment1105.deepslatecutting.common.DeepslateCuttingMain;
import io.github.nocomment1105.deepslatecutting.common.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroups;

import java.io.IOException;

public class DeepslateCutting implements ModInitializer {
    public static final Config configInstance = new Config(FabricLoader.getInstance().getConfigDir().resolve("deepslatecutting.properties"));
    static {
        try {
            configInstance.init();
        } catch (IOException e) {
            DeepslateCuttingMain.LOGGER.error("Failed to initialise DeepslateCutting configuration, default values will be used instead");
            DeepslateCuttingMain.LOGGER.error("", e);
        }
    }

    @Override
    public void onInitialize() {
        if (configInstance.areExtrasEnabled()) {
            DeepslateCuttingMain.init();
            DeepslateCuttingMain.LOGGER.info("Loaded Config! Regular Deepslate extras are ON!");
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
                DeepslateCuttingMain.LOGGER.warn("Please tell your clients they need to enable deepslate extras to " +
                        "see the regular deepslate blocks!");
            }
        } else {
            DeepslateCuttingMain.LOGGER.info("Loaded Config! Regular Deepslate extras are OFF");
        }
    }
}
