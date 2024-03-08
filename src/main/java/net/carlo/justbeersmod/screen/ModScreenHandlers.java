package net.carlo.justbeersmod.screen;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<KegScreenHandler> KEG_SCREEN_HANDLER;

    public static void  registerAllScreenHandlers(){
        KEG_SCREEN_HANDLER = new ScreenHandlerType<>(KegScreenHandler::new);
    }
}
