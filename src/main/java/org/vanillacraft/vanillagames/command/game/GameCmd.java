package org.vanillacraft.vanillagames.command.game;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.vanillacraft.vanillagames.game.Game;
import org.vanillacraft.vanillagames.party.Party;
import org.vanillacraft.vanillagames.party.PartyList;
import org.vanillacraft.vanillagames.quickjump.QuickJumpGame;
import org.vanillacraft.vanillagames.randomitems.RandomItemsGame;

public class GameCmd {
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

        if (!party.players.getFirst().equals(player)) {
            ctx.getSource().getSender().sendMessage("You are not the party leader so cannot start games");
            return Command.SINGLE_SUCCESS;
        }

        String gamemode = ctx.getArgument("gamemode", String.class);

        Game partyGame = switch (gamemode) {
            case "quickjump" -> new QuickJumpGame(party);
            case "randomitems" -> new RandomItemsGame(party);
            default -> null;
        };
        if (partyGame == null) {
            ctx.getSource().getSender().sendMessage("That minigame does not exist");
            return Command.SINGLE_SUCCESS;
        }
        party.game = partyGame;

        return Command.SINGLE_SUCCESS;
    }
}
