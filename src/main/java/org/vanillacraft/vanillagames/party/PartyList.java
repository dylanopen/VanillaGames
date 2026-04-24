package org.vanillacraft.vanillagames.party;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartyList {
    public static ArrayList<Party> parties = new ArrayList<>();

    public static void addParty(Party party) {
        party.name = party.name.toLowerCase();
        parties.add(party);
    }

    public static Party getPartyByName(String name) {
        for (Party party : parties) {
            if (party.name.equals(name.toLowerCase())) {
                return party;
            }
        }
        return null;
    }

    public static void removeParty(Party party) {
        parties.remove(party);
    }

    public static Party getPartyByPlayer(Player player) {
        for (Party party : parties) {
            if (party.players.contains(player)) {
                return party;
            }
        }
        return null;
    }
}
