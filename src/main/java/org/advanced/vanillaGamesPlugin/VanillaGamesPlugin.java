package org.advanced.vanillaGamesPlugin;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.advanced.vanillaGamesPlugin.Important.CommandRegistry;
import org.advanced.vanillaGamesPlugin.Important.EventRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaGamesPlugin extends JavaPlugin {
    public static VanillaGamesPlugin plugin;

    @Override
    public void onEnable() {
        VanillaGamesPlugin.plugin = this;
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, registry -> CommandRegistry.register(registry.registrar()));
        EventRegistry.register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
