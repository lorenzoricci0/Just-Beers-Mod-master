package net.carlo.justbeersmod.screen;

import net.carlo.justbeersmod.JustBeersMod;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<KegScreenHandler> KEG_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        KEG_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(JustBeersMod.MOD_ID, "keg"),
                        KegScreenHandler::new);
    }
}
