package org.advanced.vanillaGamesPlugin.Lobby;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class PartyData {
    public List<Player> players;
    public NamedTextColor colour;
    public String game;
    public List<Player> molehuntMoles;
    public List<Player> molehuntRunners;
    public Location gameSpawn;

    public PartyData(List<Player> players, NamedTextColor colour, String game, Location gameSpawn, List<Player> molehuntMoles, List<Player> molehuntRunners) {
        this.players = players;
        this.colour = colour;
        this.game = game;
        this.molehuntMoles = molehuntMoles;
        this.molehuntRunners = molehuntRunners;
        this.gameSpawn = gameSpawn;
    }
}
