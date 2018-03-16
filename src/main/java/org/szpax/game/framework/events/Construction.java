package org.szpax.game.framework.events;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.framework.api.Event;
import org.szpax.game.framework.api.EventBuilder;
import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.model.Building;
import org.szpax.game.framework.model.Material;
import org.szpax.game.framework.api.EventRequirement;
import org.szpax.game.world.events.requirements.MaterialRequirement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class Construction implements Event {
    private final Set<EventRequirement> requirements;

    private final Building building;
    private final Map<Material, Double> requiredMaterials;

    public Construction(Building building, Map<Material, Double> requiredMaterials, Set<EventRequirement> requirements) {
        this.building = building;
        this.requiredMaterials = requiredMaterials;
        this.requirements = requirements;
    }

    public static Builder of(Building building) {
        return new Builder(building);
    }

    @Override
    public void takesPlaceIn(Realm realm) {
        log.info("Construction event firing. Building {}. Required materials: {}", building, requiredMaterials);
        requiredMaterials.forEach((key, value) -> realm.getStorage().take(key, value));
        realm.getBuildings().add(building, 1d);
    }

    @Override
    public Set<EventRequirement> requirements() {
        return requirements;
    }

    public static class Builder implements EventBuilder {
        private final Map<Material, Double> requiredMaterials;
        private final Set<EventRequirement> eventRequirements;
        private final Building building;

        private Builder(Building occupation) {
            this.requiredMaterials = new HashMap<>();
            this.building = occupation;
            this.eventRequirements = new HashSet<>();
        }

        public Builder requires(Double amount, Material material) {
            requiredMaterials.put(material, amount);
            return this;
        }

        public Builder requires(EventRequirement requirement) {
            eventRequirements.add(requirement);
            return this;
        }

        public Construction build() {
            eventRequirements.add(new MaterialRequirement(requiredMaterials));
            return new Construction(building, requiredMaterials, eventRequirements);
        }
    }
}
