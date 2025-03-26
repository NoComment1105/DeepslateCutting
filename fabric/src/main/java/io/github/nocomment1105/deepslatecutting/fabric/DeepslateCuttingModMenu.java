package io.github.nocomment1105.deepslatecutting.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.nocomment1105.deepslatecutting.common.config.ConfigScreen;

public class DeepslateCuttingModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(DeepslateCutting.configInstance, parent);
    }
}
