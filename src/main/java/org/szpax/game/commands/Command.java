package org.szpax.game.commands;

import org.szpax.game.framework.model.Realm;

public interface Command {

    String getDescription();

    void doWork(Realm realm);

    boolean isTerminating();
}
