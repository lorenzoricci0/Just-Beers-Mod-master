package net.carlo.justbeersmod.block.entity;

import net.carlo.justbeersmod.JustBeersMod;
import net.carlo.justbeersmod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {


    public static BlockEntityType<KegBlockEntity> KEG;

    public static void registerBlockEntities(){
        KEG = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(JustBeersMod.MOD_ID, "keg"),
                FabricBlockEntityTypeBuilder.create(KegBlockEntity::new,
                        ModBlocks.KEG).build(null));
    }
}


