package org.vanillacraft.vanillagames.gamemode.quickjump;

import static org.vanillacraft.vanillagames.VanillaGames.handleListener;

public class QuickJumpListeners {
    public static void handle() {
        handleListener(new OnJump());
    }
}
