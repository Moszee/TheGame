package org.szpax.game.framework.api;

import org.szpax.game.framework.model.Kingdom;

import java.util.Set;

public interface Event {
    void takesPlaceIn(Kingdom kingdom);

    Set<EventRequirement> requirements();
}
