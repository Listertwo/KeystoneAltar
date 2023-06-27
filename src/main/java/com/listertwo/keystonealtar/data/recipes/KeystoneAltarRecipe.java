package com.listertwo.keystonealtar.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.listertwo.keystonealtar.block.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class KeystoneAltarRecipe implements IKeystoneAltarRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public KeystoneAltarRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        if(recipeItems.get(0).test(inv.getStackInSlot(0))){
            if(recipeItems.get(1).test(inv.getStackInSlot(1))) {
                if (recipeItems.get(2).test(inv.getStackInSlot(2))) {
                    if (recipeItems.get(3).test(inv.getStackInSlot(3))) {
                        if (recipeItems.get(4).test(inv.getStackInSlot(4))) {
                            return recipeItems.get(5).test(inv.getStackInSlot(5));
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    public boolean getRedstoneSignal(){
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public ItemStack getIcon(){
        return new ItemStack(ModBlocks.KEYSTONE_ALTAR.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.KEYSTONE_SERIALIZER.get();
    }

    public static class KeystoneRecipeType implements IRecipeType<KeystoneAltarRecipe>{
        @Override
        public String toString() {
            return KeystoneAltarRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<KeystoneAltarRecipe> {

        @Override
        public KeystoneAltarRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(6, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new KeystoneAltarRecipe(recipeId, output, inputs);
        }

        @Nullable
        @Override
        public KeystoneAltarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(6, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.read(buffer));
            }

            ItemStack output = buffer.readItemStack();
            return new KeystoneAltarRecipe(recipeId, output, inputs);
        }

        @Override
        public void write(PacketBuffer buffer, KeystoneAltarRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }
    }
}
