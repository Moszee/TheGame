package org.szpax.game.world.calculators.formulas;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.framework.model.Kingdom;
import org.szpax.game.framework.calculators.Calculations;
import org.szpax.game.framework.api.Calculator;

import static org.szpax.game.framework.model.Occupation.FORAGER;
import static org.szpax.game.world.calculators.CalculationKeys.FORAGER_FOOD_PRODUCTION;

@Slf4j
public class ForagerFoodProductionDelta implements Calculator {

    @Override
    public Double calculateChange(Kingdom kingdom) {
        Calculations.KingdomCalculation foragerCalculation =
                kingdom
                        .world()
                        .calculations()
                        .get(FORAGER_FOOD_PRODUCTION);

        Double current = foragerCalculation.in(kingdom).sum();

        log.info("Current gain: {}", current);

        Kingdom futureState = kingdom.copy();
        futureState.getPopulation().add(FORAGER, 1d);
        Double potentialProduction = foragerCalculation.in(futureState).sum();

        log.info("Potential gain: {}", potentialProduction);

        Double i = potentialProduction - current;
        log.info("Potential delta: {}", i);
        return i;
    }
}
