package luke.bonusblocks.ModContainer.TerrainApi;

import luke.bonusblocks.BonusBlocks;
import luke.bonusblocks.biomes.ModBiomes;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeShrub;
import useless.terrainapi.api.TerrainAPI;
import useless.terrainapi.generation.nether.api.ChunkDecoratorNetherAPI;
import useless.terrainapi.generation.overworld.OverworldConfig;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;

public class TerrainApiPlugin implements TerrainAPI {
    @Override
    public String getModID() {
        return BonusBlocks.MOD_ID;
    }
    public static final OverworldConfig overworldConfig = ChunkDecoratorOverworldAPI.overworldConfig;
    @Override
    public void onInitialize() {
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_RAINFOREST, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_SWAMPLAND, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_SEASONAL_FOREST, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_FOREST, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_GRASSLANDS, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_OUTBACK, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_SHRUBLAND, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_TAIGA, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_BOREAL_FOREST, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_DESERT, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_PLAINS, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_GLACIER, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_TUNDRA, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_MEADOW, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_BIRCH_FOREST, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_SWAMPLAND_MUDDY, 0);
        overworldConfig.addFlowerDensity(Biomes.OVERWORLD_OUTBACK_GRASSY, 0);

        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_RAINFOREST, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_SWAMPLAND, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_SEASONAL_FOREST, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_FOREST, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_GRASSLANDS, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_OUTBACK, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_SHRUBLAND, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_TAIGA, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_BOREAL_FOREST, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_DESERT, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_PLAINS, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_GLACIER, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_TUNDRA, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_MEADOW, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_BIRCH_FOREST, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_SWAMPLAND_MUDDY, 0);
        overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_OUTBACK_GRASSY, 0);

        overworldConfig.addRandomGrassBlock(ModBiomes.OVERWORLD_OUTBACK_MESA, Block.deadbush);

        ChunkDecoratorNetherAPI.oreFeatures.addManagedOreFeature(getModID(), BonusBlocks.cobblednetherrack, 32, 16, 0.0f, 1.0f, false);
        ChunkDecoratorNetherAPI.oreFeatures.addManagedOreFeature(getModID(), BonusBlocks.netherrack, 64, 8, 0.0f, 1.0f, false);
        ChunkDecoratorNetherAPI.oreFeatures.addManagedOreFeature(getModID(), Block.slate, 32, 4, 0.75f, 1.0f, false);
        ChunkDecoratorNetherAPI.oreFeatures.addManagedOreFeature(getModID(), BonusBlocks.soulslate, 32, 4, 0.0f, 0.50f, false);

        ChunkDecoratorOverworldAPI.randomFeatures.addFeature(new WorldFeatureFlowers(Block.spinifex.id), 4, -1f, 1,
                new Biome[]{ModBiomes.OVERWORLD_OUTBACK_MESA});

        ChunkDecoratorOverworldAPI.randomFeatures.addFeature(new WorldFeatureTreeShrub(Block.cobbleGranite.id, Block.dirtScorchedRich.id), 4, -1f, 1,
                new Biome[]{ModBiomes.OVERWORLD_OUTBACK_MESA});

        ChunkDecoratorOverworldAPI.randomFeatures.addFeature(new WorldFeatureFlowers(Block.deadbush.id), 4, -1f, 1,
                new Biome[]{ModBiomes.OVERWORLD_OUTBACK_MESA});

        ChunkDecoratorOverworldAPI.randomFeatures.addFeatureSurface(new WorldFeatureFlowers(BonusBlocks.flowerLime.id), 4);

        ChunkDecoratorOverworldAPI.randomFeatures.addFeatureSurface(new WorldFeatureFlowers(BonusBlocks.mushroomGray.id), 6);

    }
}
