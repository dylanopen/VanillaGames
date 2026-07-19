package org.vanillacraft.vanillagames.command.game;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;

public class GameStopCmd {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage("You must be a player to stop a game");
            return Command.SINGLE_SUCCESS;
        }

        Party party = PartyList.getPartyByPlayer(player);
        if (party == null) {
            ctx.getSource().getSender().sendMessage("You must be in a party to stop a game");
            return Command.SINGLE_SUCCESS;
        }

        if (!party.players.getFirst().equals(player)) {
            ctx.getSource().getSender().sendMessage("You are not the party leader so cannot stop games");
            return Command.SINGLE_SUCCESS;
        }

        Game oldGame = party.game;
        if (oldGame == null) {
            ctx.getSource().getSender().sendMessage("Your party is not in a game");
            return Command.SINGLE_SUCCESS;
        }
        ctx.getSource().getSender().sendMessage("Stopped game " + oldGame.name);
        party.game = null;

        return Command.SINGLE_SUCCESS;
    }
}
