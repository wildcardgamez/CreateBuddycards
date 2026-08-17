package com.wildcard.createbuddycards;

import com.wildcard.buddycards.item.IMedalTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public enum MedalTypes implements IMedalTypes {
    CREATE_SET((player, mod) -> {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 300, mod, true, false));
    });

    MedalTypes(MedalTypes.MedalEffect effect) {
        this.effect = effect;
    }
    private final MedalTypes.MedalEffect effect;

    @Override
    public void applyEffect(Player player, int mod) {
        effect.applyEffect(player, mod);
    }

    interface MedalEffect {
        void applyEffect(Player player, int mod);
    }
}
