package org.vanillacraft.vanillagames.randomitems;

import static org.vanillacraft.vanillagames.VanillaGames.handleListener;

public class RandomItemsListener {
    public static void handle() {
        handleListener(new OnBreak());
        handleListener(new OnCraft());
        handleListener(new OnEntityDeath());
    }
}
