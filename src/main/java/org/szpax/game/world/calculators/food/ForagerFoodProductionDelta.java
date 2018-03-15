package org.szpax.game.world.calculators.food;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.world.Kingdom;
import org.szpax.game.world.calculators.Calculations;
import org.szpax.game.world.calculators.Calculator;

import static org.szpax.game.world.assets.Occupation.FORAGER;
import static org.szpax.game.world.calculators.CalculationKeys.FORAGER_FOOD_PRODUCTION;

@Slf4j
public class ForagerFoodProductionDelta implements Calculator {

    @Override
    public Double calculateChange(Kingdom kingdom) {
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
}
