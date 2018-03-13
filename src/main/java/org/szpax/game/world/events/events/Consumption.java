package org.szpax.game.world.events.events;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Material;
import org.szpax.game.world.calculators.CalculationKey;
import org.szpax.game.world.calculators.Calculations;
import org.szpax.game.world.events.requirements.EventRequirement;

import java.util.HashSet;
import java.util.Set;

import static org.szpax.game.world.calculators.model.MaterialCalculationType.CONSUMPTION;

public class Consumption implements Event {

    private final Material material;

    public Consumption(Material material) {
        this.material = material;
    }

    @Override
    public void takesPlaceIn(Kingdom kingdom) {
        CalculationKey materialProduction = CalculationKey.process(CONSUMPTION)
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

        public Consumption build() {
            return new Consumption(material);
        }
    }
}
