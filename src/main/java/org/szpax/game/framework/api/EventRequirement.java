package org.szpax.game.framework.api;

import org.szpax.game.framework.model.Realm;

@FunctionalInterface
public interface EventRequirement {
    boolean canTakePlaceIn(Realm realm);
}
