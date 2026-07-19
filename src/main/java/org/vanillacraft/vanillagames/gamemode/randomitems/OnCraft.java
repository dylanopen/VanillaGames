package org.vanillacraft.vanillagames.gamemode.randomitems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.vanillacraft.vanillagames.game.Game;

public class OnCraft implements Listener {
    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        Player player = (Player)event.getViewers().getFirst();
        if (!(Game.fromPlayer(player) instanceof RandomItemsGame game)) return;

        ItemStack[] inventoryContents = event.getInventory().getContents();
        ItemStack craftResultSlot = inventoryContents[0];
        Material oldMaterial = craftResultSlot.getType();
        Material newMaterial = game.craftMap.itemTransforms.get(oldMaterial);
        craftResultSlot.setType(newMaterial);

    }
}
