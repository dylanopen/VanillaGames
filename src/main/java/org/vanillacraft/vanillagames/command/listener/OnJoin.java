package org.vanillacraft.vanillagames.command.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Party party = new Party(player.getName());
        party.addPlayer(player);
        PartyList.addParty(party);
    }
}
