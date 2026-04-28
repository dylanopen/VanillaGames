package org.vanillacraft.vanillagames.sumo;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.world.WorldTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.vanillacraft.vanillagames.VanillaGames.plugin;

public class SumoGame implements Game {
    HashMap<Player, Integer> scores = new HashMap<>();
    World world;
    Party party;
    ArrayList<Player> alivePlayers = new ArrayList<>();
    boolean roundActive = true;
    Scoreboard scoreboard;
    Objective scoreboardObjective;

    public SumoGame(Party party) {
        this.party = party;
        loadWorld();
        init(party);
        createScoreboard();
        SumoListener.handle();
    }

    private void createScoreboard() {
        Random random = new Random();

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboardObjective = scoreboard.registerNewObjective("sumo_wins", "sumo_wins_criteria", Component.text("Rounds won"));
        scoreboardObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        updateScoreboard();
    }

    void updateScoreboard() {
        for (Player player : party.players) {
            scoreboardObjective.getScore(player.getName()).setScore(scores.get(player));
        }
    }

    private void loadWorld() {
        world = generateWorld("sumo_1");
    }

    void killPlayer(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        party.title(player.name().append(Component.text(" died!")));
        alivePlayers.remove(player);
        if (alivePlayers.size() <= 1) {
            playerWon(alivePlayers.getFirst());
        }
    }

    void playerWon(Player roundWinner) {
        roundActive = false;
        party.title(roundWinner.name().append(Component.text(" won the round!")));

        Bukkit.getScheduler().runTaskLater(plugin(), () -> {
            alivePlayers.clear();
            for (Player partyPlayer : party.players) {
                alivePlayers.add(partyPlayer);
                partyPlayer.setGameMode(GameMode.ADVENTURE);
                partyPlayer.teleportAsync(world.getSpawnLocation());
            }
        }, 20*5);
    }

    @Override
    public String name() {
        return "sumo";
    }

    @Override
    public void onJoin(Player player) {
        plugin().getLogger().info(player + " joined sumo");
        scores.put(player, 0);
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
