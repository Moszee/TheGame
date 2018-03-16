package org.szpax.game.world.calculators.formulas;

import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.model.Occupation;
import org.szpax.game.framework.api.Calculator;

import static org.szpax.game.framework.model.Resource.BERRIES;

public class ForagerFoodProduction implements Calculator     {
    @Override
    public Double calculateChange(Realm realm) {
        Double foragers = realm.getPopulation().get(Occupation.FORAGER);
        Double berries = realm.getResources().get(BERRIES);

        return Math.min(foragers / 2, berries * Math.log(foragers + 1));
    }
}
