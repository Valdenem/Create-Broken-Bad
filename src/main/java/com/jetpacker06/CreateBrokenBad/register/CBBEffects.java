package com.jetpacker06.CreateBrokenBad.register;

import com.jetpacker06.CreateBrokenBad.CreateBrokenBad;
import com.jetpacker06.CreateBrokenBad.effect.MethEffect;
import com.jetpacker06.CreateBrokenBad.effect.WithdrawalEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CBBEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CreateBrokenBad.MOD_ID);

    public static final RegistryObject<MobEffect> METH =
            EFFECTS.register("meth", MethEffect::new);

    public static final RegistryObject<MobEffect> WITHDRAWAL =
            EFFECTS.register("withdrawal", WithdrawalEffect::new);



    public static void register (IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
