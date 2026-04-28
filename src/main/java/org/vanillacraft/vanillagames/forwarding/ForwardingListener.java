package org.vanillacraft.vanillagames.forwarding;

import org.bukkit.event.Listener;

import static org.vanillacraft.vanillagames.VanillaGames.handleListener;

public class ForwardingListener implements Listener {
    public static void handle() {
        handleListener(new OnChat());
        handleListener(new OnAdvancement());
    }
}
