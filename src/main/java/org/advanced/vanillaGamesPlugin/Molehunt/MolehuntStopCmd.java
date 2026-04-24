package org.advanced.vanillaGamesPlugin.Molehunt;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.advanced.vanillaGamesPlugin.Important.GlobalFunctions;
import org.advanced.vanillaGamesPlugin.Important.GlobalVariables;
import org.advanced.vanillaGamesPlugin.Lobby.PartyData;
import org.bukkit.entity.Player;

public class MolehuntStopCmd {
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

        if (party.players.contains(player)){
            GlobalFunctions.EndOfMolehunt(player);
        }

        else
        {
            player.sendMessage(Component.text("You are not in " + partyKey + " party"));
        }
        return Command.SINGLE_SUCCESS;
    }
}
