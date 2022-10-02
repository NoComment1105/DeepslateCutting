package io.github.nocomment1105.deepslatecutting.config;

import com.terraformersmc.modmenu.config.option.ConfigOptionStorage;
import com.terraformersmc.modmenu.config.option.OptionConvertable;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;

/**
 * This class was utilised and modified from ModMenu, with verbal permission from Prospector.
 */
public class BooleanConfigOption implements OptionConvertable {
    private final String key, translationKey;
    private final boolean defaultValue;
    private final Text enabledText;
    private final Text disabledText;

    public BooleanConfigOption(String key, boolean defaultValue, String enabledKey, String disabledKey) {
        ConfigOptionStorage.setBoolean(key, defaultValue);
        this.key = key;
        this.translationKey = "config.deepslatecutting."+ key;
        this.defaultValue = defaultValue;
        this.enabledText = Text.translatable(translationKey + "." + enabledKey);
        this.disabledText = Text.translatable(translationKey + "." + disabledKey);
    }

    public BooleanConfigOption(String key, boolean defaultValue) {
        this(key, defaultValue, "true", "false");
    }

    public boolean getValue() {
        return ConfigOptionStorage.getBoolean(key);
    }

    @Override
    public Option<Boolean> asOption() {
        if (enabledText != null && disabledText != null) {
            return new Option<>(translationKey, Option.emptyTooltip(),
                    (text, value) -> value ? enabledText : disabledText, Option.BOOLEAN_VALUES, getValue(),
                    newValue -> ConfigOptionStorage.setBoolean(key, newValue));
        }
        return Option.ofBoolean(translationKey, getValue(), (value) -> ConfigOptionStorage.setBoolean(key, value));
    }
}
