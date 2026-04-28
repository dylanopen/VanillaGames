package org.dylancode.craftgui.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.dylancode.craftgui.button.MenuButton;
import org.dylancode.craftgui.playermenu.PlayerMenus;
import org.jetbrains.annotations.NotNull;

public class ImmutableMenu implements Menu, InventoryHolder {
    private final String title;
    private final int size;
    private final MenuButton[] buttons;
    private final Inventory inventory;
    private Player player;

    public ImmutableMenu(int size, String title) {
        this.size = size;
        this.title = title;
        this.buttons = new MenuButton[size];
        inventory = Bukkit.createInventory(this, size, title);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public MenuButton getButton(int slot) {
        if (slot < 0 || slot >= size) {
            return null;
        }
        return buttons[slot];
    }

    @Override
    public void setButton(int slot, MenuButton button) {
        if (slot < 0 || slot >= size) {
            throw new IllegalArgumentException("Slot must be between 0 and " + (size - 1));
        }
        this.buttons[slot] = button;
        inventory.setItem(slot, button.itemStack);
    }

    @Override
    public void open(Player player) {
        this.player = player;
        player.openInventory(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    @Override
    public void close() {
        if (player != null) {
            PlayerMenus.closeMenu(player);
        }
    }
}
