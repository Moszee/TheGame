package org.szpax.game.world.events.events;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Material;
import org.szpax.game.world.events.requirements.EventRequirement;

import java.util.Set;

public class Production implements Event {

    @Override
    public void takesPlaceIn(Kingdom kingdom) {

    }

    @Override
    public Set<EventRequirement> requirements() {
        return null;
    }

    public static Builder of(Material material) {
        return new Builder(material);
    }

    public static class Builder {
        private Builder(Material material) {

        }

        public Production build() {
            return new Production();
        }
    }
}
