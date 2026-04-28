package org.vanillacraft.vanillagames.forwarding;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class OnAdvancement implements Listener {
    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) {
            return;
        }
        party.message(event.message());
        event.message(Component.empty());
    }
}
