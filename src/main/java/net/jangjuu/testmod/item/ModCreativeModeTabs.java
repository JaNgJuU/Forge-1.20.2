package net.jangjuu.testmod.item;

import net.jangjuu.testmod.TestMod;
import net.jangjuu.testmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TEST_TAB = CREATIVE_MODE_TABS.register("test_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUBY.get()))
                    .title(Component.translatable("creativetab.test_tab"))
                    .displayItems((pParameters, pOutput) -> {
                      pOutput.accept(ModItems.RUBY.get());
                      pOutput.accept(ModItems.RAW_RUBY.get());

                      pOutput.accept(Items.EMERALD);

                      pOutput.accept(ModBlocks.RUBY_BLOCK.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
