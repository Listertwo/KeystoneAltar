package com.listertwo.keystonealtar.block;


import com.listertwo.keystonealtar.container.KeystoneAltarContainer;
import com.listertwo.keystonealtar.tileentity.KeystoneAltarTile;
import com.listertwo.keystonealtar.tileentity.ModTileEntities;
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
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class KeystoneAltarBlock extends Block {

    public KeystoneAltarBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(2, 10, 2, 14, 12, 14),
            Block.makeCuboidShape(7, 12, 7, 9, 12.7, 9),
            Block.makeCuboidShape(7, 12, 2.8, 9, 12.5, 4.8),
            Block.makeCuboidShape(3, 12, 5, 5, 12.5, 7),
            Block.makeCuboidShape(11, 12, 5, 13, 12.5, 7),
            Block.makeCuboidShape(2, 0, 2, 14, 1, 14),
            Block.makeCuboidShape(4, 1, 4, 12, 2, 12),
            Block.makeCuboidShape(6, 2, 6, 10, 10, 10),
            Block.makeCuboidShape(7, 12, 11.3, 9, 12.5, 13.3),
            Block.makeCuboidShape(11, 12, 9, 13, 12.5, 11),
            Block.makeCuboidShape(3, 12, 9, 5, 12.5, 11),
            Block.makeCuboidShape(9, 12, 7, 10, 13, 9),
            Block.makeCuboidShape(7, 12, 6, 9, 13, 7),
            Block.makeCuboidShape(7, 11, 9, 9, 12, 10),
            Block.makeCuboidShape(6, 11, 7, 7, 12, 9),
            Block.makeCuboidShape(14, 11, 2, 15, 12, 14),
            Block.makeCuboidShape(2, 11, 2, 3, 12, 14),
            Block.makeCuboidShape(2, 11, 13, 14, 12, 14),
            Block.makeCuboidShape(2, 11, 1, 14, 12, 2),
            Block.makeCuboidShape(1, 9.7, 1, 2, 12.7, 2),
            Block.makeCuboidShape(14, 9.7, 1, 15, 12.7, 2),
            Block.makeCuboidShape(14, 9.7, 14, 15, 12.7, 15),
            Block.makeCuboidShape(1, 9.7, 14, 2, 12.7, 15),
            Block.makeCuboidShape(1, 11, 7.5, 2, 12.3, 8.5),
            Block.makeCuboidShape(7.5, 11, 14, 8.5, 12.3, 15),
            Block.makeCuboidShape(14, 11, 7.5, 15, 12.3, 8.5),
            Block.makeCuboidShape(7.5, 11, 1, 8.5, 12.3, 2),
            Block.makeCuboidShape(1.25, 12.7, 1.25, 1.75, 12.95, 1.75),
            Block.makeCuboidShape(1.25, 12.7, 14.25, 1.75, 12.95, 14.75),
            Block.makeCuboidShape(14.25, 12.7, 14.25, 14.75, 12.95, 14.75),
            Block.makeCuboidShape(14.25, 12.7, 1.25, 14.75, 12.95, 1.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if(!p_225533_2_.isRemote()){
            TileEntity tileEntity = p_225533_2_.getTileEntity(p_225533_3_);

            if(tileEntity instanceof KeystoneAltarTile){
                INamedContainerProvider containerProvider = createContainerProvider(p_225533_2_, p_225533_3_);

                NetworkHooks.openGui(((ServerPlayerEntity)p_225533_4_), containerProvider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Our container Provider is missing!");
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World p_225533_2_, BlockPos p_225533_3_) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.keystonealtar.keystone_altar");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new KeystoneAltarContainer(i, p_225533_2_, p_225533_3_, playerInventory, playerEntity);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.KEYSTONE_ALTAR_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
