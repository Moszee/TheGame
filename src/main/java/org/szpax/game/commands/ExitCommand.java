package org.szpax.game.commands;

import org.szpax.game.world.Kingdom;

public class ExitCommand implements Command {
    @Override
    public String getDescription() {
        return "exit";
    }

    @Override
    public void doWork(Kingdom kingdom) {
        System.exit(0);
    }

    @Override
    public boolean isTerminating() {
        return true;
    }
}
