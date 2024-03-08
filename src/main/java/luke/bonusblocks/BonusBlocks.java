package luke.bonusblocks;

import luke.bonusblocks.block.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.block.color.BlockColorGrass;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.render.entity.FallingSandRenderer;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.*;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.RecipeEntrypoint;
import useless.dragonfly.helper.ModelHelper;
import useless.dragonfly.model.block.BlockModelDragonFly;

import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import static net.minecraft.core.block.BlockMoss.stoneToMossMap;


public class BonusBlocks implements ModInitializer, RecipeEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "bonusblocks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ConfigHandler config;
    private static int blockID;
    private static int itemID;
    static {
        Properties properties = new Properties();
        properties.put("blockID", "1500");
        properties.put("itemID", "16750");
        config = new ConfigHandler(MOD_ID, properties);
        blockID = config.getInt("blockID");
        itemID = config.getInt("itemID");
    }

    @Override
    public void onInitialize() {
        LOGGER.info("BonusBlocks initialized.");
/*
        LookupFuelFurnace.instance.addFuelEntry(barkOak.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkOakMossy.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkPine.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkBirch.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkCherry.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkEucalyptus.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkMaple.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkJacaranda.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkScorched.id, 300);
        LookupFuelFurnace.instance.addFuelEntry(barkShrub.id, 300);
*/
    }

    public void onRecipesReady() {
/*
        RecipeBuilderShaped templateLogtoBark = new RecipeBuilderShaped(MOD_ID, "XX", "XX");
        templateLogtoBark.addInput('X', Block.logOak).create("barkOak", new ItemStack(BonusBlocks.barkOak, 4));
        templateLogtoBark.addInput('X', Block.logOakMossy).create("barkOakMossy", new ItemStack(BonusBlocks.barkOakMossy, 4));
        templateLogtoBark.addInput('X', Block.logBirch).create("barkBirch", new ItemStack(BonusBlocks.barkBirch, 4));
        templateLogtoBark.addInput('X', Block.logPine).create("barkPine", new ItemStack(BonusBlocks.barkPine, 4));
        templateLogtoBark.addInput('X', Block.logCherry).create("barkCherry", new ItemStack(BonusBlocks.barkCherry, 4));
        templateLogtoBark.addInput('X', Block.logEucalyptus).create("barkEucalyptus", new ItemStack(BonusBlocks.barkEucalyptus, 4));
        templateLogtoBark.addInput('X', BonusBlocks.logMaple).create("barkMaple", new ItemStack(BonusBlocks.barkMaple, 4));
        templateLogtoBark.addInput('X', BonusBlocks.logJacaranda).create("barkJacaranda", new ItemStack(BonusBlocks.barkJacaranda, 4));
        templateLogtoBark.addInput('X', BonusBlocks.logScorched).create("barkScorched", new ItemStack(BonusBlocks.barkScorched, 4));
        templateLogtoBark.addInput('X', BonusBlocks.logShrub).create("barkShrub", new ItemStack(BonusBlocks.barkShrub, 4));
        templateLogtoBark.addInput('X', BonusBlocks.logCacao).create("logCacao", new ItemStack(BonusBlocks.barkCacao, 4));
*/

        RecipeBuilderShaped templateBlocktoItem = new RecipeBuilderShaped(MOD_ID, "X");

        RecipeBuilderShaped templateFlowertoDye = new RecipeBuilderShaped(MOD_ID, "X");

        RecipeBuilderShaped templateMushroomToBlock = new RecipeBuilderShaped(MOD_ID, "XX", "XX");

        RecipeBuilderShaped templateBricks = new RecipeBuilderShaped(MOD_ID, "XX", "XX");

        RecipeBuilderShaped templateOvergrown = new RecipeBuilderShaped(MOD_ID, "X", "X");

        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("marble_pillar");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("green_dye_white_dye_to_lime_dye");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("pebbles_to_granite");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("bed");

        RecipeBuilder.ModifyBlastFurnace("minecraft").removeRecipe("cobble_basalt_to_olivine");
        RecipeBuilder.ModifyBlastFurnace("minecraft").removeRecipe("cobble_stone_to_slate");

        RecipeBuilder.Shapeless(MOD_ID)
                .addInput(new ItemStack(Item.dye, 1, 2))
                .addInput(new ItemStack(Item.dye, 1, 11))
                .create("green_dye_white_dye_to_lime_dye", new ItemStack(Item.dye, 2, 10));

        RecipeBuilderShaped templatePillar = new RecipeBuilderShaped(MOD_ID, "X", "X", "X");
        templatePillar.addInput('X', Block.marble).create("marble_pillar", new ItemStack(Block.pillarMarble, 3));

        RecipeBuilder.Shaped(MOD_ID, "PI", "IP")
                .addInput('P', Item.ammoPebble)
                .addInput('I', Block.ice)
                .create("pebbles_to_permafrost", new ItemStack(Block.cobblePermafrost, 2));

        Registries.ITEM_GROUPS.getItem("minecraft:grasses").add(Block.grassScorched.getDefaultStack());

//        Registries.ITEM_GROUPS.register("bonusblocks:bark", Registries.stackListOf(BonusBlocks.barkBirch,
//                BonusBlocks.barkCacao,
//                BonusBlocks.barkCherry,
//                BonusBlocks.barkEucalyptus,
//                BonusBlocks.barkJacaranda,
//                BonusBlocks.barkMaple,
//                BonusBlocks.barkOak,
//                BonusBlocks.barkOakMossy,
//                BonusBlocks.barkPine,
//                BonusBlocks.barkScorched,
//                BonusBlocks.barkShrub));

        RecipeBuilderShaped templateCarvedSlab = new RecipeBuilderShaped(MOD_ID, "X", "X");
//        templateCarvedSlab.addInput('X', Block.slabCapstoneMarble).create("marble_capstone_carved", new ItemStack(BonusBlocks.capstoneMarbleCarved, 1));
//        templateCarvedSlab.addInput('X', BonusBlocks.capstoneMarbleCarved).create("marble_capstone", new ItemStack(Block.capstoneMarble, 2));
        templateCarvedSlab.addInput('X', Block.slabCapstoneMarble).create("marble_capstone_carved", new ItemStack(Block.capstoneMarble, 1));

        templateCarvedSlab.addInput('X', Block.slabBasaltPolished).create("basalt_carved", new ItemStack(Block.basaltCarved, 1));
        templateCarvedSlab.addInput('X', Block.slabStonePolished).create("stone_carved", new ItemStack(Block.stoneCarved, 1));
        templateCarvedSlab.addInput('X', Block.slabLimestonePolished).create("limestone_carved", new ItemStack(Block.limestoneCarved, 1));
        templateCarvedSlab.addInput('X', Block.slabGranitePolished).create("granite_carved", new ItemStack(Block.graniteCarved, 1));

        RecipeBuilderShaped templateSlab = new RecipeBuilderShaped(MOD_ID, "XXX");

        RecipeBuilderShaped templateStairs = new RecipeBuilderShaped(MOD_ID, "X ", "XX ", "XXX");
/*
        RecipeBuilder.Furnace(MOD_ID)
                .setInput("bonusblocks:bark")
                .create("bark_charcoal", new ItemStack(Item.coal, 1, 1));
*/

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.cobbleStone)
                .create("cobble_stone_to_stone", Block.stone.getDefaultStack());

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.cobbleBasalt)
                .create("cobble_basalt_to_basalt", Block.basalt.getDefaultStack());

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.cobbleGranite)
                .create("cobble_granite_to_granite", Block.granite.getDefaultStack());

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.limestone)
                .create("limestone_to_marble", Block.marble.getDefaultStack());

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.stone)
                .create("stone_to_slate", Block.slate.getDefaultStack());

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.basalt)
                .create("basalt_to_olivine", Item.olivine.getDefaultStack());

    }

    @Override
    public void beforeClientStart() {
        EntityHelper.Client.assignEntityRenderer(EntitySulphur.class, new FallingSandRenderer());
    }

    @Override
    public void afterClientStart() {
    }
    private static HashMap<String, String> borderMaterialMap = new HashMap<>();
    public static void addBorder(ItemStack stack, String imagePath){
        borderMaterialMap.put(stack.getItemName(), imagePath);
    }
    public static String getBorder(ItemStack stack){
        return borderMaterialMap.get(stack.getItemName());
    }
    static {
        addBorder(Item.ingotIron.getDefaultStack(), "/assets/bonusblocks/art/border_iron.png");
        addBorder(Item.ingotGold.getDefaultStack(), "/assets/bonusblocks/art/border_gold.png");
        addBorder(Item.ingotSteel.getDefaultStack(), "/assets/bonusblocks/art/border_steel.png");
        addBorder(new ItemStack(Item.dye, 1, 4), "/assets/bonusblocks/art/border_lapis.png");
        addBorder(Item.diamond.getDefaultStack(), "/assets/bonusblocks/art/border_diamond.png");
        addBorder(Item.dustRedstone.getDefaultStack(), "/assets/bonusblocks/art/border_redstone.png");
        addBorder(Item.olivine.getDefaultStack(), "/assets/bonusblocks/art/border_olivine.png");
    }
}