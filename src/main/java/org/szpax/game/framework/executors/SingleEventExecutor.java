package org.szpax.game.framework.executors;

import org.szpax.game.framework.api.EventExecutor;
import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.api.Event;

public class SingleEventExecutor implements EventExecutor {

    private final Event event;

    public SingleEventExecutor(Event event) {
        this.event = event;
    }

    @Override
    public boolean canExecuteIn(Realm realm) {
        return event.requirements().stream().allMatch(it -> it.canTakePlaceIn(realm));
    }

    @Override
    public void executeIn(Realm realm) {
        event.takesPlaceIn(realm);
    }
}
