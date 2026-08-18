package com.wildcard.createbuddycards;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.logistics.item.filter.attribute.ItemAttribute;
import com.simibubi.create.content.logistics.item.filter.attribute.ItemAttributeType;
import com.wildcard.buddycards.registries.BuddycardsComponents;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record BuddycardGradeAttribute(int foil) implements ItemAttribute {
    public static final MapCodec<BuddycardGradeAttribute> CODEC = Codec.INT.xmap(BuddycardGradeAttribute::new, BuddycardGradeAttribute::foil).fieldOf("greade");
    public static final StreamCodec<ByteBuf, BuddycardGradeAttribute> STREAM_CODEC = ByteBufCodecs.INT.map(BuddycardGradeAttribute::new, BuddycardGradeAttribute::foil);

    @Override
    public boolean appliesTo(ItemStack stack, Level world) {
        return stack.has(BuddycardsComponents.BUDDYCARD_GRADE) && stack.get(BuddycardsComponents.BUDDYCARD_GRADE) == foil;
    }

    @Override
    public ItemAttributeType getType() {
        return RegistryHandler.GRADE_ATTRIBUTE.get();
    }

    @Override
    public String getTranslationKey() {
        return "buddycard_grade" + foil;
    }

    public static class Type implements ItemAttributeType {

        @Override
        public @NotNull ItemAttribute createAttribute() {
            return new BuddycardGradeAttribute(0);
        }

        @Override
        public List<ItemAttribute> getAllAttributes(ItemStack stack, Level level) {
            if (stack.has(BuddycardsComponents.BUDDYCARD_GRADE))
                return List.of(new BuddycardGradeAttribute(stack.get(BuddycardsComponents.BUDDYCARD_GRADE)));
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
