package com.listertwo.keystonealtar.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class KeystoneAltarItemGroup {
    public static final ItemGroup KEYSTONE_GROUP = new ItemGroup("keystoneAltarTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };
}
