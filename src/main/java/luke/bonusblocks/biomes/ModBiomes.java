package luke.bonusblocks.biomes;

import luke.bonusblocks.BonusBlocks;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;

public abstract class ModBiomes {
    public static final Biome OVERWORLD_OVERGROWN_MEADOW =
            Biomes.register(BonusBlocks.MOD_ID+":overworld.overgrown.meadow",
                    new BiomeOvergrown());

    public static final Biome OVERWORLD_OUTBACK_MESA =
            Biomes.register(BonusBlocks.MOD_ID+":overworld.outback.mesa",
                    new BiomeMesa());
}