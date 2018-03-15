package org.szpax.game.world;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.world.assets.Occupation;
import org.szpax.game.world.calculators.Calculations;
import org.szpax.game.world.calculators.food.ForagerFoodProduction;
import org.szpax.game.world.calculators.food.ForagerFoodProductionDelta;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.assets.Resource.BERRIES;
import static org.szpax.game.world.calculators.CalculationKeys.*;

@Slf4j
public class StandardCalculationSet {

    public static Calculations STANDARD_CALCULATIONS = Calculations.builder()

            .calculationOf(FOOD_CONSUMPTION)
            .formula((kingdom) -> kingdom.getPopulation().total() / 5d)

            .calculationOf(FORAGER_FOOD_PRODUCTION)
            .formula(new ForagerFoodProduction())

            .calculationOf(FORAGER_FOOD_PRODUCTION_DELTA)
            .formula(new ForagerFoodProductionDelta())

            .calculationOf(WOODCUTTER_WOOD_PRODUCTION)
            .formula((kingdom) -> kingdom.getPopulation().get(WOODCUTTER) / 2d)

            .calculationOf(FREE_HOUSING)
            .formula((kingdom) -> kingdom.getBuildings().get(HOUSE) * 5d - kingdom.getPopulation().total())

            .build();


}
