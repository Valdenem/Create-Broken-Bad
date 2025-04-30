package com.jetpacker06.CreateBrokenBad.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CyanideItem extends Item {
    public CyanideItem(Properties pProperties) {
        super(pProperties.food(new FoodProperties.Builder()
                .nutrition(0)
                .saturationMod(0)
                .effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 0), 1)
                .alwaysEat()
                .build()));
    }
}
