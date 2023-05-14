package com.listertwo.keystonealtar.data.recipies;

import com.listertwo.keystonealtar.KeystoneAltarMod;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KeystoneAltarMod.MODID);

    public static final RegistryObject<KeystoneAltarRecipe.Serializer> KEYSTONE_SERIALIZER = RECIPE_SERIALIZER.register("keystone", KeystoneAltarRecipe.Serializer::new);

    public static IRecipeType<KeystoneAltarRecipe> KEYSTONE_RECIPE = new KeystoneAltarRecipe.KeystoneAltarRecipeType();

    public static void register(IEventBus eventBus){
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, KeystoneAltarRecipe.TYPE_ID, KEYSTONE_RECIPE);
    }
}
