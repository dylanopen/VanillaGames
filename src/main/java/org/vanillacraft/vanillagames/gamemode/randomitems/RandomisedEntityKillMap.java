package org.vanillacraft.vanillagames.gamemode.randomitems;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RandomisedEntityKillMap {
    public HashMap<EntityType, Material> itemTransforms = new HashMap<>();

    public RandomisedEntityKillMap() {
        EntityType[] allEntities = EntityType.values();
        Material[] allMaterials = Material.values();

        ArrayList<EntityType> keyItems = new ArrayList<>();
        for (EntityType entityType : allEntities) {
            if (!entityType.isAlive()) continue;
            keyItems.add(entityType);
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
