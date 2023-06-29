package com.listertwo.keystonealtar.block;

import com.listertwo.keystonealtar.KeystoneAltarMod;
import com.listertwo.keystonealtar.item.KeystoneAltarItemGroup;
import com.listertwo.keystonealtar.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KeystoneAltarMod.MODID);

    //Registries for the blocks
    public static final RegistryObject<Block> KEYSTONE_BLOCK = registerBlock("keystone_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(3f)));
    public static final RegistryObject<Block> GATESTONE_BLOCK = registerBlock("gatestone_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(-1f)));
    public static final RegistryObject<Block> GATESTONE_KEYHOLE = registerBlock("gatestone_keyhole", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(-1f)));

    public static final RegistryObject<Block> AMETHYST_GEODE = registerBlock("amethyst_geode", () -> new AmethystGeode(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(3f)));
    public static final RegistryObject<Block> KEYSTONE_ALTAR = registerBlock("keystone_altar", () -> new KeystoneAltarBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.GLASS).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5f, 6.0f)));

    //Insures registered block returns Block
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }

    //Automatically creates block item for every Block Registered
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(KeystoneAltarItemGroup.KEYSTONE_GROUP)));
    }

    //Registers all blocks in this class
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
