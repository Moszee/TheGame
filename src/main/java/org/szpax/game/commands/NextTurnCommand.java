package org.szpax.game.commands;

import org.szpax.game.framework.model.Realm;

public class NextTurnCommand implements Command {
    @Override
    public String getDescription() {
        return "next turn";
    }

    @Override
    public void doWork(Realm realm) {
    }

    @Override
    public boolean isTerminating() {
        return true;
    }
}
