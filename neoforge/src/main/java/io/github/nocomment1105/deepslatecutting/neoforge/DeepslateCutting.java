package io.github.nocomment1105.deepslatecutting.neoforge;

import io.github.nocomment1105.deepslatecutting.common.DeepslateCuttingMain;
import io.github.nocomment1105.deepslatecutting.common.config.Config;
import io.github.nocomment1105.deepslatecutting.common.config.ConfigScreen;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.io.IOException;

@Mod(DeepslateCuttingMain.MOD_ID)
public class DeepslateCutting {
    public static final Config configInstance = new Config(FMLLoader.getGamePath().resolve("config/deepslatecutting.properties"));

    static {
        try {
            configInstance.init();
        } catch (IOException e) {
            DeepslateCuttingMain.LOGGER.error("Failed to initialise DeepslateCutting configuration, default values will be used instead");
            DeepslateCuttingMain.LOGGER.error("", e);
        }
    }

    public DeepslateCutting(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ((container, parent) -> new ConfigScreen(configInstance, parent)));
        if (configInstance.areExtrasEnabled()) {
            DeepslateCuttingMain.init();
            DeepslateCuttingMain.LOGGER.info("Loaded Config! Regular Deepslate extras are ON!");
            if (FMLLoader.getDist().isDedicatedServer()) {
                DeepslateCuttingMain.LOGGER.warn("Please tell your clients they need to enable deepslate extras to " +
                        "see the regular deepslate blocks!");
            }
        } else {
            DeepslateCuttingMain.LOGGER.info("Loaded Config! Regular Deepslate extras are OFF");
        }
    }
}
