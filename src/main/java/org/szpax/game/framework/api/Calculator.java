package org.szpax.game.framework.api;

import org.szpax.game.framework.model.Kingdom;

@FunctionalInterface
public interface Calculator {
    Double calculateChange(Kingdom kingdom);
}
