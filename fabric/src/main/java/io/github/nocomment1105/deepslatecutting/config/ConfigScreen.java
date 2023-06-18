package io.github.nocomment1105.deepslatecutting.config;

import io.github.nocomment1105.deepslatecutting.DeepslateCutting;
import io.github.nocomment1105.deepslatecutting.DeepslateCuttingMain;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.CommonTexts;
import net.minecraft.text.Text;

import java.io.IOException;

public class ConfigScreen extends GameOptionsScreen {
    private final Screen parent;
    private ButtonListWidget list;

    private final BooleanConfigOption extrasOption = new BooleanConfigOption("toggle_deepslate_extras", DeepslateCutting.config.areExtrasEnabled());

    public ConfigScreen(Screen parent) {
        super(parent, MinecraftClient.getInstance().options, Text.translatable("config.deepslatecutting.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        if (this.client == null) {
            return;
        }

        this.list = new ButtonListWidget(client, width, height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(extrasOption.asOption());
        this.addSelectableChild(this.list);

        this.addDrawableChild(new ButtonWidget.Builder(CommonTexts.DONE, buttonWidget -> {
            try {
                Config.update(DeepslateCutting.configPath, extrasOption.getValue());
            } catch (IOException e) {
                DeepslateCuttingMain.LOGGER.error("Failed to initialise DeepslateCutting configuration, default values will be used instead");
                DeepslateCuttingMain.LOGGER.error("", e);
            }
            this.client.setScreen(this.parent);
        }).positionAndSize(this.width / 2 - 100, this.height - 27, 200, 20).build());
    }

    @Override
    public void removed() {
        super.removed();
        try {
            Config.update(DeepslateCutting.configPath, extrasOption.getValue());
        } catch (IOException e) {
            DeepslateCuttingMain.LOGGER.error("Failed to initialise DeepslateCutting configuration, default values will be used instead");
            DeepslateCuttingMain.LOGGER.error("", e);
        }
    }

    @Override
    protected void renderWithList(GuiGraphics graphics, ButtonListWidget buttonList, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics);
        this.list.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredShadowedText(this.textRenderer, this.title, this.width / 2, 8, 0xffffff);
        graphics.drawCenteredShadowedText(this.textRenderer, "This option requires a restart to take effect.", this.width / 2, this.height / 2 - 57, 0xffffff);
        super.render(graphics, mouseX, mouseY, delta);
    }
}
