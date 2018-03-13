package org.szpax.game.world;

import lombok.extern.slf4j.Slf4j;
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
import static org.szpax.game.world.calculators.CalculationKeys.*;

@Slf4j
public class StandardEventChain {

    public static EventChain.Builder EVENT_CHAIN = EventChain.builder()
            .addEvent(Production.of(FOOD))
            .addEvent(Consumption.of(FOOD))
            .addEvent(Production.of(WOOD))
            .addSaturatingEvent(Migration.of(FORAGER)
                    .requires((kingdom) -> {
                        Double foodProduced = kingdom.world().calculations().get(FOOD_PRODUCTION).in(kingdom).sum();
                        Double foodConsumed = kingdom.world().calculations().get(FOOD_CONSUMPTION).in(kingdom).sum();

                        return foodProduced - foodConsumed < 4;
                    })
                    .requires((kingdom) -> {
                        Double potentialGain = kingdom.world().calculations().get(FORAGER_FOOD_PRODUCTION_DELTA).in(kingdom).sum();
                        log.info("Potential new forager gain: {}", potentialGain);
                        return 10 * potentialGain > 3;
                    })
                    .requires((kingdom) -> kingdom.world().calculations().get(FREE_HOUSING).in(kingdom).sum() > 0)
                    .requires(4d, FOOD))
            .addSaturatingEvent(Migration.of(WOODCUTTER)
                    .requires((kingdom) -> kingdom.world().calculations().get(FREE_HOUSING).in(kingdom).sum() > 0)
                    .requires(4d, FOOD))
            .addSaturatingEvent(Construction.of(HOUSE)
                    .requires((kingdom) -> {
                        Double houseOvercapacity = kingdom.getStorage().get(FOOD) / 4 * 2;
                        return kingdom.world().calculations().get(FREE_HOUSING).in(kingdom).sum() < houseOvercapacity;
                    })
                    .requires(10d, WOOD));

}
