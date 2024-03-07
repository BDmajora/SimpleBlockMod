package luke.bonusblocks.block;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;

public class EntitySulphur extends Entity {
    public EntitySulphur(World world) {
        super(world);
    }

    protected void init() {
        // Implementation of the init method.
    }

    public boolean isPickable() {
        return true;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {

    }
}
