package net.jangjuu.testmod.effect;

import net.jangjuu.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TestMod.MOD_ID);

    public static final RegistryObject<MobEffect> FROZONE = MOB_EFFECTS.register("frozone",
            () -> new FrozoneEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
