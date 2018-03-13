package org.szpax.game.world;

import org.szpax.game.world.events.EventChain;
import org.szpax.game.world.events.events.Construction;
import org.szpax.game.world.events.events.Consumption;
import org.szpax.game.world.events.events.Migration;
import org.szpax.game.world.events.events.Production;

import static org.szpax.game.world.assets.Building.HOUSE;
import static org.szpax.game.world.assets.Material.FOOD;
import static org.szpax.game.world.assets.Material.WOOD;
import static org.szpax.game.world.assets.Occupation.FORAGER;
import static org.szpax.game.world.assets.Occupation.WOODCUTTER;
import static org.szpax.game.world.calculators.CalculationKeys.FOOD_CONSUMPTION;
import static org.szpax.game.world.calculators.CalculationKeys.FOOD_PRODUCTION;
import static org.szpax.game.world.calculators.CalculationKeys.FREE_HOUSING;

public class StandardEventChain {

    public static EventChain.Builder EVENT_CHAIN = EventChain.builder()
            .addEvent(Production.of(FOOD))
            .addEvent(Consumption.of(FOOD))
            .addEvent(Production.of(WOOD))
            .addSaturatingEvent(Migration.of(FORAGER)
                    .requires((kingdom, world) -> {
                        int foodProduced = world.calculations().get(FOOD_PRODUCTION).in(kingdom).sum();
                        int foodConsumed = world.calculations().get(FOOD_CONSUMPTION).in(kingdom).sum();

                        return foodProduced - foodConsumed < 4;
                    })
                    .requires((kingdom, world) -> world.calculations().get(FREE_HOUSING).in(kingdom).sum() > 0)
                    .requires(4, FOOD))
            .addSaturatingEvent(Migration.of(WOODCUTTER)
                    .requires((kingdom, world) -> world.calculations().get(FREE_HOUSING).in(kingdom).sum() > 0)
                    .requires(4, FOOD))
            .addSaturatingEvent(Construction.of(HOUSE)
                    .requires(10, WOOD));
}
