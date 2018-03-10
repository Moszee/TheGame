package org.szpax.game.commands;

import org.szpax.game.world.Kingdom;

public class NextTurnCommand implements Command {
    @Override
    public String getDescription() {
        return "next turn";
    }

    @Override
    public void doWork(Kingdom kingdom) {
    }

    @Override
    public boolean isTerminating() {
        return true;
    }
}
