package io.github.nocomment1105.DeepslateCutting.registry;

import io.github.nocomment1105.DeepslateCutting.blocks.DeepslateCuttingStairsBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.github.nocomment1105.DeepslateCutting.deepslatecutting.MOD_ID;
import static net.minecraft.block.Blocks.COBBLED_DEEPSLATE_STAIRS;

public class ModBlocks {
    public static final Block DEEPSLATE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE,
            MapColor.DEEPSLATE_GRAY).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool()
            .strength(3.5f, 6).sounds(BlockSoundGroup.DEEPSLATE));
    public static final Block DEEPSLATE_STAIRS = new DeepslateCuttingStairsBlock(COBBLED_DEEPSLATE_STAIRS
            .getDefaultState(), FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).breakByTool(FabricToolTags.PICKAXES, 0)
                    .requiresTool().strength(3.5f,6).sounds(BlockSoundGroup.DEEPSLATE));
    public static final Block DEEPSLATE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY)
            .breakByTool(FabricToolTags.PICKAXES,0).requiresTool().strength(3.5f,6)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "deepslate_slab"), DEEPSLATE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "deepslate_stairs"), DEEPSLATE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "deepslate_wall"), DEEPSLATE_WALL);
    }
}
