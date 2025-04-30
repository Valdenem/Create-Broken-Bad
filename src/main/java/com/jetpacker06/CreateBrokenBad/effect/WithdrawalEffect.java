package com.jetpacker06.CreateBrokenBad.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class WithdrawalEffect extends MobEffect {
    public WithdrawalEffect() {
        super(MobEffectCategory.HARMFUL, 0xAA00FF);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, pAmplifier));
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
