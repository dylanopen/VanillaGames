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
        TeamRunGame game = Game.fromPlayer(player);
        Party party = PartyList.getPartyByPlayer(player);
        if (player == null) return;
        if (!Game.isPlaying(player, "teamrun")) return;
        if (event.getEntityType() != EntityType.ENDER_DRAGON) return;

        ArrayList<Player> team = null;
        for (ArrayList<Player> currentTeam : game.teams) {
            if (currentTeam.contains(player)) {
                team = currentTeam;
            }
        }
        String playerList = "";
        for (Player teamPlayer : team) {
            playerList += teamPlayer.getName() + ", ";
        }
        party.message(playerList + " have defeated the ender dragon");
    }
}
