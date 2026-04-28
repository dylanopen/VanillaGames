package org.vanillacraft.vanillagames.party;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;

import java.util.ArrayList;

public class Party {
    public String name;
    public ArrayList<Player> players;
    public Game game;

    public Party(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void message(Component msg) {
        for (Player player : players) {
            player.sendMessage(msg);
        }
    }

    public void title(Component msg) {
        for (Player player : players) {
            player.showTitle(Title.title(msg, Component.empty()));
        }
    }

    public void message(String msg) {
        message(Component.text(msg));
    }
}
