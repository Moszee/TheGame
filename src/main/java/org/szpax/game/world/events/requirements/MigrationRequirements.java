package org.szpax.game.world.events.requirements;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.framework.api.EventRequirement;

import static org.szpax.game.world.calculators.CalculationKeys.*;

@Slf4j
public class MigrationRequirements {

    public static final EventRequirement FOOD_DEMAND = (kingdom) -> {
        Double foodProduced = kingdom.world().calculations().get(FOOD_PRODUCTION).in(kingdom).sum();
        Double foodConsumed = kingdom.world().calculations().get(FOOD_CONSUMPTION).in(kingdom).sum();

        return foodProduced - foodConsumed < 4;
    };

    public static final EventRequirement FORAGER_PROFITABILITY = (kingdom) -> {
        Double potentialGain = kingdom.world().calculations().get(FORAGER_FOOD_PRODUCTION_DELTA).in(kingdom).sum();
        log.debug("Potential new forager gain: {}", potentialGain);
        return 10 * potentialGain > 3;
    };

    public static final EventRequirement PLACE_TO_LIVE = (kingdom) -> kingdom.world().calculations().get(FREE_HOUSING).in(kingdom).sum() > 0;

}
