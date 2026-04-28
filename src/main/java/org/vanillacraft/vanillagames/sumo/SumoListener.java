package org.vanillacraft.vanillagames.sumo;

import org.vanillacraft.vanillagames.randomitems.OnBreak;
import org.vanillacraft.vanillagames.randomitems.OnCraft;
import org.vanillacraft.vanillagames.randomitems.OnEntityDeath;

import static org.vanillacraft.vanillagames.VanillaGames.handleListener;

public class SumoListener {
    public static void handle() {
        handleListener(new OnMove());
    }
}
