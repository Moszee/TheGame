package org.szpax.game.commands;

import org.szpax.game.world.Kingdom;

public interface Command {

    String getDescription();

    void doWork(Kingdom kingdom);

    boolean isTerminating();
}
