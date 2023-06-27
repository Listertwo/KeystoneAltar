package com.listertwo.keystonealtar.tileentity;

import com.listertwo.keystonealtar.data.recipes.KeystoneAltarRecipe;
import com.listertwo.keystonealtar.data.recipes.ModRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class KeystoneAltarTile  extends TileEntity  implements ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public KeystoneAltarTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public KeystoneAltarTile(){
        this(ModTileEntities.KEYSTONE_ALTAR_TILE.get());
    }

    @Override
    public void read(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        itemHandler.deserializeNBT(serializeNBT().getCompound("inv"));
        super.read(p_230337_1_, p_230337_2_);
    }

    @Override
    public CompoundNBT write(CompoundNBT p_189515_1_) {
        p_189515_1_.put("inv", itemHandler.serializeNBT());
        return super.write(p_189515_1_);
    }

    private ItemStackHandler createHandler(){
        return new ItemStackHandler(7){
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)){
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void craft(){
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++){
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        Optional<KeystoneAltarRecipe> recipe = world.getRecipeManager().getRecipe(ModRecipeTypes.KEYSTONE_RECIPE, inv, world);

        recipe.ifPresent(iRecipe ->{
            ItemStack output = iRecipe.getRecipeOutput();

            if(iRecipe.getRedstoneSignal()) {
                itemHandler.extractItem(0, 1, false);
                itemHandler.extractItem(1, 1, false);
                itemHandler.extractItem(2, 1, false);
                itemHandler.extractItem(3, 1, false);
                itemHandler.extractItem(4, 1, false);
                itemHandler.extractItem(5, 1, false);
                itemHandler.insertItem(6, output, false);
            }

            markDirty();
        });
    }

    @Override
    public void tick() {
        if(world.isRemote)
            return;
        craft();
    }
}
