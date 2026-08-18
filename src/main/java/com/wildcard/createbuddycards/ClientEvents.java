package com.wildcard.createbuddycards;

import com.wildcard.buddycards.Buddycards;
import com.wildcard.buddycards.client.renderer.MedalRenderer;
import com.wildcard.buddycards.item.BuddycardItem;
import com.wildcard.buddycards.registries.BuddycardsItems;
import com.wildcard.buddycards.registries.BuddycardsMisc;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(value = Buddycards.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Buddycards.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        setupRenderers();
    }

    @SubscribeEvent
    public static void creativeTabSetup(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(BuddycardsMisc.MAIN_TAB.getKey())) {
            for (DeferredHolder<Item, ? extends Item> i : RegistryHandler.ITEMS.getEntries())
                if(!(i.get() instanceof BuddycardItem))
                    event.accept(i.get());
        } else if (event.getTabKey().equals(BuddycardsMisc.CARDS_TAB.getKey())) {
            for (DeferredHolder<Item, ? extends Item> i : RegistryHandler.ITEMS.getEntries())
                if(i.get() instanceof BuddycardItem)
                    event.accept(i.get());
        }
    }

    public static void setupRenderers() {
        CuriosRendererRegistry.register(BuddycardsItems.MEDAL_CAVE.get(), () -> new MedalRenderer(getMedalId("buddysteel_medal_create")));
    }

    protected static String getMedalId(String name) {
        return "textures/models/medal/" + name;
    }
}
