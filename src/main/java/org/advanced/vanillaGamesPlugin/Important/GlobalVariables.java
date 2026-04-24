package org.advanced.vanillaGamesPlugin.Important;

import net.kyori.adventure.text.format.NamedTextColor;
import org.advanced.vanillaGamesPlugin.Lobby.PartyData;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlobalVariables {

    public static Location lobbySpawn = new Location(Bukkit.getWorld("world"), 8, -60, 8);

    public static int numberOfParties = 4;

    public static HashMap<String, PartyData> parties = new HashMap<>(Map.of(
            "blue", new PartyData(new ArrayList<>(), NamedTextColor.BLUE, "", new Location(null,0,0,0), new ArrayList<>(), new ArrayList<>()),
            "red", new PartyData(new ArrayList<>(), NamedTextColor.RED, "", new Location(null,0,0,0), new ArrayList<>(), new ArrayList<>()),
            "green", new PartyData(new ArrayList<>(), NamedTextColor.GREEN, "", new Location(null,0,0,0), new ArrayList<>(), new ArrayList<>()),
            "yellow", new PartyData(new ArrayList<>(), NamedTextColor.YELLOW, "", new Location(null,0,0,0), new ArrayList<>(), new ArrayList<>())
    ));
}
