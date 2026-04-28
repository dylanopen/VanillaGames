package org.dylancode.craftgui.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.dylancode.craftgui.menu.Menu;
import org.dylancode.craftgui.playermenu.PlayerMenus;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof Menu) {
            Menu menu = (Menu) event.getInventory().getHolder();
            if (PlayerMenus.getMenuOwner(menu) != null) {
                PlayerMenus.closeMenu(menu);
            }
        }
    }
}
