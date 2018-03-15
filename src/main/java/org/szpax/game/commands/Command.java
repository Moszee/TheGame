package org.szpax.game.commands;

import org.szpax.game.framework.model.Kingdom;

public interface Command {

    String getDescription();

    void doWork(Kingdom kingdom);

    boolean isTerminating();
}
