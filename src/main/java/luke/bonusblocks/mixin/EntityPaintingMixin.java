package luke.bonusblocks.mixin;

import com.mojang.nbt.CompoundTag;
import luke.bonusblocks.BonusBlocks;
import luke.bonusblocks.IPaintingExtras;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityPainting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPainting.class, remap = false)
public abstract class EntityPaintingMixin extends Entity implements IPaintingExtras {
    public EntityPaintingMixin(World world) {
        super(world);
    }
    @Inject(method = "init", at = @At("HEAD"))
    private void init(CallbackInfo ci){
        entityData.define(1, -1);
        entityData.define(2, -1);
    }
    @Override
    public boolean interact(EntityPlayer entityplayer) {
        if (entityplayer.getHeldItem() != null){
            ItemStack stack = entityplayer.getHeldItem();
            if (BonusBlocks.getBorder(stack) == null) return false;
            setStack(new ItemStack(stack.getItem(), 1, stack.getMetadata()));
            stack.consumeItem(entityplayer);
            if (stack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            }
            return true;
        } else if (bonusblocks$getStack() != null){
            entityplayer.inventory.insertItem(bonusblocks$getStack(), true);
            setStack(null);
            return true;
        }
        return super.interact(entityplayer);
    }

    @Override
    public ItemStack bonusblocks$getStack() {
        if (entityData.getInt(1) == -1 || entityData.getInt(2) == -1){
            return null;
        }
        return new ItemStack(entityData.getInt(1), 1, entityData.getInt(2));
    }
    @Unique
    public void setStack(ItemStack stack){
        if (stack != null){
            entityData.set(1, stack.itemID);
            entityData.set(2, stack.getMetadata());
        } else {
            entityData.set(1, -1);
            entityData.set(2, -1);
        }
    }
    @Inject(method = "addAdditionalSaveData(Lcom/mojang/nbt/CompoundTag;)V", at = @At("TAIL"))
    private void saveExtras(CompoundTag tag, CallbackInfo ci){
        ItemStack stack = bonusblocks$getStack();
        if (stack != null){
            tag.putBoolean("itemExists", true);
            tag.putInt("itemID", stack.itemID);
            tag.putInt("itemMeta", stack.getMetadata());
        } else {
            tag.putBoolean("itemExists", false);
        }
    }
    @Inject(method = "readAdditionalSaveData(Lcom/mojang/nbt/CompoundTag;)V", at = @At("TAIL"))
    private void loadExtras(CompoundTag tag, CallbackInfo ci){
        if (tag.containsKey("itemExists") && tag.getBoolean("itemExists")){
            int id = tag.getInteger("itemID");
            int meta = tag.getInteger("itemMeta");
            setStack(new ItemStack(id, 1, meta));
        } else {
            setStack(null);
        }
    }
}
