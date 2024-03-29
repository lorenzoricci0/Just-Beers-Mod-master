package net.carlo.justbeersmod;

import net.carlo.justbeersmod.screen.KegScreen;
import net.carlo.justbeersmod.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class JustBeersModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ModScreenHandlers.KEG_SCREEN_HANDLER, KegScreen::new);

    }
}
