package org.szpax.game.framework.model;

import org.szpax.game.framework.api.Named;

public enum Building implements Named {

    HOUSE("house");

    private String name;

    Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
