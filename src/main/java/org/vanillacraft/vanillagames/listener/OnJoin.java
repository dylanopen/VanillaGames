package org.vanillacraft.vanillagames.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.vanillacraft.vanillagames.game.PlayerLobbyReset;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) {
            PlayerLobbyReset.reset(player);
            return;
        }
        party.game.onRejoin(player);
    }
}
