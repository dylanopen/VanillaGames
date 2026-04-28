package org.dylancode.craftgui.button;

import org.bukkit.inventory.ItemStack;

public class MenuButton {
    public final ItemStack itemStack;
    final Runnable onClick;

    public MenuButton(ItemStack itemStack, Runnable onClick) {
        this.itemStack = itemStack;
        this.onClick = onClick;
    }

    public void click() {
        onClick.run();
    }
}
