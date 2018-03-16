package org.szpax.game.commands;

import org.szpax.game.framework.model.Realm;

public class ExitCommand implements Command {
    @Override
    public String getDescription() {
        return "exit";
    }

    @Override
    public void doWork(Realm realm) {
        System.exit(0);
    }

    @Override
    public boolean isTerminating() {
        return true;
    }
}
