package org.vanillacraft.vanillagames.game;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerLobbyReset {
    public static void reset(Player player) {
        Location spawnLocation = new Location(Bukkit.getWorld("world"), 8, -60, 8);
        player.teleportAsync(spawnLocation);
        player.getInventory().clear();
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
        Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
        player.setHealth(20);
        player.setTotalExperience(0);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);
    }
}
