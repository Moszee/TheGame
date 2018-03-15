package org.szpax.game.world.events;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.framework.events.EventChain;
import org.szpax.game.framework.events.Construction;
import org.szpax.game.framework.events.Consumption;
import org.szpax.game.framework.events.Migration;
import org.szpax.game.framework.events.Production;

import static org.szpax.game.framework.model.Building.HOUSE;
import static org.szpax.game.framework.model.Material.FOOD;
import static org.szpax.game.framework.model.Material.WOOD;
import static org.szpax.game.framework.model.Occupation.FORAGER;
import static org.szpax.game.framework.model.Occupation.WOODCUTTER;
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
