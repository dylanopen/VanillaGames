package org.dylancode.craftgui.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.dylancode.craftgui.menu.ImmutableMenu;
import org.dylancode.craftgui.menu.Menu;
import org.dylancode.craftgui.button.MenuButton;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Menu) {
            Menu menu = (Menu) event.getInventory().getHolder();
            if (menu instanceof ImmutableMenu) {
                event.setCancelled(true);
            }
            MenuButton menuButton = menu.getButton(event.getRawSlot());
            if (menuButton != null) {
                menuButton.click();
            }
        }
    }
}
