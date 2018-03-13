package org.szpax.game.world.events.requirements;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.World;

@FunctionalInterface
public interface EventRequirement {
    boolean canTakePlaceIn(Kingdom kingdom, World world);
}
