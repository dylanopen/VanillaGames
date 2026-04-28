package org.vanillacraft.vanillagames.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;
import org.vanillacraft.vanillagames.world.WorldTemplate;

import java.util.Random;

import static org.vanillacraft.vanillagames.forwarding.CommandRunner.runCommand;

public interface Game {
    String name();
    void onJoin(Player player);
    void onLeave(Player player);
    void onRejoin(Player player);
    void onStop();
    Party getParty();

    default World generateWorld(int numberSuffix) {
        String worldName = name() + "_world_" + numberSuffix;
        WorldCreator creator = new WorldCreator(worldName);
        World world = creator.createWorld();
        return world;
    }

    default World generateWorld() {
        Random rand = new Random();
        int randNum = rand.nextInt(100_000, 999_999);
        return generateWorld(randNum);
    }

    default World generateWorld(String templateName) {
        Random rand = new Random();
        int randNum = rand.nextInt(100_000, 999_999);
        return WorldTemplate.createCopy(templateName, name() + "_world_" + randNum);
    }

    default void init(Party party) {
        for (Player player : party.players) {
            runCommand("advancement revoke " + player.getName() + " everything");
            onJoin(player);
        }
    }

    default boolean announceAdvancements() { return true; }
    default boolean enableChat() { return true; }

    static <T extends Game> T fromPlayer(Player player) {
        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) return null;
        return (T)party.game;
    }

    static boolean isPlaying(Player player, String gamemode) {
        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) return false;
        if (party.game == null) return false;
        return party.game.name().equals(gamemode);
    }
}
