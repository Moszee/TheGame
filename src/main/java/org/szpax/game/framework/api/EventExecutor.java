package org.szpax.game.framework.api;

import org.szpax.game.framework.model.Kingdom;

public interface EventExecutor {
    boolean canExecuteIn(Kingdom kingdom);

    void executeIn(Kingdom kingdom);
}
