package org.vanillacraft.vanillagames.game;

import org.vanillacraft.vanillagames.quickjump.QuickJumpGame;
import org.vanillacraft.vanillagames.randomitems.RandomItemsGame;
import org.vanillacraft.vanillagames.sumo.SumoGame;
import org.vanillacraft.vanillagames.teamrun.TeamRunGame;

import java.util.HashMap;

public class GameList {
    public static HashMap<String, Class<? extends Game>> gamemodes = new HashMap<>();

    static {
        gamemodes.put("quickjump", QuickJumpGame.class);
        gamemodes.put("randomitems", RandomItemsGame.class);
        gamemodes.put("sumo", SumoGame.class);
        gamemodes.put("teamrun", TeamRunGame.class);
    }
}
