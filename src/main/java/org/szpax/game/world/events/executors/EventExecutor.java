package org.szpax.game.world.events.executors;

import org.szpax.game.world.Kingdom;

public interface EventExecutor {
    boolean canExecuteIn(Kingdom kingdom);

    void executeIn(Kingdom kingdom);
}
