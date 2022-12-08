package io.github.nocomment1105.deepslatecutting.registry;

import io.github.nocomment1105.deepslatecutting.DeepslateCuttingMain;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.*;

@SuppressWarnings("unused")
public class ModRegistry {
    public static final Block DEEPSLATE_SLAB = registerBlock(
            "deepslate_slab", new SlabBlock(
                    AbstractBlock.Settings.copy(COBBLED_DEEPSLATE_SLAB)
            )
    );
    public static final Block DEEPSLATE_STAIRS = registerBlock(
            "deepslate_stairs", new StairBlock(
                    COBBLED_DEEPSLATE_STAIRS.getDefaultState(),
                    AbstractBlock.Settings.copy(COBBLED_DEEPSLATE_STAIRS)
            )
    );
    public static final Block DEEPSLATE_WALL = registerBlock(
            "deepslate_wall", new WallBlock(
                    AbstractBlock.Settings.copy(COBBLED_DEEPSLATE_WALL)
            )
    );


    public static final BlockItem DEEPSLATE_SLAB_ITEM = registerItem(
            "deepslate_slab", new BlockItem(
                    ModRegistry.DEEPSLATE_SLAB,
                    new Item.Settings()
            )
    );
    public static final BlockItem DEEPSLATE_STAIRS_ITEM = registerItem(
            "deepslate_stairs", new BlockItem(
                    ModRegistry.DEEPSLATE_STAIRS,
                    new Item.Settings()
            )
    );
    public static final BlockItem DEEPSLATE_WALL_ITEM = registerItem(
            "deepslate_wall", new BlockItem(
                    ModRegistry.DEEPSLATE_WALL,
                    new Item.Settings()
            )
    );


    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(DeepslateCuttingMain.MOD_ID, name), block);
    }

    private static BlockItem registerItem(String name, BlockItem item) {
        return Registry.register(Registries.ITEM, new Identifier(DeepslateCuttingMain.MOD_ID, name), item);
    }

    public static void init() {
    }

    public static class StairBlock extends StairsBlock {
        public StairBlock(BlockState blockState, Settings settings) {
            super(blockState, settings);
        }
    }
}
