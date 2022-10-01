package io.github.nocomment1105.deepslatecutting.config;

import io.github.nocomment1105.deepslatecutting.DeepslateCutting;
import io.github.nocomment1105.deepslatecutting.DeepslateCuttingMain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Config {
    public static final String COMMENT =
            "This file stores the config option for DeepslateCutting";

    private static boolean enableExtras;

    private static Path propertiesPath = null;

    public Config(Path propertiesPath) {
        enableExtras = false;
        this.propertiesPath = propertiesPath;
    }

    public void init() throws IOException {
        load();
        if (!Files.exists(propertiesPath)) {
            save();
        }
    }

    public boolean areExtrasEnabled() {
        try {
            load();
        } catch (IOException e) {
            DeepslateCuttingMain.LOGGER.error("Failed to initialise DeepslateCutting configuration, default values will be used instead");
            DeepslateCuttingMain.LOGGER.error("", e);
        }
        return enableExtras;
    }

    public void load() throws IOException {
        if (!Files.exists(propertiesPath)) {
            return;
        }

        Properties properties = new Properties();

        properties.load(Files.newInputStream(propertiesPath));
        enableExtras = "true".equals(properties.getProperty("enableExtras"));
    }

    public static void save() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("enableExtras", enableExtras ? "true" : "false");
        properties.store(Files.newOutputStream(propertiesPath), COMMENT);
    }

    public static void update(Path path, boolean newValue) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(path));
        properties.setProperty("enableExtras", newValue ? "true" : "false");
        properties.store(Files.newOutputStream(path), Config.COMMENT);
    }
}
