package org.szpax.game.world.events;

import org.szpax.game.world.Kingdom;
import org.szpax.game.world.events.events.Event;
import org.szpax.game.world.events.executors.EventExecutor;
import org.szpax.game.world.events.executors.SaturatingEventExecutor;
import org.szpax.game.world.events.executors.SingleEventExecutor;
import org.szpax.game.world.events.requirements.EventRequirement;

import java.util.*;

public class EventChain {

    private final List<EventExecutor> events;

    private EventChain(List<EventExecutor> events) {
        this.events = new LinkedList<>(events);
    }

    public void play(Kingdom kingdom) {
        events.stream()
                .filter(event -> event.canExecuteIn(kingdom))
                .forEach(event -> event.executeIn(kingdom));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<Event, Set<EventRequirement>> steps = new TreeMap<>();
        private final List<EventExecutor> events = new ArrayList<>();

        public Builder addSaturatingEvent(Event kingdomEvent) {
            events.add(new SaturatingEventExecutor(kingdomEvent));
            return this;
        }

        public Builder addEvent(Event kingdomEvent) {
            events.add(new SingleEventExecutor(kingdomEvent));
            return this;
        }

        public EventChain build() {
            return new EventChain(events);
        }
    }
}
