package org.szpax.game.world.events.requirements;

import org.szpax.game.framework.api.EventRequirement;
import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.model.Material;

import java.util.Map;

public class MaterialRequirement implements EventRequirement {
    private final Map<Material, Double> requiredMaterials;

    public MaterialRequirement(Map<Material, Double> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
    }

    @Override
    public boolean canTakePlaceIn(Realm realm) {
        return requiredMaterials.entrySet().stream().allMatch(entry ->
                realm.getStorage().get(entry.getKey()) >= entry.getValue()
        );
    }
}