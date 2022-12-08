package io.github.nocomment1105.deepslatecutting;

import io.github.nocomment1105.deepslatecutting.config.Config;
import io.github.nocomment1105.deepslatecutting.registry.ModRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroups;

import java.io.IOException;
import java.nio.file.Path;

public class DeepslateCutting implements ModInitializer {
    public static Config config;
    public static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("deepslatecutting.properties");

    @Override
    public void onInitialize() {
        config = new Config(configPath);

        try {
            config.init();
        } catch (IOException e) {
            DeepslateCuttingMain.LOGGER.error("Failed to initialise DeepslateCutting configuration, default values will be used instead");
            DeepslateCuttingMain.LOGGER.error("", e);
        }

        if (config.areExtrasEnabled()) {
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.f_pnoyoezv).register(entries -> {
                entries.addItem(ModRegistry.DEEPSLATE_SLAB_ITEM);
                entries.addItem(ModRegistry.DEEPSLATE_STAIRS_ITEM);
                entries.addItem(ModRegistry.DEEPSLATE_WALL_ITEM);
            });
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
