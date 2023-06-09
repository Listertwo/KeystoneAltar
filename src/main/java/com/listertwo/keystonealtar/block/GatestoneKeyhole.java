package com.listertwo.keystonealtar.block;

import com.listertwo.keystonealtar.container.KeystoneAltarContainer;
import com.listertwo.keystonealtar.tileentity.GatestoneKeyholeTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class GatestoneKeyhole extends Block {

    public GatestoneKeyhole(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()){
            if(handIn == Hand.MAIN_HAND){
                System.out.println("I right clicked a Gatestone Keyhole with my Main Hand!");
            }
            if(handIn == Hand.OFF_HAND){
                System.out.println("I right clicked a Gatestone Keyhole with my Off Hand!");
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}