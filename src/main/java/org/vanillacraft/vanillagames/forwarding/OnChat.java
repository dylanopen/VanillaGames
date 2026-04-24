package org.vanillacraft.vanillagames.forwarding;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class OnChat implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        Party party = PartyList.getPartyByPlayer(player);
        Component message = event.message();
        event.message(Component.empty());
        if (party == null) {
            return;
        }

        party.message(Component.text("<").append(player.name()).append(Component.text("> ")).append(message));
    }
}
