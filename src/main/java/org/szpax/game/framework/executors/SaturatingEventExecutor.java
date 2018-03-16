package org.szpax.game.framework.executors;

import org.szpax.game.framework.api.EventExecutor;
import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.api.Event;

public class SaturatingEventExecutor implements EventExecutor {

    private final Event event;

    public SaturatingEventExecutor(Event event) {
        this.event = event;
    }

    public boolean canExecuteIn(Realm realm) {
        return event.requirements().stream().allMatch(it -> it.canTakePlaceIn(realm));
    }

    public void executeIn(Realm realm) {
        while (canExecuteIn(realm)) {
            event.takesPlaceIn(realm);
        }
    }
}
