package com.listertwo.keystonealtar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class AmethystGeode extends Block {

    public AmethystGeode(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(8, 8, 0, 16, 16, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 8, 8),
            Block.makeCuboidShape(0, 0, 8, 8, 16, 16),
            Block.makeCuboidShape(0, 8, 0, 1, 16, 1),
            Block.makeCuboidShape(0, 15, 1, 1, 16, 8),
            Block.makeCuboidShape(5, 15, 0, 8, 16, 1),
            Block.makeCuboidShape(1, 8, 0, 2, 10, 1),
            Block.makeCuboidShape(1, 12, 0, 2, 15, 1),
            Block.makeCuboidShape(7, 8, 0, 8, 15, 1),
            Block.makeCuboidShape(1, 15, 0, 3, 16, 1),
            Block.makeCuboidShape(6, 13, 0, 7, 15, 1),
            Block.makeCuboidShape(5, 8, 0, 7, 9, 1),
            Block.makeCuboidShape(2, 8, 0, 3, 9, 1),
            Block.makeCuboidShape(6, 9, 0, 7, 10, 1),
            Block.makeCuboidShape(0, 8, 1, 1, 9, 4),
            Block.makeCuboidShape(0, 8, 5, 1, 9, 8),
            Block.makeCuboidShape(0, 9, 6, 1, 15, 8),
            Block.makeCuboidShape(0, 14, 4, 1, 15, 6),
            Block.makeCuboidShape(0, 9, 1, 1, 15, 2),
            Block.makeCuboidShape(0, 14, 2, 1, 15, 3),
            Block.makeCuboidShape(0, 9, 2, 1, 10, 3),
            Block.makeCuboidShape(1, 15, 6, 8, 16, 8),
            Block.makeCuboidShape(6, 15, 1, 8, 16, 6),
            Block.makeCuboidShape(1, 14, 1, 2, 16, 2),
            Block.makeCuboidShape(1, 15, 5, 2, 16, 6),
            Block.makeCuboidShape(5, 15, 5, 6, 16, 6),
            Block.makeCuboidShape(7, 9, 7, 8, 14, 8),
            Block.makeCuboidShape(6, 14, 6, 8, 15, 8),
            Block.makeCuboidShape(6, 8, 6, 8, 9, 8),
            Block.makeCuboidShape(7, 8, 1, 8, 9, 6),
            Block.makeCuboidShape(1, 8, 7, 6, 9, 8),
            Block.makeCuboidShape(7, 14, 1, 8, 15, 6),
            Block.makeCuboidShape(1, 14, 7, 6, 15, 8),
            Block.makeCuboidShape(1, 14, 6, 3, 15, 7),
            Block.makeCuboidShape(6, 8, 1, 7, 9, 2),
            Block.makeCuboidShape(1, 8, 6, 3, 9, 7),
            Block.makeCuboidShape(1, 9, 7, 2, 14, 8),
            Block.makeCuboidShape(7, 13, 1, 8, 14, 2),
            Block.makeCuboidShape(1, 8, 1, 2, 9, 3),
            Block.makeCuboidShape(7, 9, 1, 8, 10, 2),
            Block.makeCuboidShape(7, 9, 6, 8, 10, 7),
            Block.makeCuboidShape(6, 9, 7, 7, 10, 8),
            Block.makeCuboidShape(2, 9, 7, 3, 10, 8),
            Block.makeCuboidShape(1, 9, 6, 2, 10, 7),
            Block.makeCuboidShape(2, 13, 7, 3, 14, 8),
            Block.makeCuboidShape(1, 13, 6, 2, 14, 7),
            Block.makeCuboidShape(6, 13, 7, 7, 14, 8),
            Block.makeCuboidShape(7, 13, 6, 8, 14, 7),
            Block.makeCuboidShape(15, 0, 15, 16, 8, 16),
            Block.makeCuboidShape(15, 6, 14, 16, 8, 15),
            Block.makeCuboidShape(15, 1, 14, 16, 4, 15),
            Block.makeCuboidShape(8, 0, 15, 15, 1, 16),
            Block.makeCuboidShape(15, 0, 8, 16, 1, 11),
            Block.makeCuboidShape(15, 0, 13, 16, 1, 15),
            Block.makeCuboidShape(15, 1, 9, 16, 3, 10),
            Block.makeCuboidShape(15, 7, 13, 16, 8, 14),
            Block.makeCuboidShape(12, 7, 15, 15, 8, 16),
            Block.makeCuboidShape(8, 7, 15, 11, 8, 16),
            Block.makeCuboidShape(8, 1, 15, 10, 7, 16),
            Block.makeCuboidShape(14, 1, 15, 15, 7, 16),
            Block.makeCuboidShape(15, 6, 9, 16, 7, 10),
            Block.makeCuboidShape(13, 1, 15, 14, 2, 16),
            Block.makeCuboidShape(13, 6, 15, 14, 7, 16),
            Block.makeCuboidShape(10, 1, 15, 12, 2, 16),
            Block.makeCuboidShape(15, 7, 9, 16, 8, 11),
            Block.makeCuboidShape(15, 1, 8, 16, 8, 9),
            Block.makeCuboidShape(8, 0, 8, 10, 1, 15),
            Block.makeCuboidShape(10, 0, 8, 15, 1, 10),
            Block.makeCuboidShape(14, 0, 14, 15, 2, 15),
            Block.makeCuboidShape(10, 0, 14, 11, 1, 15),
            Block.makeCuboidShape(10, 0, 10, 11, 1, 11),
            Block.makeCuboidShape(8, 2, 8, 9, 7, 9),
            Block.makeCuboidShape(8, 2, 14, 9, 7, 15),
            Block.makeCuboidShape(14, 2, 8, 15, 3, 9),
            Block.makeCuboidShape(14, 6, 8, 15, 7, 9),
            Block.makeCuboidShape(9, 6, 8, 10, 7, 9),
            Block.makeCuboidShape(9, 2, 8, 10, 3, 9),
            Block.makeCuboidShape(8, 6, 9, 9, 7, 10),
            Block.makeCuboidShape(8, 2, 9, 9, 3, 10),
            Block.makeCuboidShape(8, 6, 13, 9, 7, 14),
            Block.makeCuboidShape(8, 2, 13, 9, 3, 14),
            Block.makeCuboidShape(9, 6, 14, 10, 7, 15),
            Block.makeCuboidShape(9, 2, 14, 10, 3, 15),
            Block.makeCuboidShape(8, 1, 8, 10, 2, 10),
            Block.makeCuboidShape(8, 7, 8, 10, 8, 10),
            Block.makeCuboidShape(10, 7, 8, 15, 8, 9),
            Block.makeCuboidShape(8, 7, 10, 9, 8, 15),
            Block.makeCuboidShape(8, 1, 10, 9, 2, 15),
            Block.makeCuboidShape(9, 7, 13, 10, 8, 15),
            Block.makeCuboidShape(13, 7, 14, 15, 8, 15),
            Block.makeCuboidShape(14, 7, 9, 15, 8, 10),
            Block.makeCuboidShape(10, 1, 8, 15, 2, 9),
            Block.makeCuboidShape(9, 1, 13, 10, 2, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }
}
