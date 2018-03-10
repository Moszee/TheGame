package org.szpax.game.world.events.events;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.events.requirements.EventRequirement;

import java.util.Set;

public interface Event {
    void takesPlaceIn(Kingdom kingdom);

    Set<EventRequirement> requirements();
}
