package io.github.nocomment1105.deepslatecutting;

import com.google.gson.Gson;
import io.github.nocomment1105.deepslatecutting.config.Config;
import io.github.nocomment1105.deepslatecutting.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeepslateCutting implements ModInitializer {
    public static final String MOD_ID = "deepslatecutting";
    public static Config CONFIG;
    public static final Logger LOGGER = LogManager.getLogger("Deepslate Cutting");
    @Override
    public void onInitialize() {
        CONFIG = new Config(false);
        var myValue = CONFIG.smoothStuff;
        if (myValue) {
            ModRegistry.init();
            LOGGER.info("Loaded Config! Regular deepslate extras are ON");
            LOGGER.warn("If you are running a server, please tell your clients they need to enable deepslate " +
                    "extras to see the regular deepslate blocks!");
        } else {
            LOGGER.info("Loaded Config! Regular deepslate extras are OFF");
        }
    }
}
