package org.szpax.game.world.events.events;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Building;
import org.szpax.game.world.assets.Material;
import org.szpax.game.world.calculators.Calculations;
import org.szpax.game.world.events.requirements.EventRequirement;
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
    public void takesPlaceIn(Kingdom kingdom) {
        log.info("Construction event firing. Building {}. Required materials: {}", building, requiredMaterials);
        requiredMaterials.forEach((key, value) -> kingdom.getStorage().take(key, value));
        kingdom.getBuildings().add(building, 1d);
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
