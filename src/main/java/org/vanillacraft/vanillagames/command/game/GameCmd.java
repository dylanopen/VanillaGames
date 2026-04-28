package org.vanillacraft.vanillagames.command.game;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.dylancode.craftgui.button.MenuItem;
import org.dylancode.craftgui.menu.ImmutableMenu;
import org.dylancode.craftgui.button.MenuButton;
import org.dylancode.craftgui.playermenu.PlayerMenus;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;
import org.vanillacraft.vanillagames.randomitems.RandomItemsGame;
import org.vanillacraft.vanillagames.sumo.SumoGame;
import org.vanillacraft.vanillagames.teamrun.TeamRunGame;

public class GameCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage("You must be a player to use the GUI game selector");
            return 1;
        }
        Party party = PartyList.getPartyByPlayer(player);

        ImmutableMenu menu = new ImmutableMenu(9, "Choose a gamemode");

        menu.setButton(0, new MenuButton(MenuItem.stack(
                Material.TURTLE_SCUTE, Component.text("Random Items", NamedTextColor.LIGHT_PURPLE)),
                () -> startGamemode(new RandomItemsGame(party), player)));

        menu.setButton(1, new MenuButton(MenuItem.stack(
                Material.LEATHER_BOOTS, Component.text("Sumo", NamedTextColor.GRAY)),
                () -> startGamemode(new SumoGame(party), player)));

        menu.setButton(2, new MenuButton(MenuItem.stack(
                Material.ENDER_EYE, Component.text("Teamrun", NamedTextColor.BLUE)),
                () -> startGamemode(new TeamRunGame(party), player)));

        menu.open(player);

        return 1;
    }

    private static void startGamemode(Game gameObject, Player player) {
        PlayerMenus.closeMenu(player);
        Party party = gameObject.getParty();
        party.game = gameObject;
    }
}
