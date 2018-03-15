package org.szpax.game.framework.api;

import org.szpax.game.framework.model.Kingdom;

@FunctionalInterface
public interface EventRequirement {
    boolean canTakePlaceIn(Kingdom kingdom);
}
