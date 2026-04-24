package org.vanillacraft.vanillagames.command.party;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class PartyPlayersCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage("Only players can execute this command");
            return Command.SINGLE_SUCCESS;
        }
        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) {
            ctx.getSource().getSender().sendMessage("You are not in a party");
            return Command.SINGLE_SUCCESS;
        }
        ctx.getSource().getSender().sendMessage("There are " + party.players.size() + " players in your party (" + party.name + "):");
        for (Player partyPlayer : party.players) {
            ctx.getSource().getSender().sendMessage("- " + partyPlayer.getName());
        }
        return Command.SINGLE_SUCCESS;
    }
}
