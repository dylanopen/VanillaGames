package org.vanillacraft.vanillagames.randomitems;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

import java.util.Random;

public class RandomItemsGame implements Game {
    public RandomisedBlockDropMap dropMap;
    public RandomisedCraftMap craftMap;
    public RandomisedEntityKillMap entityKillMap;
    public Random random;
    public World world;

    public RandomItemsGame(Party party) {
        party.message("Starting 'Random Items'!");
        dropMap = new RandomisedBlockDropMap();
        craftMap = new RandomisedCraftMap();
        entityKillMap = new RandomisedEntityKillMap();

        random = new Random();
        world = generateWorld();
        init(party);
    }

    @Override
    public String name() {
        return "randomitems";
    }

    @Override
    public void onJoin(Player player) {
        player.teleportAsync(world.getSpawnLocation());
    }
}
