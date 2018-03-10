package org.szpax.game.world.assets;

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
