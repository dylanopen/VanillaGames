package org.vanillacraft.vanillagames.teamrun;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

public class TeamRunGame implements Game {
    public World world;

    public TeamRunGame(Party party){
        party.message("Starting 'Team Run'!");
        world = generateWorld();
        init(party);
    }

    @Override
    public String name() {
        return "teamrun";
    }

    @Override
    public void onJoin(Player player) {
        player.teleportAsync(world.getSpawnLocation());
    }
}