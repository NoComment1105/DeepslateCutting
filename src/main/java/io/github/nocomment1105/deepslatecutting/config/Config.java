package io.github.nocomment1105.deepslatecutting.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import io.github.nocomment1105.deepslatecutting.DeepslateCutting;
import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;


public class Config {
    private final transient File file = FabricLoader.getInstance().getConfigDir().resolve("deepslatecutting.toml").toFile();
    public boolean smoothStuff;
    public Config(boolean b){
        this.smoothStuff = b;
        if (file.exists() && file.isFile()) {
            Toml toml = new Toml().read(file);
            this.smoothStuff = toml.getBoolean("smoothStuff");
        } else {
            DeepslateCutting.LOGGER.info("Unable to find config file for DeepslateCutting, creating");
            try {
                Files.copy(Objects.requireNonNull(Config.class.getResourceAsStream("/data/deepslatecutting/default_config.toml")), file.toPath());
            } catch (IOException e) {
                DeepslateCutting.LOGGER.warn("Unable to create config file for DeepslateCutting");
                e.printStackTrace();
            }
       }
    }
    public void save() {
        TomlWriter writer = new TomlWriter();
        try {
            writer.write(this, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
