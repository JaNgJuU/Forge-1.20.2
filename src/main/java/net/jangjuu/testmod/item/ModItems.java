package net.jangjuu.testmod.item;

import net.jangjuu.testmod.ModArmorMaterials;
import net.jangjuu.testmod.TestMod;
import net.jangjuu.testmod.item.custom.ModArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FROZONE_BOOTS = ITEMS.register("frozone_boots",
            () -> new ModArmorItem(ModArmorMaterials.FROZONE, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
