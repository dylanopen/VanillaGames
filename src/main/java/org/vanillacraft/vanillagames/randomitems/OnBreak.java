package org.vanillacraft.vanillagames.randomitems;

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
        if (!Game.isPlaying(player, "randomitems")) return;
        event.setDropItems(false);
        RandomItemsGame game = Game.fromPlayer(player);
        Material newDrop = game.dropMap.itemTransforms.get(event.getBlock().getType());
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(newDrop));
    }
}
