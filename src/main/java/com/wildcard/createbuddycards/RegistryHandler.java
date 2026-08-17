package com.wildcard.createbuddycards;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.registry.CreateRegistries;
import com.simibubi.create.content.decoration.palettes.AllPaletteBlocks;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.wildcard.buddycards.Buddycards;
import com.wildcard.buddycards.block.BuddycardBoosterBoxBlock;
import com.wildcard.buddycards.block.CardStandBlock;
import com.wildcard.buddycards.block.PlaymatBlock;
import com.wildcard.buddycards.core.BuddycardSet;
import com.wildcard.buddycards.item.*;
import com.wildcard.buddycards.registries.BuddycardsBlocks;
import com.wildcard.buddycards.registries.BuddycardsItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryHandler {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, CreateBuddycards.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, CreateBuddycards.MOD_ID);

    public static void init() {
        BOOSTER_BOX = BLOCKS.register("buddycard_booster_box_create", () -> new BuddycardBoosterBoxBlock(BuddycardsItems.DEFAULT_BUDDYCARD_REQUIREMENT, BuddycardsBlocks.BOOSTER_BOX_PROPERTIES));
        PLAYMAT = registerPlaymat("playmat_create", () -> new PlaymatBlock(BuddycardsBlocks.PLAYMAT_PROPERTIES));

        PACK = ITEMS.register("buddycard_pack_create", () -> new BuddycardSetPackItem(CREATE_SET, 4, 1, BuddycardsItems.DEFAULT_RARITY_WEIGHTS, BuddycardsItems.DEFAULT_PACK_PROPERTIES));
        BINDER = ITEMS.register("buddycard_binder_create", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, CREATE_SET, new ResourceLocation(Buddycards.MOD_ID, "textures/gui/buddycard_binder_create.png"), false));
        LARGE_BINDER = ITEMS.register("large_buddycard_binder_create", () -> new BuddycardBinderItem(BuddycardsItems.DEFAULT_BINDER_PROPERTIES, CREATE_SET, new ResourceLocation(Buddycards.MOD_ID, "textures/gui/large_buddycard_binder_create.png"), true));
        MEDAL = ITEMS.register("buddysteel_medal_create", () -> new BuddysteelSetMedalItem(MedalTypes.CREATE_SET, CREATE_SET, BuddycardsItems.DEFAULT_CURIO_PROPERTIES));
        LUMINIS_MEDAL = ITEMS.register("luminis_medal_create", () -> new LuminisSetMedalItem(MedalTypes.CREATE_SET, CREATE_SET, BuddycardsItems.DEFAULT_CURIO_PROPERTIES));
        ZYLEX_MEDAL = ITEMS.register("zylex_medal_create", () -> new ZylexSetMedalItem(MedalTypes.CREATE_SET, CREATE_SET, BuddycardsItems.DEFAULT_CURIO_PROPERTIES));

        BOOSTER_BOX_ITEM = ITEMS.register("buddycard_booster_box_create", () -> new BuddycardBoosterBoxItem(BOOSTER_BOX.get(), PACK, BuddycardsItems.DEFAULT_UNCOMMON_PROPERTIES));
        PLAYMAT_ITEM = ITEMS.register("playmat_create", () -> new SetBasedBlockItem(PLAYMAT.get(), BuddycardsItems.DEFAULT_PROPERTIES, CREATE_SET));

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

        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final BuddycardSet CREATE_SET = new BuddycardSet("create");

    public static final BuddycardsItems.BuddycardRequirement CREATE_REQUIREMENT = () -> ModList.get().isLoaded("create");

    public static RegistryObject<Block> BOOSTER_BOX;
    public static RegistryObject<PlaymatBlock> PLAYMAT;

    public static RegistryObject<BuddycardPackItem> PACK;
    public static RegistryObject<BuddycardBinderItem> BINDER;
    public static RegistryObject<BuddycardBinderItem> LARGE_BINDER;
    public static RegistryObject<BuddysteelSetMedalItem> MEDAL;
    public static RegistryObject<LuminisSetMedalItem> LUMINIS_MEDAL;
    public static RegistryObject<ZylexSetMedalItem> ZYLEX_MEDAL;

    public static RegistryObject<BuddycardBoosterBoxItem> BOOSTER_BOX_ITEM;
    public static RegistryObject<BlockItem> PLAYMAT_ITEM;

    public static RegistryObject<Item> SHREDDED_BUDDYCARD;
    public static RegistryObject<Item> RECYCLED_BUDDYCARD;
    public static RegistryObject<Item> UNFINISHED_PACK;

    public static RegistryObject<CardStandBlock> ASURINE_CARD_STAND;
    public static RegistryObject<CardStandBlock> CRIMSITE_CARD_STAND;
    public static RegistryObject<CardStandBlock> LIMESTONE_CARD_STAND;
    public static RegistryObject<CardStandBlock> OCHRUM_CARD_STAND;
    public static RegistryObject<CardStandBlock> SCORIA_CARD_STAND;
    public static RegistryObject<CardStandBlock> SCORCHIA_CARD_STAND;
    public static RegistryObject<CardStandBlock> VERIDIUM_CARD_STAND;


    public static void registerCards(int startValue, int amount, Rarity rarity, BuddycardsItems.BuddycardRequirement requirement) {
        for (int i = startValue; i < amount + startValue; i++) {
            int finalI = i;
            ITEMS.register("buddycard_create" + finalI, () -> new BuddycardItem(requirement, CREATE_SET, finalI, rarity, BuddycardsItems.DEFAULT_CARD_PROPERTIES, 2, 1, BuddycardsItems.DEFAULT_NO_ABILITIES));
        }
    }

    public static RegistryObject<PlaymatBlock> registerPlaymat(String id, Supplier<PlaymatBlock> supplier) {
        RegistryObject<PlaymatBlock> playmat = BLOCKS.register(id, supplier);
        BuddycardsBlocks.PLAYMAT_BLOCKS.add(playmat);
        return playmat;
    }

    public static RegistryObject<CardStandBlock> registerStand(String id, Supplier<CardStandBlock> supplier) {
        RegistryObject<CardStandBlock> stand = BLOCKS.register(id, supplier);
        BuddycardsBlocks.STAND_BLOCKS.add(stand);
        return stand;
    }
}
