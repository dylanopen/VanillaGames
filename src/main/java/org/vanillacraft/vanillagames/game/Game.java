package org.vanillacraft.vanillagames.game;

import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.mvplugins.multiverse.core.world.MultiverseWorld;
import org.mvplugins.multiverse.core.world.options.CloneWorldOptions;
import org.mvplugins.multiverse.core.world.options.CreateWorldOptions;
import org.mvplugins.multiverse.core.world.options.LoadWorldOptions;
import org.vanillacraft.vanillagames.VanillaGames;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;
import org.vanillacraft.vanillagames.world.Multiverse;
import org.vanillacraft.vanillagames.world.WorldTemplate;

import java.util.Random;

import static org.vanillacraft.vanillagames.forwarding.CommandRunner.runCommand;

public abstract class Game {
    public Party party;

    public String name;
    protected void onJoin(Player player) {}
    protected void onLeave(Player player) {}
    public void onRejoin(Player player) {}
    protected void onStop() {}

    protected World overworld;
    protected World nether;
    protected World end;

    protected boolean hasNether = false;
    protected boolean hasEnd = false;
    protected boolean enableChat = true;
    protected boolean announceAdvancements = true;
    protected boolean announceDeaths = true;
    protected boolean announceJoinLeave = true;

    protected int numberSuffix = 0;

    protected void init(Party party) {
        this.party = party;
        party.game = this;
        tryStart();
    }

    protected void generateWorld() {
        if (numberSuffix == 0) {
            numberSuffix = new Random().nextInt(100_000, 999_999);
        }
        String worldName = name + "_" + numberSuffix;

        Multiverse.core.getWorldManager()
                .createWorld(CreateWorldOptions.worldName(worldName))
                .onSuccess(loadedWorld -> {
                    overworld = loadedWorld.getBukkitWorld().get();
                    tryStart();
                })
                .onFailure(reason -> {
                    VanillaGames.plugin().getLogger().severe("Failed to create world " + worldName + ": " + reason);
                });

        if (hasNether) {
            String netherWorldName = worldName + "_nether";
            Multiverse.core.getWorldManager()
                    .createWorld(CreateWorldOptions.worldName(netherWorldName).environment(World.Environment.NETHER))
                    .onSuccess(loadedWorld -> {
                        nether = loadedWorld.getBukkitWorld().get();
                        tryStart();
                    })
                    .onFailure(reason -> {
                        VanillaGames.plugin().getLogger().severe("Failed to create nether world " + netherWorldName + ": " + reason);
                    });
        }

        if (hasEnd) {
            String endWorldName = worldName + "_end";
            Multiverse.core.getWorldManager()
                    .createWorld(CreateWorldOptions.worldName(endWorldName).environment(World.Environment.THE_END))
                    .onSuccess(loadedWorld -> {
                        end = loadedWorld.getBukkitWorld().get();
                        tryStart();
                    })
                    .onFailure(reason -> {
                        VanillaGames.plugin().getLogger().severe("Failed to create end world " + endWorldName + ": " + reason);
                    });
        }
    }

    protected void generateWorld(String templateName) {
        if (numberSuffix == 0) {
            numberSuffix = new Random().nextInt(100_000, 999_999);
        }
        MultiverseWorld templateWorld = Multiverse.core.getWorldManager().getWorld("template_" + templateName).get();
        Multiverse.core.getWorldManager()
                .cloneWorld(CloneWorldOptions.fromTo(templateWorld, name + "_" + numberSuffix))
                .onSuccess(loadedWorld -> {
                    overworld = loadedWorld.getBukkitWorld().get();
                    tryStart();
                })
                .onFailure(reason -> {
                    VanillaGames.plugin().getLogger().severe("Failed to load template world " + templateName + ": " + reason);
                });
    }

    protected void linkWorlds() {
        if (hasNether) {
            Multiverse.portals.addWorldLink(overworld.getName(), nether.getName(), PortalType.NETHER);
            Multiverse.portals.addWorldLink(nether.getName(), overworld.getName(), PortalType.NETHER);
        }
        if (hasEnd) {
            Multiverse.portals.addWorldLink(overworld.getName(), end.getName(), PortalType.ENDER);
            Multiverse.portals.addWorldLink(end.getName(), overworld.getName(), PortalType.ENDER);
        }
        if (hasNether && hasEnd) {
            Multiverse.portals.addWorldLink(nether.getName(), end.getName(), PortalType.NETHER);
            Multiverse.portals.addWorldLink(end.getName(), nether.getName(), PortalType.NETHER);
        }
    }

    protected void stop() {
        party.game = null;
        for (Player player : party.players){
            PlayerLobbyReset.reset(player);
        }
        onStop();
    }

    public static <T extends Game> T fromPlayer(Player player) {
        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) return null;
        return (T)party.game;
    }

    static boolean isPlaying(Player player, String gamemode) {
        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) return false;
        if (party.game == null) return false;
        return party.game.name.equals(gamemode);
    }

    private void tryStart() {
        if (party == null) return;
        if (overworld == null) return;
        if (hasNether && nether == null) return;
        if (hasEnd && end == null) return;
        start();
    }

    private void start() {
        linkWorlds();
        for (Player player : party.players) {
            runCommand("advancement revoke " + player.getName() + " everything");
            onJoin(player);
        }
    }
}
