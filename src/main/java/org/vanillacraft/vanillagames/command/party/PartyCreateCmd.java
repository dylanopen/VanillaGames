package org.vanillacraft.vanillagames.command.party;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class PartyCreateCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        String partyName = ctx.getArgument("name", String.class);

        if (PartyList.getPartyByName(partyName) != null) {
            ctx.getSource().getSender().sendMessage("There is already a party with that name!");
            return Command.SINGLE_SUCCESS;
        }
        Party party = new Party(partyName);

        if (ctx.getSource().getExecutor() instanceof Player player) {
            party.players.add(player);
        }

        PartyList.addParty(party);

        ctx.getSource().getSender().sendMessage("Successfully created party " + party.name);

        return Command.SINGLE_SUCCESS;
    }
}
