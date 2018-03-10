package org.szpax.game.world.events.events;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Material;
import org.szpax.game.world.calculators.KingdomCalculator;
import org.szpax.game.world.events.requirements.EventRequirement;

import java.util.Set;

public class Production implements Event {

    private final Material material;
    private final KingdomCalculator kingdomCalculator;

    public Production(Material material, KingdomCalculator kingdomCalculator) {
        this.material = material;
        this.kingdomCalculator = kingdomCalculator;
    }

    @Override
    public void takesPlaceIn(Kingdom kingdom) {
        kingdom.getStorage().add(material, kingdomCalculator.foodChange(kingdom));
    }

    @Override
    public Set<EventRequirement> requirements() {
        return null;
    }

    public static Builder of(Material material) {
        return new Builder(material);
    }

    public static class Builder {

        private Material material;
        private KingdomCalculator kingdomCalculator;

        private Builder(Material material) {
            this.material = material;
            this.kingdomCalculator = new KingdomCalculator();
        }

        public Production build() {
            return new Production(material, kingdomCalculator);
        }
    }
}
