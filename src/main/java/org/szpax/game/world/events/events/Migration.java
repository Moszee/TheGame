package org.szpax.game.world.events.events;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Material;
import org.szpax.game.world.assets.Occupation;
import org.szpax.game.world.events.requirements.EventRequirement;
import org.szpax.game.world.events.requirements.MaterialRequirement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Migration implements Event {
    private final Map<Material, Integer> requiredMaterials;
    private final Occupation occupation;
    private final Set<EventRequirement>  requirements;

    Migration(Map<Material, Integer> requiredMaterials, Occupation occupation, Set<EventRequirement> requirements) {
        this.requiredMaterials = requiredMaterials;
        this.occupation = occupation;
        this.requirements = new HashSet<>(requirements);
    }

    @Override
    public Set<EventRequirement> requirements() {
        return requirements;
    }

    @Override
    public void takesPlaceIn(Kingdom kingdom) {
        requiredMaterials.forEach((key, value) -> kingdom.getStorage().take(key, value));
        kingdom.getPopulation().add(occupation, 1);
    }

    public static Builder of(Occupation occupation) {
        return new Builder(occupation);
    }

    public static class Builder {
        private final Map<Material, Integer> requiredMaterials;
        private final Set<EventRequirement> eventRequirements;
        private final Occupation occupation;

        private Builder(Occupation occupation) {
            this.requiredMaterials = new HashMap<>();
            this.occupation = occupation;
            this.eventRequirements = new HashSet<>();
        }

        public Builder requires(Integer amount, Material material) {
            requiredMaterials.put(material, amount);
            return this;
        }

        public Builder requires(EventRequirement requirement) {
            eventRequirements.add(requirement);
            return this;
        }

        public Migration build() {
            eventRequirements.add(new MaterialRequirement(requiredMaterials));
            return new Migration(requiredMaterials, occupation, eventRequirements);
        }
    }
}
