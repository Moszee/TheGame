package org.szpax.game.framework.model;

import org.szpax.game.framework.api.Named;

public enum Occupation implements Named {
    FORAGER("peasant"),
    WOODCUTTER("woodcutter");

    private String name;

    Occupation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
