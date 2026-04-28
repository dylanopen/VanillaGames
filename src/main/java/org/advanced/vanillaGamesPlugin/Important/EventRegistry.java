package org.advanced.vanillaGamesPlugin.Important;

import org.advanced.vanillaGamesPlugin.Events.JoinEvent;
import org.advanced.vanillaGamesPlugin.Events.KillDragon;
import org.advanced.vanillaGamesPlugin.Events.RespawnTpAndGamemode;
import org.advanced.vanillaGamesPlugin.VanillaGamesPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class EventRegistry {
    public static void handle(Listener listener){
        Bukkit.getServer().getPluginManager().registerEvents(listener, VanillaGamesPlugin.plugin);
    }

    public static void register() {
        handle(new JoinEvent());
        handle(new KillDragon());
        handle(new RespawnTpAndGamemode());
    }
}