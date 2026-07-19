package org.vanillacraft.vanillagames.gamemode.randomitems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.vanillacraft.vanillagames.game.Game;

public class OnBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!(Game.fromPlayer(player) instanceof RandomItemsGame game)) return;

        event.setDropItems(false);
        Material newDrop = game.dropMap.itemTransforms.get(event.getBlock().getType());
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(newDrop));
    }
}
