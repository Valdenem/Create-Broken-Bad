package com.jetpacker06.CreateBrokenBad.effect;

import com.jetpacker06.CreateBrokenBad.register.CBBEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class MethEffect extends MobEffect {
    private static final List<ItemStack> EMPTY_CURATIVE_ITEMS = List.of();
    public MethEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xAA00FF);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, pAmplifier, false, false, false));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, pAmplifier, false, false, false));
        MobEffectInstance instance = pLivingEntity.getEffect(this);
        if (instance != null && instance.getDuration() <= 1) {
            pLivingEntity.addEffect(new MobEffectInstance(CBBEffects.WITHDRAWAL.get(), 20 * 30 * (pAmplifier + 1), pAmplifier));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return EMPTY_CURATIVE_ITEMS;
    }
}
