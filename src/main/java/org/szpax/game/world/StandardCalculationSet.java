package org.szpax.game.world;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.world.assets.Occupation;
import org.szpax.game.world.calculators.Calculations;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Occupation.FORAGER;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.assets.Resource.BERRIES;
import static org.szpax.game.world.calculators.CalculationKeys.*;

@Slf4j
public class StandardCalculationSet {

    public static Calculations STANDARD_CALCULATIONS = Calculations.builder()

            .calculationOf(FOOD_CONSUMPTION)
            .formula((kingdom) -> kingdom.getPopulation().total() / 5d)

            .calculationOf(FORAGER_FOOD_PRODUCTION)
            .formula((kingdom) -> {
                        Double foragers = kingdom.getPopulation().get(Occupation.FORAGER);
                        Double berries = kingdom.getResources().get(BERRIES);

                        return Math.min(foragers / 2, berries * Math.log(foragers + 1));
                    }
            )

            .calculationOf(FORAGER_FOOD_PRODUCTION_DELTA)
            .formula((kingdom) -> {
                        Calculations.KingdomCalculation foragerCalculation = kingdom.world().calculations().get(FORAGER_FOOD_PRODUCTION);

                        Double current = foragerCalculation.in(kingdom).sum();
                        log.info("Current gain: {}", current);
                        Kingdom potential = kingdom.copy();
                        potential.getPopulation().add(FORAGER, 1d);
                        Double potentialProduction = foragerCalculation.in(potential).sum();
                        log.info("Potential gain: {}", potentialProduction);

                        Double i = potentialProduction - current;
                        log.info("Potential delta: {}", i);
                        return i;
                    }
            )

            .calculationOf(WOODCUTTER_WOOD_PRODUCTION)
            .formula((kingdom) -> kingdom.getPopulation().get(WOODCUTTER) / 2d)

            .calculationOf(FREE_HOUSING)
            .formula((kingdom) -> kingdom.getBuildings().get(HOUSE) * 5d - kingdom.getPopulation().total())

            .build();
}
