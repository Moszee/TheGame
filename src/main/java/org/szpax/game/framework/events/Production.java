package org.szpax.game.framework.events;

import org.szpax.game.framework.api.Event;
import org.szpax.game.framework.api.EventBuilder;
import org.szpax.game.framework.model.Kingdom;
import org.szpax.game.framework.model.Material;
import org.szpax.game.framework.calculators.CalculationKey;
import org.szpax.game.framework.api.EventRequirement;

import java.util.HashSet;
import java.util.Set;

import static org.szpax.game.framework.calculators.model.MaterialCalculationType.PRODUCTION;

public class Production implements Event {

    private final Material material;

    public Production(Material material) {
        this.material = material;
    }

    @Override
    public void takesPlaceIn(Kingdom kingdom) {
        CalculationKey materialProduction = CalculationKey.process(PRODUCTION)
                .of(material)
                .build();

        kingdom.getStorage().add(material,
                kingdom.world().calculations().get(
                        materialProduction)
                        .in(kingdom)
                        .sum());
    }

    @Override
    public Set<EventRequirement> requirements() {
        return new HashSet<>();
    }

    public static Builder of(Material material) {
        return new Builder(material);
    }

    public static class Builder implements EventBuilder {

        private Material material;

        private Builder(Material material) {
            this.material = material;
        }

        public Production build() {
            return new Production(material);
        }
    }
}
