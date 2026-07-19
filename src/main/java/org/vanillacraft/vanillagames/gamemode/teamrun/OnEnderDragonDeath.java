package org.vanillacraft.vanillagames.gamemode.teamrun;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

import java.util.ArrayList;

public class OnEnderDragonDeath implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if (player == null) return;
        if (!(Game.fromPlayer(player) instanceof TeamRunGame game)) return;
        if (event.getEntityType() != EntityType.ENDER_DRAGON) return;
        Party party = PartyList.getPartyByPlayer(player);

        ArrayList<Player> team = null;
        for (ArrayList<Player> currentTeam : game.teams) {
            if (currentTeam.contains(player)) {
                team = currentTeam;
            }
        }
        StringBuilder playerList = new StringBuilder();
        for (Player teamPlayer : team) {
            playerList.append(teamPlayer.getName()).append(", ");
        }
        party.message(playerList.toString().substring(0, playerList.length() - 2) + " have defeated the ender dragon");
    }
}
