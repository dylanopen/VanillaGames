package org.advanced.vanillaGamesPlugin.Molehunt;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.advanced.vanillaGamesPlugin.Important.GlobalVariables;
import org.advanced.vanillaGamesPlugin.Lobby.PartyData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.generator.structure.Structure;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.StructureSearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MolehuntStartCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Player player = (Player) ctx.getSource().getExecutor();
        String partyColour = ctx.getArgument("party colour", String.class);

        String partyKey = partyColour.toLowerCase();
        PartyData party = GlobalVariables.parties.get(partyKey);

        if (party == null)
        {
            player.sendMessage(Component.text("That party does not exist"));
            return Command.SINGLE_SUCCESS;
        }

        if (party.players.contains(player))
        {
            party.game = "molehunt";
            String worldName = LoadWorld();
            int border = 400 + party.players.size() * 40;
            Location strongholdLoc = SetSpawnAndBorder(worldName, party.players, border);
            party.gameSpawn = strongholdLoc;
            TeleportPlayers(worldName, strongholdLoc, party.players, border);
            party.molehuntRunners = new ArrayList<>(party.players);
            Random random = new Random();
            int moleCount = Math.max(1, party.players.size() / 4);
            for (int i = 0; i < moleCount; i++) {
                if (party.molehuntRunners.isEmpty()){
                    break;
                }
                int speedrunnerIndex = random.nextInt(0, party.molehuntRunners.size());
                Player mole = party.molehuntRunners.remove(speedrunnerIndex);
                party.molehuntMoles.add(mole);
            }
            AnnounceRoles(party.molehuntRunners, party.molehuntMoles);
            for (Player players : party.players){
                players.setGameMode(GameMode.SURVIVAL);
                players.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1200, 0));
            }
        }
        else
        {
            player.sendMessage(Component.text("You are not in " + partyKey + " party"));
        }
        return Command.SINGLE_SUCCESS;
    }

    public static String LoadWorld()
    {
        Random rand = new Random();
        int randNum = rand.nextInt(0,1000);
        String worldName = "molehunt world " +randNum;
        WorldCreator creator = new WorldCreator(worldName);
        World world = creator.createWorld();
        return worldName;
    }

    public static Location SetSpawnAndBorder(String worldName, List<Player> players, int border)
    {
        World world = Bukkit.getWorld(worldName);
        Location spawn = new Location(world, 0, 64, 0);
        StructureSearchResult result = world.locateNearestStructure(spawn, Structure.STRONGHOLD, 100, false);
        Location strongholdLoc = result.getLocation();
        world.setSpawnLocation(strongholdLoc);
        world.getWorldBorder().setCenter(strongholdLoc);
        world.getWorldBorder().setSize(border);
        return strongholdLoc;
    }

    public static void TeleportPlayers(String worldName, Location strongholdLoc, List<Player> playerList, int border)
    {
        for (Player players : playerList){
            Random random = new Random();
            double XborderMax = strongholdLoc.getX() + ((double) border / 2);
            double XborderMin = strongholdLoc.getX() - ((double) border / 2);
            double ZboarderMax = strongholdLoc.getZ() + ((double) border / 2);
            double ZborderMin = strongholdLoc.getZ() - ((double) border / 2);
            int xcoord = (random.nextInt((int)XborderMin, (int)XborderMax));
            int zcoord = (random.nextInt((int)ZborderMin, (int)ZboarderMax));
            players.teleport(new Location(Bukkit.getWorld(worldName), xcoord, 200, zcoord));
        }
    }

    public static void AnnounceRoles(List<Player> speedrunners, List<Player> moles) {
        StringBuilder otherMolesMessageBuilder = new StringBuilder();
        for (Player mole : moles) {
            mole.sendMessage(Component.text("You are a MOLE!", NamedTextColor.RED));
            if (moles.size() == 1) {
                mole.sendMessage(Component.text("There are no other moles to help you: good luck.", NamedTextColor.BLUE));
                mole.sendMessage(Component.text("Your role is to go undercover and sabotage the speedrunners - don't let them beat the game!", NamedTextColor.BLUE));
                mole.showTitle(Title.title(Component.text("You are a MOLE!", NamedTextColor.RED), Component.text("You are the only mole", NamedTextColor.BLUE)));
            } else {
                mole.sendMessage(Component.text("The moles are: ", NamedTextColor.BLUE));
                mole.sendMessage(Component.text("Your role is to go undercover and sabotage the speedrunners - don't let them beat the game!", NamedTextColor.BLUE));
                mole.showTitle(Title.title(Component.text("You are a MOLE!", NamedTextColor.RED), Component.text("Moles: ", NamedTextColor.BLUE)));
            }
        }
        for (Player speedrunner : speedrunners) {
            speedrunner.sendMessage(Component.text("You are a SPEEDRUNNER!", NamedTextColor.GREEN));
            speedrunner.sendMessage(Component.text("Watch your back - anyone could be a mole that is trying to stop you from beating the game", NamedTextColor.BLUE));
            speedrunner.showTitle(Title.title(Component.text("You are a SPEEDRUNNER!", NamedTextColor.GREEN), Component.text("Try to kill the ender dragon", NamedTextColor.BLUE)));
        }
    }
}
