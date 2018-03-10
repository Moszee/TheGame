package org.szpax.game.world.assets;

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
