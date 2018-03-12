package org.szpax.game.world.calculators;

import org.szpax.game.world.Kingdom;

@FunctionalInterface
public interface Calculator {
    int calculateChange(Kingdom kingdom);
}
