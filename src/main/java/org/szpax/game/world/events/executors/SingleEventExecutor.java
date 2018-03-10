package org.szpax.game.world.events.executors;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.events.events.Event;

public class SingleEventExecutor implements EventExecutor {

    private final Event event;

    public SingleEventExecutor(Event event) {
        this.event = event;
    }

    @Override
    public boolean canExecuteIn(Kingdom kingdom) {
        return event.requirements().stream().allMatch(it -> it.canTakePlaceIn(kingdom));
    }

    @Override
    public void executeIn(Kingdom kingdom) {
        event.takesPlaceIn(kingdom);
    }
}
