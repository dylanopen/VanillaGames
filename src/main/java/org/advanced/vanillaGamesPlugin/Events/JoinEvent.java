package org.advanced.vanillaGamesPlugin.Events;

import net.kyori.adventure.text.Component;
import org.advanced.vanillaGamesPlugin.Important.GlobalFunctions;
import org.advanced.vanillaGamesPlugin.Important.GlobalVariables;
import org.advanced.vanillaGamesPlugin.Lobby.PartyData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (GlobalFunctions.CheckPlayerGame(player, "molehunt"))
        {
            return;
        }
        else if (GlobalFunctions.CheckPlayerParty(player) != ""){
            PartyData party = GlobalVariables.parties.get(GlobalFunctions.CheckPlayerParty(player));
            player.playerListName(Component.text(player.getName(), party.colour));
        }
        else
        {
            player.setGameMode(GameMode.ADVENTURE);
            player.teleport(GlobalVariables.lobbySpawn);
            player.heal(20);
            player.setFoodLevel(20);
            player.getInventory().clear();
            player.setExp(0);
        }
    }
}