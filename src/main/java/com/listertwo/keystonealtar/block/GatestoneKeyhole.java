package com.listertwo.keystonealtar.block;

import com.listertwo.keystonealtar.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.Property;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.lwjgl.opengl.WGLARBExtensionsString;

public class GatestoneKeyhole extends Block {

    public GatestoneKeyhole(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            ItemStack itemStack = player.getHeldItem(handIn);
            if(handIn == Hand.MAIN_HAND && isValidKey(itemStack)){
                System.out.println("I right clicked a Gatestone Keyhole with the Example Key!");
                itemStack.shrink(1);
                updateNeighbors(worldIn, pos);
                worldIn.destroyBlock(pos, false);

            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    public static void updateNeighbors(World world, BlockPos pos) {
        BlockPos[] neighbors = {
                pos.north(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down()
        };

        for (BlockPos neighborPos : neighbors) {
            BlockState neighborState = world.getBlockState(neighborPos);
            if (neighborState.getBlock() == ModBlocks.GATESTONE_BLOCK.get()) {
                world.destroyBlock(neighborPos, false);
                System.out.println("I Updated! I've Destroyed myself!");
            }
        }
    }

    private static boolean isValidKey(ItemStack itemStack){
        return itemStack.getItem() == ModItems.EXAMPLE_KEY.get();
    }
}