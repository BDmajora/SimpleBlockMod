package luke.bonusblocks.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFlower;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemDye.class, remap = false, priority = 999)
public abstract class ItemDyeMixin extends Item {
    public ItemDyeMixin(String name, int id) {
        super(name, id);
    }

}
