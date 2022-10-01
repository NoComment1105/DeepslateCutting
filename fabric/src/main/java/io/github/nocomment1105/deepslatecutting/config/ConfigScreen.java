package io.github.nocomment1105.deepslatecutting.config;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTexts;
import dev.lambdaurora.spruceui.option.SpruceBooleanOption;
import dev.lambdaurora.spruceui.option.SpruceOption;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import io.github.nocomment1105.deepslatecutting.DeepslateCutting;
import io.github.nocomment1105.deepslatecutting.DeepslateCuttingMain;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.function.Consumer;

public class ConfigScreen extends SpruceScreen {
    private final Screen parent;
    private SpruceOptionListWidget list;

    public Consumer<SpruceButtonWidget> resetConsumer;

    private SpruceOption extrasOption;
    private boolean extrasEnabled = DeepslateCutting.config.areExtrasEnabled();

    public ConfigScreen(Screen parent) {
        super(Text.translatable("config.deepslatecutting.title"));
        this.parent = parent;

        this.extrasOption = new SpruceBooleanOption("config.deepslatecutting.toggle_deepslate_extras",
                () -> extrasEnabled,
                newValue -> extrasEnabled = newValue,
                Text.translatable("config.deepslatecutting.toggle_deepslate_extras"),
                true);
    }

    @Override
    protected void init() {
        super.init();

        if (this.client == null) {
            return;
        }

        this.list = buildOptionList(Position.of(0, 22), this.width, this.height - 35 - 22);
        this.resetConsumer = button -> this.init(this.client, this.client.getWindow().getScaledWidth(), this.client.getWindow().getScaledHeight());

        this.addDrawableChild(this.list);

        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 155 + 160, this.height - 29), 150, 20, SpruceTexts.GUI_DONE,
                btn -> this.client.setScreen(this.parent)).asVanilla());
    }

    @Override
    public void removed() {
        super.removed();
        try {
            Config.update(DeepslateCutting.configPath, extrasEnabled);
        } catch (IOException e) {
            DeepslateCuttingMain.LOGGER.error("Failed to initialise DeepslateCutting configuration, default values will be used instead");
            DeepslateCuttingMain.LOGGER.error("", e);
        }
    }


    @Override
    public void renderTitle(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 8, 16777215);
    }


    public SpruceOptionListWidget buildOptionList(Position position, int width, int height) {
        var list = new SpruceOptionListWidget(position, width, height);

        list.addSingleOptionEntry(extrasOption);
        return list;
    }
}
