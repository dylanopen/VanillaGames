package org.advanced.vanillaGamesPlugin.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.advanced.vanillaGamesPlugin.Important.GlobalFunctions;
import org.advanced.vanillaGamesPlugin.Important.GlobalVariables;
import org.advanced.vanillaGamesPlugin.Lobby.PartyData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillDragon implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();

        if (GlobalFunctions.CheckPlayerGame(player, "molehunt"))
        {
            if (event.getEntityType() == EntityType.ENDER_DRAGON)
            {
                PartyData party = GlobalVariables.parties.get(GlobalFunctions.CheckPlayerParty(player));
                for (Player players : party.players){
                    players.showTitle(Title.title(Component.text("Speedrunners win!", NamedTextColor.RED), Component.text("The enderdragon has been defeated", NamedTextColor.BLUE)));
                }
                GlobalFunctions.EndOfMolehunt(player);
            }
        }
    }
}