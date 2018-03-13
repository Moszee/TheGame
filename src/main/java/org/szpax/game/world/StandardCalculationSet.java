package org.szpax.game.world;

import org.szpax.game.world.assets.Occupation;
import org.szpax.game.world.calculators.Calculations;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.assets.Resource.BERRIES;
import static org.szpax.game.world.calculators.CalculationKeys.*;

public class StandardCalculationSet {

    public static Calculations STANDARD_CALCULATIONS = Calculations.builder()

                .calculationOf(FOOD_CONSUMPTION)
                .formula((kingdom, world) -> kingdom.getPopulation().total() / 5)

                .calculationOf(FORAGER_FOOD_PRODUCTION)
                .formula((kingdom, world) -> {
                            Integer foragers = kingdom.getPopulation().get(Occupation.FORAGER);
                            Integer berries = kingdom.getResources().get(BERRIES);

                            return (int) Math.floor(Math.min(foragers / 2, berries * Math.log(foragers + 1)));
                        }
                )

                .calculationOf(WOODCUTTER_WOOD_PRODUCTION)
                .formula((kingdom, world) -> kingdom.getPopulation().get(WOODCUTTER) / 2)

                .calculationOf(FREE_HOUSING)
                .formula((kingdom, world) -> kingdom.getBuildings().get(HOUSE) * 5 - kingdom.getPopulation().total())

                .build();
}
