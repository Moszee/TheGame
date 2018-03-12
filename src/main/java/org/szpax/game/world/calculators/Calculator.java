package org.szpax.game.world.calculators;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.World;

@FunctionalInterface
public interface Calculator {
    int calculateChange(Kingdom kingdom, World world);
}
