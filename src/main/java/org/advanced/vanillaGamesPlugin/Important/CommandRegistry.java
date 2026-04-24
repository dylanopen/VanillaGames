package org.advanced.vanillaGamesPlugin.Important;

import com.mojang.brigadier.arguments.StringArgumentType;
import io.papermc.paper.command.brigadier.Commands;
import org.advanced.vanillaGamesPlugin.Lobby.PartyJoinCmd;
import org.advanced.vanillaGamesPlugin.Lobby.PartyListCmd;
import org.advanced.vanillaGamesPlugin.Molehunt.MolehuntStartCmd;
import org.advanced.vanillaGamesPlugin.Molehunt.MolehuntStopCmd;
import org.advanced.vanillaGamesPlugin.PvP.PvPStartCmd;

public class CommandRegistry {
    public static void register(Commands r) {
        r.register(Commands.literal("join")
                .then(Commands.argument("party colour", StringArgumentType.string())
                        .executes(PartyJoinCmd::execute))
                .build());

        r.register(Commands.literal("partylist")
                .then(Commands.argument("party colour", StringArgumentType.string())
                        .executes(PartyListCmd::execute))
                .build());

        r.register(Commands.literal("start")
                .then(Commands.literal("molehunt")
                        .then(Commands.argument("party colour", StringArgumentType.string())
                            .executes(MolehuntStartCmd::execute)))
                .build());

        r.register(Commands.literal("stop")
                .then(Commands.literal("molehunt")
                        .then(Commands.argument("party colour", StringArgumentType.string())
                                .executes(MolehuntStopCmd::execute)))
                .then(Commands.literal("pvp")
                        .then(Commands.argument("party colour", StringArgumentType.string())
                                .executes(PvPStartCmd::execute)))
                .build());
    }
}