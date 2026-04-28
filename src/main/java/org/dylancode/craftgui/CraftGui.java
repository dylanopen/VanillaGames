package org.dylancode.craftgui;

import org.bukkit.plugin.Plugin;
import org.dylancode.craftgui.listener.CraftGuiListeners;

public class CraftGui {
    public static Plugin plugin;

    public CraftGui(Plugin plugin) {
        CraftGui.plugin = plugin;
        CraftGuiListeners.register();
    }
}
