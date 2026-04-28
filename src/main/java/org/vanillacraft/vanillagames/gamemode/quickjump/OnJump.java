package org.vanillacraft.vanillagames.gamemode.quickjump;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.vanillacraft.vanillagames.game.Game;

public class OnJump implements Listener {
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        if (!Game.isPlaying(player, "quickjump")) return;
        QuickJumpGame game = Game.fromPlayer(player);
        if (game == null) return;

        int newScore = game.scores.get(player) + 1;
        game.scores.put(player, newScore);
        event.getPlayer().sendMessage("score: " + newScore);
    }
}
