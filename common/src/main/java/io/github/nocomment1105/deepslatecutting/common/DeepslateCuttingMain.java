package io.github.nocomment1105.deepslatecutting.common;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

import static net.minecraft.block.Blocks.*;

public class DeepslateCuttingMain {
    public static final String MOD_ID = "deepslatecutting";
    public static final Logger LOGGER = LogManager.getLogger("Deepslate Cutting");

    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final Registrar<Block> BLOCK_REGISTRY = REGISTRIES.get().get(RegistryKeys.BLOCK);
    public static final Registrar<Item> ITEM_REGISTRY = REGISTRIES.get().get(RegistryKeys.ITEM);

    private static final Identifier DEEPSLATE_SLAB_ID = identifier("deepslate_slab");
    public static RegistrySupplier<Block> DEEPSLATE_SLAB;
    public static RegistrySupplier<Item> DEEPSLATE_SLAB_ITEM;

    private static final Identifier DEEPSLATE_STAIRS_ID = identifier("deepslate_stairs");
    public static RegistrySupplier<Block> DEEPSLATE_STAIRS;
    public static RegistrySupplier<Item> DEEPSLATE_STAIRS_ITEM;

    private static final Identifier DEEPSLATE_WALL_ID = identifier("deepslate_wall");
    public static RegistrySupplier<Block> DEEPSLATE_WALL;
    public static RegistrySupplier<Item> DEEPSLATE_WALL_ITEM;

    public static void init() {
        DEEPSLATE_SLAB = BLOCK_REGISTRY.register(DEEPSLATE_SLAB_ID, () -> new SlabBlock(AbstractBlock.Settings.copy(COBBLED_DEEPSLATE_SLAB).registryKey(RegistryKey.of(RegistryKeys.BLOCK, DEEPSLATE_SLAB_ID))));
        DEEPSLATE_SLAB_ITEM = ITEM_REGISTRY.register(DEEPSLATE_SLAB_ID, () -> new BlockItem(DEEPSLATE_SLAB.get(), new Item.Settings().arch$tab(ItemGroups.BUILDING_BLOCKS).useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, DEEPSLATE_SLAB_ID))));

        DEEPSLATE_STAIRS = BLOCK_REGISTRY.register(DEEPSLATE_STAIRS_ID, () -> new StairsBlock(COBBLED_DEEPSLATE_STAIRS.getDefaultState(), AbstractBlock.Settings.copy(COBBLED_DEEPSLATE_SLAB).registryKey(RegistryKey.of(RegistryKeys.BLOCK, DEEPSLATE_STAIRS_ID))));
        DEEPSLATE_STAIRS_ITEM = ITEM_REGISTRY.register(DEEPSLATE_STAIRS_ID, () -> new BlockItem(DEEPSLATE_STAIRS.get(), new Item.Settings().arch$tab(ItemGroups.BUILDING_BLOCKS).useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, DEEPSLATE_STAIRS_ID))));

        DEEPSLATE_WALL = BLOCK_REGISTRY.register(DEEPSLATE_WALL_ID, () -> new WallBlock(AbstractBlock.Settings.copy(COBBLED_DEEPSLATE_WALL).registryKey(RegistryKey.of(RegistryKeys.BLOCK, DEEPSLATE_WALL_ID))));
        DEEPSLATE_WALL_ITEM = ITEM_REGISTRY.register(DEEPSLATE_WALL_ID, () -> new BlockItem(DEEPSLATE_WALL.get(), new Item.Settings().arch$tab(ItemGroups.BUILDING_BLOCKS).useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, DEEPSLATE_WALL_ID))));
    }

    private static Identifier identifier(String name) {
        return Identifier.of(DeepslateCuttingMain.MOD_ID, name);
    }
}
