package net.carlo.justbeersmod.item;

import net.carlo.justbeersmod.JustBeersMod;
import net.carlo.justbeersmod.item.custom.BeerItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item MUG = registerItem("mug",
            new Item(new FabricItemSettings().group(ModGroup.JUST_BEERS).maxCount(8)));
    public static final Item WHEAT_BEER = registerItem("wheat_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.WHEAT_BEER).maxCount(8)));
    public static final Item STRONG_BEER = registerItem("strong_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.STRONG_BEER).maxCount(8)));
    public static final Item SWEET_BEER = registerItem("sweet_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.SWEET_BEER).maxCount(8)));
    public static final Item MINER_BEER = registerItem("miner_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.MINER_BEER).maxCount(8)));
    public static final Item BLAZING_BEER = registerItem("blazing_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.BLAZING_BEER).maxCount(8)));
    public static final Item FROST_BEER = registerItem("frost_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.FROST_BEER).maxCount(8)));
    public static final Item APPLE_BEER = registerItem("apple_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.APPLE_BEER).maxCount(8)));
    public static final Item PUMPKIN_BEER = registerItem("pumpkin_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.PUMPKIN_BEER).maxCount(8)));
    public static final Item BERRY_BEER = registerItem("berry_beer",
            new BeerItem(new FabricItemSettings().group(ModGroup.JUST_BEERS).food(ModFoodComponents.BERRY_BEER).maxCount(8)));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(JustBeersMod.MOD_ID, name), item);
    }
    public static void registerModItems(){
        JustBeersMod.LOGGER.debug("Registering Mod Items for" + JustBeersMod.MOD_ID);
    }
}
