package org.vanillacraft.vanillagames.quickjump;

import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

import java.util.HashMap;

public class QuickJumpGame implements Game {
    HashMap<Player, Integer> scores = new HashMap<>();

    public QuickJumpGame(Party party) {
        for (Player player : party.players) {
            scores.put(player, 0);
        }
    }

    @Override
    public String name() {
        return "quickjump";
    }

    @Override
    public void onJoin(Player player) {
        scores.put(player, 0);
    }
}
