package org.vanillacraft.vanillagames.listener;

import static org.vanillacraft.vanillagames.VanillaGames.handleListener;

public class VanillaGamesListener {
    public static void handle() {
        handleListener(new OnJoin());
    }
}
