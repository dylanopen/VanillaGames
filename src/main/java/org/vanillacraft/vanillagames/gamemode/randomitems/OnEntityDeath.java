package org.vanillacraft.vanillagames.gamemode.randomitems;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.vanillacraft.vanillagames.game.Game;

import java.util.Collection;
import java.util.List;

import static org.vanillacraft.vanillagames.VanillaGames.plugin;

public class OnEntityDeath implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if (!(Game.fromPlayer(player) instanceof RandomItemsGame game)) return;

        Material newMaterial = game.entityKillMap.itemTransforms.get(event.getEntityType());
        int quantity = game.random.nextInt(1, 4);
        event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(newMaterial, quantity));

        final List<ItemStack> itemStacks = event.getDrops();
        final Location location = event.getEntity().getLocation();

        Bukkit.getScheduler().runTaskLater(plugin(), task -> {
            Collection<Item> droppedItems = location.getNearbyEntitiesByType(Item.class, 2);
            for (Item item : droppedItems) {
                if (!itemStacks.contains(item.getItemStack())) continue;
                item.remove();
            }
        }, 2);
    }
}
