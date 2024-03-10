package net.carlo.justbeersmod.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.carlo.justbeersmod.block.entity.KegBlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class KegRecipe implements Recipe<SimpleInventory> {


    private final Ingredient inputA;
    private final Ingredient inputB;
    private final Ingredient inputC;
    private final Ingredient inputD;
    private final Ingredient inputE;
    private final Identifier id;
    private final ItemStack result;

    public KegRecipe(Ingredient inputA, Ingredient inputB, Ingredient inputC, Ingredient inputD, Ingredient inputE,
                     ItemStack result, Identifier id) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.inputC = inputC;
        this.inputD = inputD;
        this.inputE = inputE;
        this.result = result;
        this.id = id;
    }
    public Ingredient getInputA() {
        return inputA;
    }

    public Ingredient getInputB() {
        return inputB;
    }
    public Ingredient getInputC() {
        return inputC;
    }
    public Ingredient getInputD() {
        return inputD;
    }
    public Ingredient getInputE() {
        return inputE;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {

        if (inventory.size() < 5) return false;

        return inputA.test(inventory.getStack(0)) && inputB.test(inventory.getStack(1))
                && inputC.test(inventory.getStack(2))&& inputD.test(inventory.getStack(3))
                && inputE.test(inventory.getStack(4));
    }



    @Override
    public ItemStack craft(SimpleInventory inv) {
        return this.getOutput().copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }
    @Override
    public ItemStack getOutput() {
        return this.result;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KegRecipeSerializer.INSTANCE;
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


    class KegRecipeJsonFormat {
        JsonObject inputA;
        JsonObject inputB;
        JsonObject inputC;
        JsonObject inputD;
        JsonObject inputE;
        String outputItem;
        int outputAmount;
    }


    public static class KegRecipeSerializer implements RecipeSerializer<KegRecipe> {
        private KegRecipeSerializer() {}

        public static final KegRecipeSerializer INSTANCE = new KegRecipeSerializer();
        public static final Identifier ID = new Identifier("keg");

        @Override
        public KegRecipe read(Identifier recipeId, JsonObject json) {
            KegRecipeJsonFormat recipeJson = new Gson().fromJson(json, KegRecipeJsonFormat.class);

            // Ingredient easily turns JsonObjects of the correct format into Ingredients
            Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
            Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
            Ingredient inputC = Ingredient.fromJson(recipeJson.inputC);
            Ingredient inputD = Ingredient.fromJson(recipeJson.inputD);
            Ingredient inputE = Ingredient.fromJson(recipeJson.inputE);
            // The json will specify the item ID. We can get the Item instance based off of that from the Item registry.
            Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem)).get();
            ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

            return new KegRecipe(inputA, inputB,inputC,inputD,inputE, output, recipeId);
        }

        @Override
        public void write(PacketByteBuf packetData, KegRecipe recipe) {
            recipe.getInputA().write(packetData);
            recipe.getInputB().write(packetData);
            recipe.getInputC().write(packetData);
            recipe.getInputD().write(packetData);
            recipe.getInputE().write(packetData);
            packetData.writeItemStack(recipe.getOutput());
        }

        @Override
        public KegRecipe read(Identifier recipeId, PacketByteBuf packetData) {
            // Make sure the read in the same order you have written!
            Ingredient inputA = Ingredient.fromPacket(packetData);
            Ingredient inputB = Ingredient.fromPacket(packetData);
            Ingredient inputC = Ingredient.fromPacket(packetData);
            Ingredient inputD = Ingredient.fromPacket(packetData);
            Ingredient inputE = Ingredient.fromPacket(packetData);
            ItemStack output = packetData.readItemStack();
            return new KegRecipe(inputA, inputB,inputC,inputD,inputE, output, recipeId);
        }

    }
}
