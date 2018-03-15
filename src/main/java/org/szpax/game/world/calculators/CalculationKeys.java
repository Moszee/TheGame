package org.szpax.game.world.calculators;

import org.szpax.game.framework.calculators.CalculationKey;

import static org.szpax.game.framework.model.Building.HOUSE;
import static org.szpax.game.framework.model.Material.FOOD;
import static org.szpax.game.framework.model.Material.WOOD;
import static org.szpax.game.framework.model.Occupation.FORAGER;
import static org.szpax.game.framework.model.Occupation.WOODCUTTER;
import static org.szpax.game.framework.calculators.model.BuildingCalculationType.FREE;
import static org.szpax.game.framework.calculators.model.MaterialCalculationType.CONSUMPTION;
import static org.szpax.game.framework.calculators.model.MaterialCalculationType.PRODUCTION;
import static org.szpax.game.framework.calculators.model.MaterialCalculationType.PRODUCTION_DELTA;

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

    public static CalculationKey FORAGER_FOOD_PRODUCTION_DELTA = CalculationKey.process(PRODUCTION_DELTA)
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
