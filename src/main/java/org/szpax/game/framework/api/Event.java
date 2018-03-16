package org.szpax.game.framework.api;

import org.szpax.game.framework.model.Realm;

import java.util.Set;

public interface Event {
    void takesPlaceIn(Realm realm);

    Set<EventRequirement> requirements();
}
