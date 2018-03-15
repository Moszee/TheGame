package org.szpax.game.framework.executors;

import org.szpax.game.framework.api.EventExecutor;
import org.szpax.game.framework.model.Kingdom;
import org.szpax.game.framework.api.Event;

public class SaturatingEventExecutor implements EventExecutor {

    private final Event event;

    public SaturatingEventExecutor(Event event) {
        this.event = event;
    }

    public boolean canExecuteIn(Kingdom kingdom) {
        return event.requirements().stream().allMatch(it -> it.canTakePlaceIn(kingdom));
    }

    public void executeIn(Kingdom kingdom) {
        while (canExecuteIn(kingdom)) {
            event.takesPlaceIn(kingdom);
        }
    }
}
