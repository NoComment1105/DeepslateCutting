package me.nocomment1105.DeepslateCutting.registry;

import me.nocomment1105.DeepslateCutting.blocks.DeepslateCuttingStairsBlock;
import me.nocomment1105.DeepslateCutting.deepslatecutting;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import static net.minecraft.block.Blocks.*;

public class ModBlocks {
    public static final Block DEEPSLATE_SLAB = new SlabBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).requiresTool().strength(3.5f, 6).sounds(BlockSoundGroup.DEEPSLATE));
    public static final Block DEEPSLATE_STAIRS = new DeepslateCuttingStairsBlock(DEEPSLATE.getDefaultState(), AbstractBlock.Settings.copy(DEEPSLATE));
    public static final Block DEEPSLATE_WALL = new WallBlock(AbstractBlock.Settings.copy(DEEPSLATE_SLAB));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(deepslatecutting.MOD_ID, "deepslate_slab"), DEEPSLATE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(deepslatecutting.MOD_ID, "deepslate_stairs"), DEEPSLATE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(deepslatecutting.MOD_ID, "deepslate_wall"), DEEPSLATE_WALL);
    }
}
