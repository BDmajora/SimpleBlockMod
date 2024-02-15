package luke.bonusblocks.block;

import net.minecraft.core.block.BlockPressurePlate;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

import java.util.List;
import java.util.Random;

public class BlockCopperPressurePlate extends BlockPressurePlate {
    protected int ticks;
    protected int transformID;
    public BlockCopperPressurePlate(String key, int id, Material material, int transformID) {
        super(key, id, null, material);
        this.transformID = transformID;
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (transformID != -1 && world.getBlockMetadata(x, y, z) == 0) {
            ++this.ticks;
            if (this.ticks == 200) {
                world.setBlockAndMetadataWithNotify(x, y, z, transformID, 0);
                this.ticks = 0;
            }
        }
        if (world.isClientSide) {
            return;
        }
        if (world.getBlockMetadata(x, y, z) != 0) {
            this.updateState(world, x, y, z);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (world.isClientSide) {
            return;
        }
        if (world.getBlockMetadata(x, y, z) != 1) {
            this.updateState(world, x, y, z);
        }
    }

    private void updateState(World world, int x, int y, int z) {
        boolean isPressed = world.getBlockMetadata(x, y, z) == 1;
        boolean isSteppedOn = false;
        float f = 0.125f;
        List<Entity> list = world.getEntitiesWithinAABB(EntityItem.class, AABB.getBoundingBoxFromPool((float)x + f, y, (float)z + f, (float)(x + 1) - f, (double)y + 0.25, (float)(z + 1) - f));
        if (list != null && !list.isEmpty()) {
            isSteppedOn = true;
        }
        if (isSteppedOn && !isPressed) {
            world.setBlockMetadataWithNotify(x, y, z, 1);
            world.notifyBlocksOfNeighborChange(x, y, z, this.id);
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this.id);
            world.markBlocksDirty(x, y, z, x, y, z);
            world.playSoundEffect(SoundType.WORLD_SOUNDS, (double)x + 0.5, (double)y + 0.1, (double)z + 0.5, "random.click", 0.3f, 0.6f);
        }
        if (!isSteppedOn && isPressed) {
            world.setBlockMetadataWithNotify(x, y, z, 0);
            world.notifyBlocksOfNeighborChange(x, y, z, this.id);
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this.id);
            world.markBlocksDirty(x, y, z, x, y, z);
            world.playSoundEffect(SoundType.WORLD_SOUNDS, (double)x + 0.5, (double)y + 0.1, (double)z + 0.5, "random.click", 0.3f, 0.5f);
        }
        if (isSteppedOn) {
            world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
        }
    }
}
