package org.vanillacraft.vanillagames.gamemode.sumo;

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
        if (!(Game.fromPlayer(player) instanceof SumoGame game)) return;
        if (player.getLocation().y() >= -61.0) return;
        if (player.getGameMode().equals(GameMode.SPECTATOR)) return;
        if (!game.roundActive) return;

        game.killPlayer(player);

    }
}
