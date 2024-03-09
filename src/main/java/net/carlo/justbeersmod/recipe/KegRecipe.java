package net.carlo.justbeersmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.carlo.justbeersmod.block.entity.KegBlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class KegRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public KegRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()){
            return false;
        }

        return recipeItems.get(0).test(inventory.getStack(KegBlockEntity.WHEAT_SLOT)) &&
                recipeItems.get(1).test(inventory.getStack(KegBlockEntity.SUGAR_SLOT)) &&
                recipeItems.get(2).test(inventory.getStack(KegBlockEntity.WATER_SLOT)) &&
                recipeItems.get(3).test(inventory.getStack(KegBlockEntity.INGREDIENT_SLOT)) &&
                recipeItems.get(4).test(inventory.getStack(KegBlockEntity.MUG_SLOT));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<KegRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "keg";
    }

    public static class Serializer implements RecipeSerializer<KegRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "keg";

        @Override
        public KegRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(5, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new KegRecipe(id, output, inputs);

        }

        @Override
        public KegRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }
            ItemStack output = buf.readItemStack();
            return new KegRecipe(id, output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, KegRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
        }

    }
}
