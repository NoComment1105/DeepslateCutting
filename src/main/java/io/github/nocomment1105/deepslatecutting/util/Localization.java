package io.github.nocomment1105.deepslatecutting.util;

import io.github.nocomment1105.deepslatecutting.DeepslateCutting;
import net.minecraft.text.Text;

/**
 * This code was utilized from the <a href="https://github.com/juliand665/Dynamic-FPS">DynamicFPS</a> mod.
 */
public final class Localization {
    /**
     * e.g. keyString("title", "config") -> "title.deepslatecutting.config")
     */
    public static String translationKey(String domain, String path) {
        return domain + "." + DeepslateCutting.MOD_ID + "." + path;
    }

    public static Text localized(String domain, String path, Object... args) {
        return Text.createFormatted(translationKey(domain, path), args);
    }
}
