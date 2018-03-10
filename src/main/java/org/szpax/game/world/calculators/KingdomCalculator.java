package org.szpax.game.world.calculators;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Occupation;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.assets.Resource.BERRIES;

public class KingdomCalculator {

    public Integer foragersProduction(Kingdom kingdom) {
        Integer foragers = kingdom.getPopulation().get(Occupation.FORAGER);
        Integer berries = kingdom.getResources().get(BERRIES);

        return (int) Math.floor(Math.min(foragers / 2, berries * Math.log(foragers + 1)));
    }

    public int foodConsumption(Kingdom kingdom) {
        return kingdom.getPopulation().total() / 5;
    }

    public int foodChange(Kingdom kingdom) {
        return foragersProduction(kingdom) - foodConsumption(kingdom);
    }

    public int woodChange(Kingdom kingdom) {
        return kingdom.getPopulation().get(WOODCUTTER) / 5;
    }

    public int freeHousing(Kingdom kingdom) {
        return (kingdom.getBuildings().get(HOUSE) * 5) - kingdom.getPopulation().total();
    }
}
