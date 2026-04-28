package org.dylancode.craftgui.playermenu;

import org.bukkit.entity.Player;
import org.dylancode.craftgui.menu.Menu;

import java.util.HashMap;

public class PlayerMenus {
    public static final HashMap<Player, Menu> openMenus = new HashMap<>();

    public static void openMenu(Player player, Menu menu) {
        menu.open(player);
        openMenus.put(player, menu);
    }

    public static void closeMenu(Player player) {
        player.closeInventory();
        openMenus.remove(player);
    }

    public static void closeMenu(Menu menu) {
        for (Player player : openMenus.keySet()) {
            if (openMenus.get(player) == menu) {
                closeMenu(player);
                break;
            }
        }
    }

    public static Menu getOpenMenu(Player player) {
        return openMenus.get(player);
    }

    public static Player getMenuOwner(Menu menu) {
        for (Player player : openMenus.keySet()) {
            if (openMenus.get(player) == menu) {
                return player;
            }
        }
        return null;
    }
}
