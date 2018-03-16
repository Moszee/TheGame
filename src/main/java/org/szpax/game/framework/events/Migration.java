package org.szpax.game.framework.events;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.framework.api.Event;
import org.szpax.game.framework.api.EventBuilder;
import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.model.Material;
import org.szpax.game.framework.model.Occupation;
import org.szpax.game.framework.api.EventRequirement;
import org.szpax.game.world.events.requirements.MaterialRequirement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class Migration implements Event {
    private final Map<Material, Double> requiredMaterials;
    private final Occupation occupation;
    private final Set<EventRequirement>  requirements;

    Migration(Map<Material, Double> requiredMaterials, Occupation occupation, Set<EventRequirement> requirements) {
        this.requiredMaterials = requiredMaterials;
        this.occupation = occupation;
        this.requirements = new HashSet<>(requirements);
    }

    @Override
    public Set<EventRequirement> requirements() {
        return requirements;
    }

    @Override
    public void takesPlaceIn(Realm realm) {
        log.info("Migration event firing. Migrating {}. Required materials: {}", occupation, requiredMaterials);
        requiredMaterials.forEach((key, value) -> realm.getStorage().take(key, value));
        realm.getPopulation().add(occupation, 1d);
    }

    public static Builder of(Occupation occupation) {
        return new Builder(occupation);
    }

    public static class Builder implements EventBuilder {
        private final Map<Material, Double> requiredMaterials;
        private final Set<EventRequirement> eventRequirements;
        private final Occupation occupation;

        private Builder(Occupation occupation) {
            this.requiredMaterials = new HashMap<>();
            this.occupation = occupation;
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

        public Migration build() {
            eventRequirements.add(new MaterialRequirement(requiredMaterials));
            return new Migration(requiredMaterials, occupation, eventRequirements);
        }
    }
}
