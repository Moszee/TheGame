package org.szpax.game.world;

import org.szpax.game.world.assets.Named;

public class NotEnoughResourcesException extends RuntimeException {

    private final Integer needed;
    private final Integer actual;
    private final Named resource;

    public NotEnoughResourcesException(Integer needed, Integer actual, Named resource) {
        this.needed = needed;
        this.actual = actual;
        this.resource = resource;
    }

    public Integer getNeeded() {
        return needed;
    }

    public Integer getActual() {
        return actual;
    }

    public Named resource() {
        return resource;
    }
}
