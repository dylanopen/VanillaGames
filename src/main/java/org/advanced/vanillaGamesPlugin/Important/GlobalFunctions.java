package org.advanced.vanillaGamesPlugin.Important;

import net.kyori.adventure.text.Component;
import org.advanced.vanillaGamesPlugin.Lobby.PartyData;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GlobalFunctions {
    public static Boolean CheckPlayerGame(Player player, String game) {
        for (String key : GlobalVariables.parties.keySet()) {
            PartyData party = GlobalVariables.parties.get(key);
            if (party.players.contains(player)) {
                if (party.game.equals(game)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String CheckPlayerParty(Player player) {
        for (String key : GlobalVariables.parties.keySet()) {
            PartyData party = GlobalVariables.parties.get(key);
            if (party.players.contains(player)) {
                return key;
            }
        }
        return "";
    }

    public static void EndOfMolehunt(Player player) {
        PartyData party = GlobalVariables.parties.get(GlobalFunctions.CheckPlayerParty(player));
        for (Player players : party.players) {
            players.teleport(GlobalVariables.lobbySpawn);
            players.getInventory().clear();
            players.setGameMode(GameMode.ADVENTURE);
            player.playerListName(Component.text(player.getName()));
        }
        party.players.clear();
        party.molehuntRunners.clear();
        party.molehuntMoles.clear();
        party.game = "";
        party.gameSpawn = new Location(null, 0, 0, 0);
    }
}