package org.szpax.game.world.events.events;

import org.szpax.game.world.calculators.Calculations;

public interface EventBuilder {

    Event build(Calculations calculations);

}
