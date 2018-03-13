package org.szpax.game.world.events.requirements;

import org.szpax.game.world.Kingdom;

@FunctionalInterface
public interface EventRequirement {
    boolean canTakePlaceIn(Kingdom kingdom);
}
