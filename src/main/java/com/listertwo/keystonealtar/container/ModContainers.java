package com.listertwo.keystonealtar.container;

import com.listertwo.keystonealtar.KeystoneAltarMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, KeystoneAltarMod.MODID);

    public static final RegistryObject<ContainerType<KeystoneAltarContainer>> KEYSTONE_ALTAR_CONTAINER = CONTAINERS.register("keystone_altar_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new KeystoneAltarContainer(windowId, world, pos, inv, inv.player);
    }));

    public static void register(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }
}
