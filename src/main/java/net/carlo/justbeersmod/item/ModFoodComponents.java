package net.carlo.justbeersmod.item;

import net.carlo.justbeersmod.item.custom.BeerItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;

public class ModFoodComponents {

    public static final FoodComponent WHEAT_BEER = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), 0.1F).build();
    public static final FoodComponent STRONG_BEER = (new FoodComponent.Builder()).hunger(4).saturationModifier(2.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 1), 0.3F).build();
    public static final FoodComponent SWEET_BEER = (new FoodComponent.Builder()).hunger(3).saturationModifier(1.5F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0), 0.1F).build();
    public static final FoodComponent MINER_BEER = (new FoodComponent.Builder()).hunger(6).saturationModifier(3.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 2), 0.5F).build();
    public static final FoodComponent BLAZING_BEER = (new FoodComponent.Builder()).hunger(8).saturationModifier(4.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), 0.1F).build();
    public static final FoodComponent FROST_BEER = (new FoodComponent.Builder()).hunger(8).saturationModifier(4.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), 0.1F).build();
    public static final FoodComponent APPLE_BEER = (new FoodComponent.Builder()).hunger(4).saturationModifier(2.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), 0.1F).build();
    public static final FoodComponent PUMPKIN_BEER = (new FoodComponent.Builder()).hunger(6).saturationModifier(3.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 2), 0.1F).build();
    public static final FoodComponent BERRY_BEER = (new FoodComponent.Builder()).hunger(3).saturationModifier(1.5F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0), 0.1F).build();


}
