package com.listertwo.keystonealtar.tileentity;

import com.listertwo.keystonealtar.KeystoneAltarMod;
import com.listertwo.keystonealtar.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, KeystoneAltarMod.MODID);

    public static RegistryObject<TileEntityType<KeystoneAltarTile>> KEYSTONE_ALTAR_TILE = TILE_ENTITIES.register("keystone_altar_tile", () -> TileEntityType.Builder.create(KeystoneAltarTile::new, ModBlocks.KEYSTONE_ALTAR.get()).build(null));

    public static void register(IEventBus eventBus){
        TILE_ENTITIES.register(eventBus);
    }
}
