package io.github.nocomment1105.deepslatecutting.mixin;

import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

import static io.github.nocomment1105.deepslatecutting.DeepslateCutting.*;

@Mixin(RecipeManager.class)
public abstract class MixinRecipeManager {
    @Inject(method = "apply", at = @At("HEAD"))
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo callbackInfo) {
        Iterator<Map.Entry<Identifier, JsonElement>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Identifier id = iterator.next().getKey();

            if (id.getNamespace().equals(MOD_ID) && id.getPath().startsWith("extras") && !CONFIG.getSmoothStuff()) {
                iterator.remove();
            }
        }
    }
}
