package org.vanillacraft.vanillagames.forwarding;

import org.bukkit.Bukkit;

public class CommandRunner {
    public static boolean runCommand(String command) {
        return Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
