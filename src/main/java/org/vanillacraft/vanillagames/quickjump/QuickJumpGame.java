package org.vanillacraft.vanillagames.quickjump;

import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

import java.util.HashMap;

public class QuickJumpGame implements Game {
    Party party;
    HashMap<Player, Integer> scores = new HashMap<>();

    public QuickJumpGame(Party party) {
        for (Player player : party.players) {
            scores.put(player, 0);
        }
        this.party = party;
    }

    @Override
    public String name() {
        return "quickjump";
    }

    @Override
    public void onJoin(Player player) {
        scores.put(player, 0);
    }

    @Override
    public void onLeave(Player player) {

    }

    @Override
    public void onRejoin(Player player) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public Party getParty() {
        return party;
    }
}
