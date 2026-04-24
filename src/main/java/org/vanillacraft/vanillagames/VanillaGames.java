package org.vanillacraft.vanillagames;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.vanillacraft.vanillagames.command.game.GameCommandRegistry;
import org.vanillacraft.vanillagames.command.party.PartyCommandRegistry;
import org.vanillacraft.vanillagames.forwarding.ForwardingListener;
import org.vanillacraft.vanillagames.quickjump.QuickJumpListeners;
import org.vanillacraft.vanillagames.randomitems.RandomItemsListener;


public class VanillaGames extends JavaPlugin {
    @Override
    public void onEnable() {
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, registry -> {
            PartyCommandRegistry.register(registry.registrar());
            GameCommandRegistry.register(registry.registrar());
        });

        ForwardingListener.handle();
        QuickJumpListeners.handle();
        RandomItemsListener.handle();
    }

    public static void handleListener(Listener listener){
        Bukkit.getServer().getPluginManager().registerEvents(listener, VanillaGames.getPlugin(VanillaGames.class));
    }

    public static VanillaGames plugin() {
        return VanillaGames.getPlugin(VanillaGames.class);
    }

    @Override
    public void onDisable() {

    }
}
