package org.vanillacraft.vanillagames.gamemode.sumo;

import static org.vanillacraft.vanillagames.VanillaGames.handleListener;

public class SumoListener {
    public static void handle() {
        handleListener(new OnMove());
    }
}
