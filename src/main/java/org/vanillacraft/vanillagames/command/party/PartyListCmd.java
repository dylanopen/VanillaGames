package org.vanillacraft.vanillagames.command.party;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class PartyListCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        ctx.getSource().getSender().sendMessage("There are " + PartyList.parties.size() + " parties:");
        for (Party party : PartyList.parties) {
            ctx.getSource().getSender().sendMessage("- " + party.name);
        }
        return Command.SINGLE_SUCCESS;
    }
}
