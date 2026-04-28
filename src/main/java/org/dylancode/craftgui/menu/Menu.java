package org.dylancode.craftgui.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.dylancode.craftgui.button.MenuButton;

public interface Menu extends InventoryHolder {
    int getSize();
    String getTitle();
    MenuButton getButton(int slot);
    void setButton(int slot, MenuButton button);
    void open(Player player);

    void close();
}
