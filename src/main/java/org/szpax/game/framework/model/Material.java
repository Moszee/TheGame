package org.szpax.game.framework.model;

import org.szpax.game.framework.api.Named;

public enum Material implements Named {

    FOOD("food"),
    WOOD("wood");

    private String name;

    Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
