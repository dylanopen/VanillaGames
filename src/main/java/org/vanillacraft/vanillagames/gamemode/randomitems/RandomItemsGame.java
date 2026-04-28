package org.vanillacraft.vanillagames.gamemode.randomitems;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

import java.util.Random;

public class RandomItemsGame implements Game {
    Party party;
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
        this.party = party;
    }

    @Override
    public String name() {
        return "randomitems";
    }

    @Override
    public void onJoin(Player player) {
        player.teleportAsync(world.getSpawnLocation());
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
