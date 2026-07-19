package org.vanillacraft.vanillagames.gamemode.randomitems;

import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

import java.util.Random;

public class RandomItemsGame extends Game {
    public RandomisedBlockDropMap dropMap;
    public RandomisedCraftMap craftMap;
    public RandomisedEntityKillMap entityKillMap;
    public Random random;

    public RandomItemsGame(Party party) {
        name = "randomitems";

//        party.message("Starting 'Random Items'!");
        dropMap = new RandomisedBlockDropMap();
        craftMap = new RandomisedCraftMap();
        entityKillMap = new RandomisedEntityKillMap();

        init(party);

        random = new Random();
        generateWorld();
    }

    @Override
    public void onJoin(Player player) {
        player.teleportAsync(overworld.getSpawnLocation());
    }
}
