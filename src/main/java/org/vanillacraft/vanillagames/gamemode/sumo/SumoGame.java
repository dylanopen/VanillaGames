package org.vanillacraft.vanillagames.gamemode.sumo;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.vanillacraft.vanillagames.VanillaGames.plugin;

public class SumoGame extends Game {
    HashMap<Player, Integer> scores = new HashMap<>();
    ArrayList<Player> alivePlayers = new ArrayList<>();
    boolean roundActive = true;
    Scoreboard scoreboard;
    Objective scoreboardObjective;

    public int pointsPerRound = 5;

    public SumoGame(Party party) {
        name = "sumo";
        generateWorld("sumo_a");
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


    void killPlayer(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        alivePlayers.remove(player);
        for (Player player2 : alivePlayers) {
            plugin().getLogger().info(player2.getName());
        }
        if (alivePlayers.size() <= 1) {
            playerWon(alivePlayers.getFirst());
        }
    }

    void playerWon(Player roundWinner) {
        roundActive = false;
        party.title(roundWinner.name().append(Component.text(" won the round!")));

        scores.put(roundWinner, scores.get(roundWinner) + 1);

        boolean roundOver = false;
        String scoreMessage = "SCORES:\n";
        for (Player scorePlayer : scores.keySet()) {
            int scoreValue = scores.get(scorePlayer);
            if (scoreValue >= pointsPerRound) roundOver = true;
            party.message(scorePlayer.getName() + ": " + scoreValue);
        }

        if (roundOver) {
            stop();
            return;
        }

        Bukkit.getScheduler().runTaskLater(plugin(), () -> {
            alivePlayers.clear();
            for (Player partyPlayer : party.players) {
                alivePlayers.add(partyPlayer);
                partyPlayer.setGameMode(GameMode.ADVENTURE);
                partyPlayer.teleportAsync(overworld.getSpawnLocation());
                roundActive = true;
            }
        }, 20*5);
    }

    @Override
    public void onJoin(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 255));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255));
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, PotionEffect.INFINITE_DURATION, 255));
        plugin().getLogger().info(player + " joined sumo");
        alivePlayers.add(player);
        scores.put(player, 0);
        player.teleportAsync(overworld.getSpawnLocation());
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
}
