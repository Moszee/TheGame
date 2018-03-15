package org.szpax.game.world.events.requirements;

import static org.szpax.game.world.assets.Material.FOOD;
import static org.szpax.game.world.calculators.CalculationKeys.FREE_HOUSING;

public class ConstructionRequirements {
    public static final EventRequirement HOUSING_DEMAND = (kingdom) -> {
        Double houseOvercapacity = kingdom.getStorage().get(FOOD) / 4 * 2;
        return kingdom.world().calculations().get(FREE_HOUSING).in(kingdom).sum() < houseOvercapacity;
    };
}
