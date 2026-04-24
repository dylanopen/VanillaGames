package org.advanced.vanillaGamesPlugin.Lobby;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.advanced.vanillaGamesPlugin.Important.GlobalVariables;
import org.bukkit.entity.Player;

public class PartyListCmd {
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
        else
        {
            for (Player players : party.players)
            {
                player.sendMessage(players.name());
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
