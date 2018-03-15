package org.szpax.game.world.calculators.food;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Occupation;
import org.szpax.game.world.calculators.Calculator;

import static org.szpax.game.world.assets.Resource.BERRIES;

public class ForagerFoodProduction implements Calculator {
    @Override
    public Double calculateChange(Kingdom kingdom) {
        Double foragers = kingdom.getPopulation().get(Occupation.FORAGER);
        Double berries = kingdom.getResources().get(BERRIES);

        return Math.min(foragers / 2, berries * Math.log(foragers + 1));
    }
}
