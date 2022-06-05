package io.github.nocomment1105.deepslatecutting;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DeepslateCuttingModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return DeepslateCuttingModMenu::genConfig;
    }

    private static Screen genConfig(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.createFormatted("config.deepslatecutting.title"))
                .setSavingRunnable(DeepslateCutting.CONFIG::save);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.getOrCreateCategory(Text.createFormatted("config.deepslatecutting.general"))
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                Text.createFormatted("config.deepslatecutting.toggle_deepslate_extras"),
                                DeepslateCutting.CONFIG.smoothStuff
                        )
                        .requireRestart()
                        .setSaveConsumer(value -> DeepslateCutting.CONFIG.smoothStuff = value)
                        .build()
                );
        return builder.build();
    }
}
