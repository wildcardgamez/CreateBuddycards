package com.wildcard.createbuddycards;

import com.wildcard.buddycards.Buddycards;
import com.wildcard.buddycards.core.BuddycardSet;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateBuddycards.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen {
    @SubscribeEvent
    static void onGatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new CardModelGen(event.getGenerator().getPackOutput(), "createbuddycards", event.getExistingFileHelper()));
    }

    static private class CardModelGen extends ItemModelProvider {
        public CardModelGen(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
            super(output, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            for (int i = 1; i <= 36; i++) {
                genCardModel(i);
            }
        }

        void genCardModel(int cardNum) {
            ItemModelBuilder card = getBuilder(ModelProvider.ITEM_FOLDER + "/buddycard_create" + cardNum)
                    .parent(factory.apply(new ResourceLocation(Buddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/buddycard")))
                    .texture("layer0", new ResourceLocation(CreateBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/create_set/" + cardNum));
            for (int i = 0; i <= 5; i++) {
                for (int j = 0; j <= 3; j++)
                    if (j + i != 0)
                        card.override().predicate(new ResourceLocation(Buddycards.MOD_ID, "grade"), i).predicate(new ResourceLocation(Buddycards.MOD_ID, "foil"), j).model(genFoiledGradedCardModel(cardNum, i, j));
            }
        }

        ModelFile genFoiledGradedCardModel(int cardNum, int grade, int foil) {
            ItemModelBuilder card = getBuilder(ModelProvider.ITEM_FOLDER + "/buddycard_create" + cardNum + "_g" + grade + "_f" + foil)
                    .parent(factory.apply(new ResourceLocation(Buddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/buddycard")))
                    .texture("layer0", new ResourceLocation(CreateBuddycards.MOD_ID, ModelProvider.ITEM_FOLDER + "/create_set/" + cardNum));
            if (foil != 0)
                card.texture("layer1", new ResourceLocation(Buddycards.MOD_ID,ModelProvider.ITEM_FOLDER + "/foil" + foil));
            if (grade != 0)
                card.texture(foil == 0 ? "layer1" : "layer2", new ResourceLocation(Buddycards.MOD_ID,ModelProvider.ITEM_FOLDER + "/grade" + grade));
            return card;
        }
    }
}
