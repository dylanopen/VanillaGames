package org.vanillacraft.vanillagames.gamemode.teamrun;

import static org.vanillacraft.vanillagames.VanillaGames.handleListener;

public class TeamRunListener {
    public static void handle() {
        handleListener(new OnEnderDragonDeath());
    }
}
