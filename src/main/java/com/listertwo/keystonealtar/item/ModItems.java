package com.listertwo.keystonealtar.item;

import com.listertwo.keystonealtar.KeystoneAltarMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KeystoneAltarMod.MODID);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().group(KeystoneAltarItemGroup.KEYSTONE_GROUP)));
    public static final RegistryObject<Item> EXAMPLE_KEY = ITEMS.register("example_key", () -> new Item(new Item.Properties().group(KeystoneAltarItemGroup.KEYSTONE_GROUP).maxStackSize(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
