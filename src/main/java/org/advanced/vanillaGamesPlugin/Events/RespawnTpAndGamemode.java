package org.advanced.vanillaGamesPlugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.advanced.vanillaGamesPlugin.Important.GlobalFunctions;
import org.advanced.vanillaGamesPlugin.Important.GlobalVariables;
import org.advanced.vanillaGamesPlugin.Lobby.PartyData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.advanced.vanillaGamesPlugin.VanillaGamesPlugin.plugin;

public class RespawnTpAndGamemode implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (GlobalFunctions.CheckPlayerGame(player, "molehunt"))
        {
            PartyData party = GlobalVariables.parties.get(GlobalFunctions.CheckPlayerParty(player));
            Location spawnLocation = new Location(party.gameSpawn.getWorld(), party.gameSpawn.getX(), 200, party.gameSpawn.getZ());
            event.setRespawnLocation(spawnLocation);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 600, 0));
            }, 1L);

            if (party.molehuntRunners.contains(player)){
                player.setGameMode(GameMode.SPECTATOR);
                party.molehuntRunners.remove(player);
                if (party.molehuntRunners.isEmpty()){
                    for (Player players : party.players){
                    players.showTitle(Title.title(Component.text("Moles win!", NamedTextColor.RED), Component.text("All runners have been defeated", NamedTextColor.BLUE)));
                    }
                    GlobalFunctions.EndOfMolehunt(player);
                }
            }
        }
    }
}
