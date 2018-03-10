package org.szpax.game.world.assets;

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
