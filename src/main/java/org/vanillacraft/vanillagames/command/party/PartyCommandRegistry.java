package org.vanillacraft.vanillagames.command.party;

import com.mojang.brigadier.arguments.StringArgumentType;
import io.papermc.paper.command.brigadier.Commands;
import org.vanillacraft.vanillagames.command.game.GameCliCmd;
import org.vanillacraft.vanillagames.command.game.GameStopCmd;

public class PartyCommandRegistry {
    public static void register(Commands registry) {
        registry.register(Commands.literal("party")
                .then(Commands.literal("create")
                        .then(Commands.argument("name", StringArgumentType.string())
                                .executes(PartyCreateCmd::execute)))
                .then(Commands.literal("play")
                        .then(Commands.argument("gamemode", StringArgumentType.string())
                                .executes(GameCliCmd::execute)))
                .then(Commands.literal("join")
                        .then(Commands.argument("name", StringArgumentType.string())
                                .executes(PartyJoinCmd::execute)))
                .then(Commands.literal("list")
                        .executes(PartyListCmd::execute))
                .then(Commands.literal("players")
                                .executes(PartyPlayersCmd::execute))
                .then(Commands.literal("stop")
                        .executes(GameStopCmd::execute))
                .build());

    }
}
