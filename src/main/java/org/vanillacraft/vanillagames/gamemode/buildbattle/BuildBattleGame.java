package org.vanillacraft.vanillagames.gamemode.buildbattle;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

public class BuildBattleGame implements Game {
    Party party;
    World world;

    @Override
    public String name() {
        return "buildbattle";
    }

    @Override
    public void onJoin(Player player) {

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
        return null;
    }
}
