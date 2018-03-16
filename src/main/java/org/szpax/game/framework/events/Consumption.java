package org.szpax.game.framework.events;

import org.szpax.game.framework.api.Event;
import org.szpax.game.framework.api.EventBuilder;
import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.model.Material;
import org.szpax.game.framework.calculators.CalculationKey;
import org.szpax.game.framework.api.EventRequirement;

import java.util.HashSet;
import java.util.Set;

import static org.szpax.game.framework.calculators.model.MaterialCalculationType.CONSUMPTION;

public class Consumption implements Event {

    private final Material material;

    public Consumption(Material material) {
        this.material = material;
    }

    @Override
    public void takesPlaceIn(Realm realm) {
        CalculationKey materialProduction = CalculationKey.process(CONSUMPTION)
                .of(material)
                .build();

        realm.getStorage().add(material,
                realm.world().calculations().get(
                        materialProduction)
                        .in(realm)
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
