package org.vanillacraft.vanillagames.command.game;

import com.mojang.brigadier.arguments.StringArgumentType;
import io.papermc.paper.command.brigadier.Commands;

public class GameCommandRegistry {
    public static void register(Commands registry) {
        registry.register(Commands.literal("game")
                .then(Commands.argument("gamemode", StringArgumentType.string())
                        .executes(GameCmd::execute)
                ).then(Commands.literal("stop")
                                .executes(GameStopCmd::execute)
                ).build());
    }
}
