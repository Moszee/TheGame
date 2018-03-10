package org.szpax.game.world.assets;

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
