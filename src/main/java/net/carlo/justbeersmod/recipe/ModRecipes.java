package net.carlo.justbeersmod.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
    public static void registerRecipes(){
        Registry.register(Registry.RECIPE_SERIALIZER, KegRecipe.KegRecipeSerializer.ID,
                KegRecipe.KegRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier("keg", KegRecipe.Type.ID), KegRecipe.Type.INSTANCE);

    }
}
