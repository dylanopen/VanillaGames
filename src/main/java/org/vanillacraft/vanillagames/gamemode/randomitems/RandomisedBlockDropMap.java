package org.vanillacraft.vanillagames.gamemode.randomitems;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RandomisedBlockDropMap {
    public HashMap<Material, Material> itemTransforms = new HashMap<>();

    public RandomisedBlockDropMap() {
        Material[] allMaterials = Material.values();
        ArrayList<Material> keyItems = new ArrayList<>();
        for (Material material : allMaterials) {
            if (!material.isBlock()) continue;
            keyItems.add(material);
        }
        Collections.shuffle(keyItems);
        ArrayList<Material> resultItems = new ArrayList<>();
        for (Material material : allMaterials) {
            if (!material.isItem()) continue;
            resultItems.add(material);
        }
        Collections.shuffle(resultItems);


        int minLength = Math.min(keyItems.size(), resultItems.size());
        for (int i = 0; i < minLength; i++) {
            itemTransforms.put(keyItems.get(i), resultItems.get(i));
        }
    }
}
