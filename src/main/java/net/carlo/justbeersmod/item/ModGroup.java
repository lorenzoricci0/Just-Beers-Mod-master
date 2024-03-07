package net.carlo.justbeersmod.item;

import net.carlo.justbeersmod.JustBeersMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModGroup {
    public static ItemGroup JUST_BEERS = FabricItemGroupBuilder.build(
            new Identifier(JustBeersMod.MOD_ID,"just_beers"), ()-> new ItemStack(ModItems.WHEAT_BEER));
}
