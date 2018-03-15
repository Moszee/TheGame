package org.szpax.game.framework.model;

import org.szpax.game.framework.api.Named;

public enum Resource implements Named {
    BERRIES("berries");

    private String name;

    @Override
    public String getName() {
        return name;
    }

    Resource(String name) {
        this.name = name;
    }

}
