package org.szpax.game.world.calculators;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.assets.Material;
import org.szpax.game.world.assets.Occupation;

import java.util.HashMap;
import java.util.Map;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.assets.Resource.BERRIES;

public class KingdomCalculator {

    private Map<Material, ProductionCalculator> calculators = new HashMap<>();

    public KingdomCalculator() {
        ProductionCalculator foodCalculator = ProductionCalculator.builder()
                .addConsumption(kingdom -> kingdom.getPopulation().total() / 5)
                .addProduction(kingdom -> {
                    Integer foragers = kingdom.getPopulation().get(Occupation.FORAGER);
                    Integer berries = kingdom.getResources().get(BERRIES);

                    return (int) Math.floor(Math.min(foragers / 2, berries * Math.log(foragers + 1)));
                })
                .build();

        calculators.put(Material.FOOD, foodCalculator);
    }

    public int foodChange(Kingdom kingdom) {
        return calculators.get(Material.FOOD).calculateChange(kingdom);
    }

    public int woodChange(Kingdom kingdom) {
        return kingdom.getPopulation().get(WOODCUTTER) / 5;
    }

    public int freeHousing(Kingdom kingdom) {
        return (kingdom.getBuildings().get(HOUSE) * 5) - kingdom.getPopulation().total();
    }
}
