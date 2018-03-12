package org.szpax.game.world.calculators;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Material.FOOD;
import static org.szpax.game.world.assets.Material.WOOD;
import static org.szpax.game.world.assets.Occupation.FORAGER;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.calculators.BuildingCalculationType.FREE;
import static org.szpax.game.world.calculators.MaterialCalculationType.CONSUMPTION;
import static org.szpax.game.world.calculators.MaterialCalculationType.PRODUCTION;

public class CalculationKeys {

    public static CalculationKey FOOD_CONSUMPTION = CalculationKey.process(CONSUMPTION)
            .of(FOOD)
            .build();

    public static CalculationKey FOOD_PRODUCTION = CalculationKey.process(PRODUCTION)
            .of(FOOD)
            .build();

    public static CalculationKey FORAGER_FOOD_PRODUCTION = CalculationKey.process(PRODUCTION)
            .of(FOOD)
            .by(FORAGER)
            .build();

    public static CalculationKey WOODCUTTER_WOOD_PRODUCTION = CalculationKey.process(PRODUCTION)
            .of(WOOD)
            .by(WOODCUTTER)
            .build();

    public static CalculationKey FREE_HOUSING = CalculationKey
            .numberOf(FREE)
            .placesIn(HOUSE)
            .build();
}
