package com.wildcard.createbuddycards;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.logistics.item.filter.attribute.ItemAttribute;
import com.simibubi.create.content.logistics.item.filter.attribute.ItemAttributeType;
import com.simibubi.create.content.logistics.item.filter.attribute.attributes.BookAuthorAttribute;
import com.wildcard.buddycards.registries.BuddycardsComponents;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record BuddycardFoilAttribute(int foil) implements ItemAttribute {
    public static final MapCodec<BuddycardFoilAttribute> CODEC = Codec.INT.xmap(BuddycardFoilAttribute::new, BuddycardFoilAttribute::foil).fieldOf("foil");
    public static final StreamCodec<ByteBuf, BuddycardFoilAttribute> STREAM_CODEC = ByteBufCodecs.INT.map(BuddycardFoilAttribute::new, BuddycardFoilAttribute::foil);

    @Override
    public boolean appliesTo(ItemStack stack, Level world) {
        return stack.has(BuddycardsComponents.BUDDYCARD_FOIL) && stack.get(BuddycardsComponents.BUDDYCARD_FOIL) == foil;
    }

    @Override
    public ItemAttributeType getType() {
        return RegistryHandler.FOIL_ATTRIBUTE.get();
    }

    @Override
    public String getTranslationKey() {
        return "buddycard_foil" + foil;
    }

    public static class Type implements ItemAttributeType {

        @Override
        public @NotNull ItemAttribute createAttribute() {
            return new BuddycardFoilAttribute(0);
        }

        @Override
        public List<ItemAttribute> getAllAttributes(ItemStack stack, Level level) {
            if (stack.has(BuddycardsComponents.BUDDYCARD_FOIL))
                return List.of(new BuddycardFoilAttribute(stack.get(BuddycardsComponents.BUDDYCARD_FOIL)));
            return List.of();
        }

        @Override
        public MapCodec<? extends ItemAttribute> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, ? extends ItemAttribute> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
