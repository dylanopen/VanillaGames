package org.dylancode.craftgui.listener;

import org.bukkit.event.Listener;
import org.dylancode.craftgui.CraftGui;

public class CraftGuiListeners {
    public static void register() {
        handle(new InventoryClickListener());
        handle(new InventoryCloseListener());
    }

    public static void handle(Listener listener) {
        CraftGui.plugin.getServer().getPluginManager().registerEvents(listener, CraftGui.plugin);
    }
}
