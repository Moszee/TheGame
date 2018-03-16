package org.szpax.game.framework.api;

import org.szpax.game.framework.model.Realm;

public interface EventExecutor {
    boolean canExecuteIn(Realm realm);

    void executeIn(Realm realm);
}
