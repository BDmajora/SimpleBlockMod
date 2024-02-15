package luke.bonusblocks.block;

import luke.bonusblocks.BonusBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.logic.BedDirections;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.enums.EnumSleepStatus;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.ChunkCoordinates;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Iterator;

import static luke.bonusblocks.BonusBlocks.MOD_ID;

public class BlockBedroll extends Block {
    public static final int[][] headBlockToFootBlockMap = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public BlockBedroll(String key, int id) {
        super(key, id, Material.cloth);
        this.setBounds();
    }

    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (world.isClientSide) {
            return true;
        } else {
            int meta = world.getBlockMetadata(x, y, z);
            if (!isBlockFootOfBed(meta)) {
                int dir = getDirectionFromMetadata(meta);
                x += headBlockToFootBlockMap[dir][0];
                z += headBlockToFootBlockMap[dir][1];
                if (world.getBlockId(x, y, z) != this.id) {
                    return true;
                }

                meta = world.getBlockMetadata(x, y, z);
            }

            if (!world.worldType.mayRespawn()) {
                double d = (double)x + 0.5;
                double d1 = (double)y + 0.5;
                double d2 = (double)z + 0.5;
                world.setBlockWithNotify(x, y, z, 0);
                int dir = getDirectionFromMetadata(meta);
                x += headBlockToFootBlockMap[dir][0];
                z += headBlockToFootBlockMap[dir][1];
                if (world.getBlockId(x, y, z) == this.id) {
                    world.setBlockWithNotify(x, y, z, 0);
                    d = (d + (double)x + 0.5) / 2.0;
                    d1 = (d1 + (double)y + 0.5) / 2.0;
                    d2 = (d2 + (double)z + 0.5) / 2.0;
                }

                world.newExplosion((Entity)null, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 5.0F, true, false);
                return true;
            } else {
                if (isBedOccupied(meta)) {
                    EntityPlayer player1 = null;
                    Iterator var8 = world.players.iterator();

                    while(var8.hasNext()) {
                        EntityPlayer p = (EntityPlayer)var8.next();
                        if (p.isPlayerSleeping()) {
                            ChunkCoordinates pos = p.bedChunkCoordinates;
                            if (pos.x == x && pos.y == y && pos.z == z) {
                                player1 = p;
                            }
                        }
                    }

                    if (player1 != null) {
                        player.addChatMessage("bed.occupied");
                        return true;
                    }

                    setBedOccupied(world, x, y, z, false);
                }

                if (player.sleepInBedAt(x, y, z) == EnumSleepStatus.OK) {
                    setBedOccupied(world, x, y, z, true);
                    return true;
                } else {
                    return true;
                }
            }
        }
    }

    public int getBlockTextureFromSideAndMetadata(Side side, int data) {
        if (side == Side.BOTTOM) {
            return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bedrolltop2.png");
        } else {
            int k = getDirectionFromMetadata(data);
            int l = BedDirections.bedDirection[k][side.getId()];
            if (isBlockFootOfBed(data)) {
                if (l == 2) {
                    return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bedrollfront.png");
                } else {
                    return l != 5 && l != 4 ? TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bedrolltop1.png") : TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bedrollside1.png");
                }
            } else if (l == 3) {
                return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bedrollback.png");
            } else {
                return l != 5 && l != 4 ? TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bedrolltop2.png") : TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bedrollside2.png");
            }
        }
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isSolidRender() {
        return false;
    }

    public void setBlockBoundsBasedOnState(World world, int x, int y, int z) {
        this.setBounds();
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        int i1 = world.getBlockMetadata(x, y, z);
        int j1 = getDirectionFromMetadata(i1);
        if (isBlockFootOfBed(i1)) {
            if (world.getBlockId(x - headBlockToFootBlockMap[j1][0], y, z - headBlockToFootBlockMap[j1][1]) != this.id) {
                world.setBlockWithNotify(x, y, z, 0);
            }
        } else if (world.getBlockId(x + headBlockToFootBlockMap[j1][0], y, z + headBlockToFootBlockMap[j1][1]) != this.id) {
            world.setBlockWithNotify(x, y, z, 0);
        }

    }

    private void setBounds() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25f, 1.0F);
    }

    public static int getDirectionFromMetadata(int i) {
        return i & 3;
    }

    public static boolean isBlockFootOfBed(int i) {
        return (i & 8) != 0;
    }

    public static boolean isBedOccupied(int i) {
        return (i & 4) != 0;
    }

    public static void setBedOccupied(World world, int i, int j, int k, boolean flag) {
        int l = world.getBlockMetadata(i, j, k);
        if (flag) {
            l |= 4;
        } else {
            l &= -5;
        }

        world.setBlockMetadataWithNotify(i, j, k, l);
    }

    public static ChunkCoordinates getNearestEmptyChunkCoordinates(World world, int i, int j, int k, int l) {
        int i1 = world.getBlockMetadata(i, j, k);
        int j1 = getDirectionFromMetadata(i1);

        for(int k1 = 0; k1 <= 1; ++k1) {
            int l1 = i - headBlockToFootBlockMap[j1][0] * k1 - 1;
            int i2 = k - headBlockToFootBlockMap[j1][1] * k1 - 1;
            int j2 = l1 + 2;
            int k2 = i2 + 2;

            for(int l2 = l1; l2 <= j2; ++l2) {
                for(int i3 = i2; i3 <= k2; ++i3) {
                    if (world.isBlockNormalCube(l2, j - 1, i3) && world.isAirBlock(l2, j, i3) && world.isAirBlock(l2, j + 1, i3)) {
                        if (l <= 0) {
                            return new ChunkCoordinates(l2, j, i3);
                        }

                        --l;
                    }
                }
            }
        }

        return null;
    }

    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(BonusBlocks.bedrollItem)};
    }

    public int getPistonPushReaction() {
        return 1;
    }
}