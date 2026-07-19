package org.vanillacraft.vanillagames;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.lapisdev.craftgui.CraftGui;
import org.mvplugins.multiverse.core.MultiverseCoreApi;
import org.mvplugins.multiverse.netherportals.MultiverseNetherPortals;
import org.vanillacraft.vanillagames.command.game.GameCommandRegistry;
import org.vanillacraft.vanillagames.listener.VanillaGamesListener;
import org.vanillacraft.vanillagames.command.party.PartyCommandRegistry;
import org.vanillacraft.vanillagames.forwarding.ForwardingListener;
import org.vanillacraft.vanillagames.gamemode.randomitems.RandomItemsListener;
import org.vanillacraft.vanillagames.gamemode.teamrun.TeamRunListener;
import org.vanillacraft.vanillagames.world.Multiverse;


public class VanillaGames extends JavaPlugin {
    @Override
    public void onEnable() {
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, registry -> {
            PartyCommandRegistry.register(registry.registrar());
            GameCommandRegistry.register(registry.registrar());
        });

        VanillaGamesListener.handle();
        ForwardingListener.handle();
        RandomItemsListener.handle();
        TeamRunListener.handle();

        new CraftGui(this);
        Multiverse.core = MultiverseCoreApi.get();
        Multiverse.portals = (MultiverseNetherPortals)Bukkit.getPluginManager().getPlugin("Multiverse-NetherPortals");
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
