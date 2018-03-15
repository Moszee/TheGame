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
import static org.szpax.game.world.events.requirements.ConstructionRequirements.HOUSING_DEMAND;
import static org.szpax.game.world.events.requirements.MigrationRequirements.*;

@Slf4j
public class StandardEventChain {

    public static EventChain.Builder EVENT_CHAIN = EventChain.builder()
            .addEvent(Production.of(FOOD))
            .addEvent(Consumption.of(FOOD))
            .addEvent(Production.of(WOOD))
            .addSaturatingEvent(Migration.of(FORAGER)
                    .requires(FOOD_DEMAND)
                    .requires(FORAGER_PROFITABILITY)
                    .requires(PLACE_TO_LIVE)
                    .requires(4d, FOOD)
            )
            .addSaturatingEvent(Migration.of(WOODCUTTER)
                    .requires(PLACE_TO_LIVE)
                    .requires(4d, FOOD)
            )
            .addSaturatingEvent(Construction.of(HOUSE)
                    .requires(HOUSING_DEMAND)
                    .requires(10d, WOOD)
            );

}
