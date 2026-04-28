package org.vanillacraft.vanillagames.teamrun;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TeamRunGame implements Game {
    Party party;
    World world;
    int numTeams = 2;
    ArrayList<ArrayList<Player>> teams = new ArrayList<>();


    public TeamRunGame(Party party) {
        for (int i = 0; i < numTeams; i++){
            teams.add(new ArrayList<>());
        }

        ArrayList<Player> players = new ArrayList<>(party.players);
        Collections.shuffle(players);

        int teamSize = players.size() / numTeams;
        int teamExtra = players.size() % numTeams;

        int assignedPlayers = 0;
        for (int teamNumber = 0; teamNumber < numTeams; teamNumber++) {
            int lastPlayerInTeam = assignedPlayers + teamSize;
            if (teamNumber < teamExtra) lastPlayerInTeam++;
            for (int playerIndex = assignedPlayers; playerIndex < lastPlayerInTeam; playerIndex++) {
                teams.get(teamNumber).add(players.get(playerIndex));
            }
        }

        this.party = party;
        world = generateWorld();
        init(party);
    }

    @Override
    public String name() {
        return "teamrun";
    }

    @Override
    public void onJoin(Player player) {
        ArrayList<Player> team = null;
        for (ArrayList<Player> currentTeam : teams) {
            if (currentTeam.contains(player)) {
                team = currentTeam;
            }
        }
        Random rand = new Random();
        double teamX = world.getSpawnLocation().getX() + rand.nextInt(-100, 100);
        double teamY = world.getSpawnLocation().getY() + rand.nextInt(-100, 100);
        double teamZ = world.getSpawnLocation().getZ() + rand.nextInt(-100, 100);
        Location teamSpawn = new Location(world, teamX, teamY,teamZ);
        String playerList = "";
        for (Player teamPlayer : team) {
            teamPlayer.teleportAsync(teamSpawn);
            if (!(teamPlayer == player)) {
                playerList += teamPlayer.getName() + ", ";
            }
        }
        player.sendMessage("You are in a team with " + playerList);


    }

    @Override
    public void onLeave(Player player) {

    }

    @Override
    public void onRejoin(Player player) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public Party getParty() {
        return party;
    }
}
