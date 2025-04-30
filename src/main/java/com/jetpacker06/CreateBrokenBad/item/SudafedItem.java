package com.jetpacker06.CreateBrokenBad.item;

import com.jetpacker06.CreateBrokenBad.util.TooltipHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SudafedItem extends Item {

    public SudafedItem(Properties pProperties) {
        super(pProperties.food(new FoodProperties.Builder()
                .alwaysEat()
                .nutrition(0)
                .saturationMod(0)
                .build())
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            List<MobEffectInstance> effectsToRemove = new ArrayList<>();

            for (MobEffectInstance effectInstance : pLivingEntity.getActiveEffects()) {
                if (effectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                    effectsToRemove.add(effectInstance);
                }
            }

            for (MobEffectInstance effect : effectsToRemove) {
                pLivingEntity.removeEffect(effect.getEffect());
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(TooltipHelper.holdShift());

        if (TooltipHelper.isShiftKeyDown()) {
            pTooltipComponents.addAll(TooltipHelper.formattedTooltip("item.createbb.sudafed.tooltip"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
