package org.advanced.vanillaGamesPlugin.Lobby;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.advanced.vanillaGamesPlugin.Important.GlobalFunctions;
import org.advanced.vanillaGamesPlugin.Important.GlobalVariables;
import org.bukkit.entity.Player;

public class PartyJoinCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Player player = (Player) ctx.getSource().getExecutor();
        String partyColour = ctx.getArgument("party colour", String.class);

        String partyKey = partyColour.toLowerCase();
        PartyData party = GlobalVariables.parties.get(partyKey);

        if (party == null) {
            player.sendMessage(Component.text("That party does not exist"));
            return Command.SINGLE_SUCCESS;
        }
        if (party.players.contains(player))
        {
            party.players.remove(player);
            player.sendMessage(Component.text("You have left the " + partyKey + " party"));
            player.playerListName(Component.text(player.getName()));
        }
        else if (GlobalFunctions.CheckPlayerParty(player) != ""){
            player.sendMessage(Component.text("You are already in another party"));
        }
        else
        {
            party.players.add(player);
            player.sendMessage(Component.text("You have joined the " + partyKey + " party"));
            player.playerListName(Component.text(player.getName(), party.colour));
        }
        return Command.SINGLE_SUCCESS;
    }
}