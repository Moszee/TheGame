package org.szpax.game.framework;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.framework.calculators.Calculations;
import org.szpax.game.framework.events.EventChain;
import org.szpax.game.framework.model.Kingdom;

@Slf4j
public class World {

    private final EventChain eventChain;
    private final Calculations calculations;

    public World(EventChain eventChain, Calculations calculations) {
        this.eventChain = eventChain;
        this.calculations = calculations;
    }

    public Calculations calculations() {
        return calculations;
    }

    public static Builder newWorld() {
        return new Builder();
    }

    public void executeEventsIn(Kingdom kingdom) {
        log.debug("Executing events from event chain in Kingdom \"{}\"", kingdom.getName());
        eventChain.play(kingdom);
    }

    public static class Builder {

        private EventChain.Builder eventChain;
        private Calculations calculations;

        public Builder withEventChain(EventChain.Builder eventChain) {
            this.eventChain = eventChain;
            return this;
        }

        public Builder withCalculcations(Calculations calculations) {
            this.calculations = calculations;
            return this;
        }

        public World create() {
            if(calculations == null) {
                throw new IllegalStateException();
            }

            if(eventChain == null) {
                throw new IllegalStateException();
            }

            return new World(eventChain.build(), calculations);
        }
    }
}
