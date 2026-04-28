package org.vanillacraft.vanillagames.command.party;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class PartyJoinCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage("You must be a player to join a party");
            return Command.SINGLE_SUCCESS;
        }

        String partyName = ctx.getArgument("name", String.class);
        Party party = PartyList.getPartyByName(partyName);
        if (party == null) {
            ctx.getSource().getSender().sendMessage("That party doesn't exist");
            return Command.SINGLE_SUCCESS;
        }
        Party playerParty = PartyList.getPartyByPlayer(player);
        if (playerParty != null) {
            ctx.getSource().getSender().sendMessage("You are already in the " + playerParty.name + " party");
            return Command.SINGLE_SUCCESS;
        }

        party.message(player.getName() + " has joined the party");
        party.addPlayer(player);

        ctx.getSource().getSender().sendMessage("You have joined party " + party.name + ", hosted by " + party.players.getFirst().getName());

        return Command.SINGLE_SUCCESS;
    }
}
