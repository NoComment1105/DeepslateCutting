package io.github.nocomment1105.deepslatecutting.common.config;

import io.github.nocomment1105.deepslatecutting.common.DeepslateCuttingMain;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.io.IOException;

public class ConfigScreen extends Screen {
    private final Screen parent;
    private final Config config;

    public ConfigScreen(Config config, Screen parent) {
        super(Text.translatable("config.deepslatecutting.title"));
        this.parent = parent;
        this.config = config;
    }

    @Override
    protected void init() {
        if (this.client == null) return;

        ConfigValue<Boolean> extrasValue = ConfigValue.of("config.deepslatecutting.toggle_deepslate_extras", config.areExtrasEnabled());

        ButtonWidget.Builder extrasButton = ButtonWidget.builder(
                ScreenTexts.composeGenericOptionText(
                        Text.translatable(extrasValue.getTranslationKey()), ScreenTexts.onOrOff(extrasValue.get())
                ), button -> {
                    boolean newExtrasValue = !extrasValue.get();
                    extrasValue.set(newExtrasValue);
                    Text newText = ScreenTexts.onOrOff(newExtrasValue);
                    if (extrasValue.isDifferent()) {
                        newText = newText.copy().styled(style -> style.withBold(true));
                    }
                    button.setMessage(ScreenTexts.composeGenericOptionText(Text.translatable(extrasValue.getTranslationKey()), newText));
                }).tooltip(Tooltip.of(Text.translatable("config.deepslatecutting.restart_required")));


        addDrawableChild(extrasButton.position(width / 2 - 100, height / 2 - 52).size(200, 20).build());

        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> {
            try {
                config.update(config.getPropertiesPath(), extrasValue.get());
            } catch (IOException e) {
                DeepslateCuttingMain.LOGGER.error("Failed to save DeepslateCutting configuration, current values will remain.");
                DeepslateCuttingMain.LOGGER.error("", e);
            }
            close();
        }).dimensions(width / 2 - 75 - 79, height - 40, 150, 20).build());
        addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, button -> close()).dimensions(width / 2 - 75 + 79, height - 40, 150, 20).build());
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        this.renderBackground(drawContext, mouseX, mouseY, delta);
        super.render(drawContext, mouseX, mouseY, delta);
        drawContext.drawCenteredTextWithShadow(textRenderer, title, width / 2, 30, 0xFFFFFF);
    }

    @Override
    public void close() {
        assert this.client != null;
        this.client.setScreen(parent);
    }

    private static class ConfigValue<T> {
        private final String translationKey;
        private final T originalValue;
        private T value;

        public ConfigValue(String translationKey, T originalValue) {
            this.translationKey = translationKey;
            this.originalValue = originalValue;
            value = originalValue;
        }

        public static <T> ConfigValue<T> of(String translationKey, T originalValue) {
            return new ConfigValue<>(translationKey, originalValue);
        }

        public T get() {
            return value;
        }

        public void set(T value) {
            this.value = value;
        }

        public boolean isDifferent() {
            return !value.equals(originalValue);
        }

        public String getTranslationKey() {
            return this.translationKey;
        }
    }
}
