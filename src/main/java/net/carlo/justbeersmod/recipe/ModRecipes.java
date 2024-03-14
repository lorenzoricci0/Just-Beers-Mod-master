package net.carlo.justbeersmod.recipe;

import net.carlo.justbeersmod.JustBeersMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(JustBeersMod.MOD_ID, KegRecipe.Serializer.ID),
                KegRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(JustBeersMod.MOD_ID, KegRecipe.Type.ID),
                KegRecipe.Type.INSTANCE);
    }
}
