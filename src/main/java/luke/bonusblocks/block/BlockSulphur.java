package luke.bonusblocks.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;

public class BlockSulphur extends Block {
    public BlockSulphur(String key, int id, Material material) {
        super(key, id, material);
    }

    public boolean blockActivated() {
        return true;
    }
}
