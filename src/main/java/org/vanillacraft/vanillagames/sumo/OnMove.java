package org.vanillacraft.vanillagames.sumo;

import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.vanillacraft.vanillagames.game.Game;

public class OnMove implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!Game.isPlaying(player, "sumo")) return;
        if (player.getLocation().y() >= -61.0) return;
        if (player.getGameMode().equals(GameMode.SPECTATOR)) return;
        SumoGame game = Game.fromPlayer(player);
        if (!game.roundActive) return;

        game.killPlayer(player);

    }
}
