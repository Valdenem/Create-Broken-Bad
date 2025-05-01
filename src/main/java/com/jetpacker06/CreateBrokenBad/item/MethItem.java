package com.jetpacker06.CreateBrokenBad.item;

import com.jetpacker06.CreateBrokenBad.block.TrayBlock;
import com.jetpacker06.CreateBrokenBad.register.CBBBlocks;
import com.jetpacker06.CreateBrokenBad.register.CBBEffects;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class MethItem extends Item {
    public MethItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Block clickedBlock = pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock();
        Block newBlock = ((pContext.getItemInHand().getItem() instanceof MethItem.Blue) ? CBBBlocks.BLUE_METH_TRAY : CBBBlocks.WHITE_METH_TRAY).get();
        if (clickedBlock instanceof TrayBlock.Empty) {
            Direction direction = pContext.getLevel().getBlockState(pContext.getClickedPos()).getValue(TrayBlock.FACING);
            pContext.getLevel().setBlock(
                pContext.getClickedPos(),
                newBlock.defaultBlockState().setValue(TrayBlock.FACING, direction),
                3
            );
            pContext.getLevel().playSound(pContext.getPlayer(),pContext.getClickedPos(), SoundEvents.SAND_HIT, SoundSource.BLOCKS, 2f, 1f);
            pContext.getItemInHand().shrink(1);
        }
        return InteractionResult.CONSUME;
    }
    public static class Blue extends MethItem {
        private static final int MAX_LEVEL = 3;

        public Blue(Properties pProperties) {
            super(pProperties.food(new FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(0)
                    .saturationMod(0)
                    .effect(() -> new MobEffectInstance(CBBEffects.METH.get(), 1200, 0), 1)
                    .build()).stacksTo(16));
        }
        @Override
        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
            if(!level.isClientSide && entity instanceof ServerPlayer player) {
                MobEffectInstance currentEffect = player.getEffect(CBBEffects.METH.get());
                int newLevel = currentEffect != null ?
                        Math.min(currentEffect.getAmplifier() + 1, MAX_LEVEL) : 0;
                int newDuration = currentEffect != null ?
                        currentEffect.getDuration() + 1200 : 2400;

                player.addEffect(new MobEffectInstance(CBBEffects.METH.get(), newDuration, newLevel));
            }
            return super.finishUsingItem(stack, level, entity);
        }
    }
    public static class White extends MethItem {
        private static final int MAX_LEVEL = 1;

        public White(Properties pProperties) {
            super(pProperties.food(new FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(0)
                    .saturationMod(0)
                    .effect(() -> new MobEffectInstance(CBBEffects.METH.get(), 600, 0), 1)
                    .build()).stacksTo(16));
        }
        @Override
        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
            if(!level.isClientSide && entity instanceof ServerPlayer player) {
                MobEffectInstance currentEffect = player.getEffect(CBBEffects.METH.get());
                int newLevel = currentEffect != null ?
                        Math.min(currentEffect.getAmplifier() + 1, MAX_LEVEL) : 0;
                int newDuration = currentEffect != null ?
                        currentEffect.getDuration() + 600 : 1200;

                player.addEffect(new MobEffectInstance(CBBEffects.METH.get(), newDuration, newLevel));
            }
            return super.finishUsingItem(stack, level, entity);
        }
    }
}
