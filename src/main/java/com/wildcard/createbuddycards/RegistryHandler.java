package com.wildcard.createbuddycards;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.wildcard.buddycards.block.BuddycardBoosterBoxBlock;
import com.wildcard.buddycards.block.CardStandBlock;
import com.wildcard.buddycards.core.BuddycardSet;
import com.wildcard.buddycards.item.*;
import com.wildcard.buddycards.registries.BuddycardsBlocks;
import com.wildcard.buddycards.registries.BuddycardsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RegistryHandler {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateBuddycards.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateBuddycards.MOD_ID);

    public static void registerAll(IEventBus eventBus) {
        BOOSTER_BOX = BLOCKS.register("buddycard_booster_box_create", () -> new BuddycardBoosterBoxBlock(BuddycardsItems.DEFAULT_BUDDYCARD_REQUIREMENT, BuddycardsBlocks.BOOSTER_BOX_PROPERTIES));

        PACK = ITEMS.register("buddycard_pack_create", () -> new BuddycardSetPackItem(CREATE_SET, 4, 1, BuddycardsItems.DEFAULT_RARITY_WEIGHTS, BuddycardsItems.DEFAULT_PACK_PROPERTIES));
        BINDER = ITEMS.register("buddycard_binder_create", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, CREATE_SET, ResourceLocation.fromNamespaceAndPath(CreateBuddycards.MOD_ID, "textures/gui/buddycard_binder_create.png"), false));
        LARGE_BINDER = ITEMS.register("large_buddycard_binder_create", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, CREATE_SET, ResourceLocation.fromNamespaceAndPath(CreateBuddycards.MOD_ID, "textures/gui/large_buddycard_binder_create.png"), true));
        MEDAL = ITEMS.register("buddysteel_medal_create", () -> new BuddysteelSetMedalItem(MedalTypes.CREATE_SET, CREATE_SET, BuddycardsItems.DEFAULT_CURIO_PROPERTIES));

        BOOSTER_BOX_ITEM = ITEMS.register("buddycard_booster_box_create", () -> new BuddycardBoosterBoxItem(BOOSTER_BOX.get(), PACK, BuddycardsItems.DEFAULT_UNCOMMON_PROPERTIES));

        SHREDDED_BUDDYCARD = ITEMS.register("shredded_buddycard", () -> new Item(BuddycardsItems.DEFAULT_PROPERTIES));
        RECYCLED_BUDDYCARD = ITEMS.register("recycled_buddycard", () -> new Item(BuddycardsItems.DEFAULT_PROPERTIES));
        UNFINISHED_PACK = ITEMS.register("unfinished_buddycard_pack", () -> new SequencedAssemblyItem(new Item.Properties()));

        registerCards(1, 12, Rarity.COMMON, CREATE_REQUIREMENT);
        registerCards(13, 9, Rarity.UNCOMMON, CREATE_REQUIREMENT);
        registerCards(22, 4, Rarity.RARE, CREATE_REQUIREMENT);
        registerCards(26, 2, Rarity.EPIC, CREATE_REQUIREMENT);
        registerCards(28, 4, Rarity.COMMON, CREATE_REQUIREMENT);
        registerCards(32, 3, Rarity.UNCOMMON, CREATE_REQUIREMENT);
        registerCards(35, 2, Rarity.RARE, CREATE_REQUIREMENT);

        ASURINE_CARD_STAND = registerStand("asurine_card_stand", () -> new CardStandBlock(BlockBehaviour.Properties.of().destroyTime(1.25f).mapColor(MapColor.COLOR_BLUE)));
        CRIMSITE_CARD_STAND = registerStand("crimsite_card_stand", () -> new CardStandBlock(BlockBehaviour.Properties.of().destroyTime(1.25f).mapColor(MapColor.COLOR_RED)));
        LIMESTONE_CARD_STAND = registerStand("limestone_card_stand", () -> new CardStandBlock(BlockBehaviour.Properties.of().destroyTime(1.25f).mapColor(MapColor.SAND)));
        OCHRUM_CARD_STAND = registerStand("ochrum_card_stand", () -> new CardStandBlock(BlockBehaviour.Properties.of().destroyTime(1.25f).mapColor(MapColor.TERRACOTTA_YELLOW)));
        SCORIA_CARD_STAND = registerStand("scoria_card_stand", () -> new CardStandBlock(BlockBehaviour.Properties.of().destroyTime(1.25f).mapColor(MapColor.COLOR_BROWN)));
        SCORCHIA_CARD_STAND = registerStand("scorchia_card_stand", () -> new CardStandBlock(BlockBehaviour.Properties.of().destroyTime(1.25f).mapColor(MapColor.TERRACOTTA_GRAY)));
        VERIDIUM_CARD_STAND = registerStand("veridium_card_stand", () -> new CardStandBlock(BlockBehaviour.Properties.of().destroyTime(1.25f).mapColor(MapColor.WARPED_NYLIUM)));

        ITEMS.register("asurine_card_stand", () -> new BlockItem(ASURINE_CARD_STAND.get(), BuddycardsItems.DEFAULT_PROPERTIES));
        ITEMS.register("crimsite_card_stand", () -> new BlockItem(CRIMSITE_CARD_STAND.get(), BuddycardsItems.DEFAULT_PROPERTIES));
        ITEMS.register("limestone_card_stand", () -> new BlockItem(LIMESTONE_CARD_STAND.get(), BuddycardsItems.DEFAULT_PROPERTIES));
        ITEMS.register("ochrum_card_stand", () -> new BlockItem(OCHRUM_CARD_STAND.get(), BuddycardsItems.DEFAULT_PROPERTIES));
        ITEMS.register("scoria_card_stand", () -> new BlockItem(SCORIA_CARD_STAND.get(), BuddycardsItems.DEFAULT_PROPERTIES));
        ITEMS.register("scorchia_card_stand", () -> new BlockItem(SCORCHIA_CARD_STAND.get(), BuddycardsItems.DEFAULT_PROPERTIES));
        ITEMS.register("veridium_card_stand", () -> new BlockItem(VERIDIUM_CARD_STAND.get(), BuddycardsItems.DEFAULT_PROPERTIES));

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

    public static final BuddycardSet CREATE_SET = new BuddycardSet("create");

    public static final BuddycardsItems.BuddycardRequirement CREATE_REQUIREMENT = () -> ModList.get().isLoaded("create");

    public static DeferredBlock<BuddycardBoosterBoxBlock> BOOSTER_BOX;

    public static DeferredItem<BuddycardPackItem> PACK;
    public static DeferredItem<BuddycardBinderItem> BINDER;
    public static DeferredItem<BuddycardBinderItem> LARGE_BINDER;
    public static DeferredItem<BuddysteelSetMedalItem> MEDAL;

    public static DeferredItem<BuddycardBoosterBoxItem> BOOSTER_BOX_ITEM;

    public static DeferredItem<Item> SHREDDED_BUDDYCARD;
    public static DeferredItem<Item> RECYCLED_BUDDYCARD;
    public static DeferredItem<SequencedAssemblyItem> UNFINISHED_PACK;

    public static DeferredBlock<CardStandBlock> ASURINE_CARD_STAND;
    public static DeferredBlock<CardStandBlock> CRIMSITE_CARD_STAND;
    public static DeferredBlock<CardStandBlock> LIMESTONE_CARD_STAND;
    public static DeferredBlock<CardStandBlock> OCHRUM_CARD_STAND;
    public static DeferredBlock<CardStandBlock> SCORIA_CARD_STAND;
    public static DeferredBlock<CardStandBlock> SCORCHIA_CARD_STAND;
    public static DeferredBlock<CardStandBlock> VERIDIUM_CARD_STAND;


    public static void registerCards(int startValue, int amount, Rarity rarity, BuddycardsItems.BuddycardRequirement requirement) {
        for (int i = startValue; i < amount + startValue; i++) {
            int finalI = i;
            ITEMS.register("buddycard_create" + i, () -> new BuddycardItem(requirement, CREATE_SET, finalI, rarity));
        }
    }

    public static DeferredBlock<CardStandBlock> registerStand(String id, Supplier<CardStandBlock> supplier) {
        DeferredBlock<CardStandBlock> stand = BLOCKS.register(id, supplier);
        BuddycardsBlocks.STAND_BLOCKS.add(stand);
        return stand;
    }
}
