package org.szpax.game.world.events.requirements;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Material;

import java.util.Map;

public class MaterialRequirement implements EventRequirement {
    private final Map<Material, Integer> requiredMaterials;

    public MaterialRequirement(Map<Material, Integer> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
    }

    @Override
    public boolean canTakePlaceIn(Kingdom kingdom) {
        return requiredMaterials.entrySet().stream().allMatch(entry ->
                kingdom.getStorage().get(entry.getKey()) >= entry.getValue()
        );
    }
}