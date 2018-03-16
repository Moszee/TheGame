package org.szpax.game.framework;

import lombok.extern.slf4j.Slf4j;
import org.szpax.game.framework.calculators.Calculations;
import org.szpax.game.framework.events.EventChain;
import org.szpax.game.framework.model.Realm;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class World {

    private final EventChain eventChain;
    private final Calculations calculations;

    private final List<Realm> realms = new ArrayList<>();

    public World(EventChain eventChain, Calculations calculations) {
        this.eventChain = eventChain;
        this.calculations = calculations;
    }

    public Realm spawnRealm(String name) {
        Realm realm = new Realm(name, this);
        realms.add(realm);
        return realm;
    }

    public List<Realm> getRealms() {
        return realms;
    }

    public Calculations calculations() {
        return calculations;
    }

    public static Builder newWorld() {
        return new Builder();
    }

    public void executeEventsIn(World world) {
        world.getRealms().forEach(
                realm -> {
                    log.debug("Executing events from event chain in Realm \"{}\"", realm.getName());
                    eventChain.play(realm);
                }
        );
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
            if (calculations == null) {
                throw new IllegalStateException();
            }

            if (eventChain == null) {
                throw new IllegalStateException();
            }

            return new World(eventChain.build(), calculations);
        }
    }
}
